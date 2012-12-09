package br.inf.pucrio.actomatic.dao;

import java.util.Date;

import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.action.VolumeConfigurationCommand;
import br.inf.pucrio.actomatic.action.notification.AlertNotificationCommand;
import br.inf.pucrio.actomatic.action.notification.EmailNotificationCommand;
import br.inf.pucrio.actomatic.action.notification.SmsNotificationCommand;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.event.region.EnterRegionCommand;
import br.inf.pucrio.actomatic.event.region.LeaveRegionCommand;
import br.inf.pucrio.actomatic.event.timer.TimerCommand;
import br.inf.pucrio.actomatic.model.Configuration;
import br.inf.pucrio.actomatic.model.Configuration.ConfigurationType;
import br.inf.pucrio.actomatic.model.Notification;
import br.inf.pucrio.actomatic.model.Notification.NotificationType;
import br.inf.pucrio.actomatic.model.Region;
import br.inf.pucrio.actomatic.model.Rule;
import br.inf.pucrio.actomatic.model.Time;

public final class FactoryDAO
{
	private static TransientDAO<ActionCommand<?>> actionDAO;

	private static TransientDAO<EventCommand<?>> eventDAO;

	private static TransientDAO<Rule> ruleDAO;

	static
	{
		Notification notification1 = new Notification();
		notification1.setDescription( "This is a mock notification." );
		notification1.setName( "Mock Notification 1" );
		notification1.setMessage( "Hello World!" );
		notification1.setSendTo( "Me, of course." );
		notification1.setType( NotificationType.ALERT );

		Notification notification2 = new Notification();
		notification2.setDescription( "This is another mock notification." );
		notification2.setName( "Mock Notification 2" );
		notification2.setMessage( "Hello World again?!" );
		notification2.setSendTo( "Not you." );
		notification2.setType( NotificationType.EMAIL );

		Notification notification3 = new Notification();
		notification3.setDescription( "This is the last mock notification." );
		notification3.setName( "Mock Notification 3" );
		notification3.setMessage( "Last hellow!" );
		notification3.setSendTo( "Not you again." );
		notification3.setType( NotificationType.SMS );

		AlertNotificationCommand action1 = new AlertNotificationCommand( notification1 );
		EmailNotificationCommand action2 = new EmailNotificationCommand( notification2 );
		SmsNotificationCommand action3 = new SmsNotificationCommand( notification3 );

		Configuration configuration1 = new Configuration();
		configuration1.setDescription( "Mock description" );
		configuration1.setName( "Config 1 " );
		configuration1.setSetting( 10.2 );
		configuration1.setType( ConfigurationType.SCREEN );

		Configuration configuration2 = new Configuration();
		configuration2.setDescription( "Mock description 2" );
		configuration2.setName( "Config 2" );
		configuration2.setSetting( 11.2 );
		configuration2.setType( ConfigurationType.VOLUME );

		VolumeConfigurationCommand action4 = new VolumeConfigurationCommand( configuration1 );
		VolumeConfigurationCommand action5 = new VolumeConfigurationCommand( configuration2 );

		actionDAO = new TransientDAO<ActionCommand<?>>();
		actionDAO.add( action1 );
		actionDAO.add( action2 );
		actionDAO.add( action3 );
		actionDAO.add( action4 );
		actionDAO.add( action5 );

		Region region = new Region();
		region.setLatitude( -22.96750220 );
		region.setLongitude( -43.18187130 );
		region.setRadius( 10.0 );
		region.setDescription( "Home" );
		region.setName( "Home Region" );

		EnterRegionCommand event1 = new EnterRegionCommand( region );
		LeaveRegionCommand event2 = new LeaveRegionCommand( region );
		Date date = new Date( System.currentTimeMillis() + 15000 );
		Time time = new Time( date );
		TimerCommand event3 = new TimerCommand( time );
		event3.setDescription( "new time" );
		event3.setName( "Event Timer" );

		eventDAO = new TransientDAO<EventCommand<?>>();
		eventDAO.add( event1 );
		eventDAO.add( event2 );
		eventDAO.add( event3 );

		event3.addObserver( action1 );

		Rule rule = new Rule();
		rule.setAction( action1 );
		rule.setEvent( event3 );
		rule.setDescription( "Just another Mock Rule" );
		rule.setName( "Mock Rule" );
		ruleDAO = new TransientDAO<Rule>();
		ruleDAO.add( rule );
	}

	public static IAbstractDAO<ActionCommand<?>> getActionDAOInstance()
	{
		if (actionDAO != null)
		{
			return actionDAO;
		}
		return new TransientDAO<ActionCommand<?>>();
	}

	public static IAbstractDAO<EventCommand<?>> getEventDAOInstance()
	{
		if (eventDAO != null)
		{
			return eventDAO;
		}
		return new TransientDAO<EventCommand<?>>();
	}

	public static IAbstractDAO<Rule> getRuleDAOInstance()
	{
		if (ruleDAO != null)
		{
			return ruleDAO;
		}
		return new TransientDAO<Rule>();
	}
}
