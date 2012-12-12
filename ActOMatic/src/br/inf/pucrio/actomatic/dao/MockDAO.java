package br.inf.pucrio.actomatic.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.action.configuration.VolumeConfigurationCommand;
import br.inf.pucrio.actomatic.action.notification.EmailNotificationCommand;
import br.inf.pucrio.actomatic.action.notification.SmsNotificationCommand;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.event.timer.TimerCommand;
import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.Configuration;
import br.inf.pucrio.actomatic.model.Configuration.ConfigurationType;
import br.inf.pucrio.actomatic.model.Event;
import br.inf.pucrio.actomatic.model.Notification;
import br.inf.pucrio.actomatic.model.Notification.NotificationType;
import br.inf.pucrio.actomatic.model.Time;

public class MockDAO
{
	public static List<ActionCommand<? extends Action>> createMockActions()
	{

		Notification nSMS = new Notification();
		nSMS.setDescription( "Mensagem para testar SMS." );
		nSMS.setName( "Mensagem de teste para SMS." );
		nSMS.setMessage( "Testando minha aplicacao." );
		nSMS.setSendTo( "+552183882998" );
		nSMS.setType( NotificationType.SMS );

		SmsNotificationCommand sms = new SmsNotificationCommand( nSMS );
		sms.setDescription( "Acao de enviar SMS" );
		sms.setName( "Enviar SMS" );

		Notification email = new Notification();
		email.setDescription( "Teste para enviar email" );
		email.setMessage( "Mensagem de texto para email." );
		email.setName( "Titulo: subject" );
		email.setSendTo( "eijiadachi@gmail.com" );
		email.setType( NotificationType.EMAIL );

		EmailNotificationCommand emailCommand = new EmailNotificationCommand( email );
		emailCommand.setDescription( "Email command description" );
		emailCommand.setName( "Email Command 1" );

		Configuration configuration = new Configuration();
		configuration.setDescription( "Mudo" );
		configuration.setName( "Mudo" );
		configuration.setSetting( 0.0 );
		configuration.setType( ConfigurationType.VOLUME );
		VolumeConfigurationCommand volume = new VolumeConfigurationCommand( configuration );
		volume.setDescription( "Colocar dispositivo no mudo." );
		volume.setName( "Colocar no mudo." );

		List<ActionCommand<? extends Action>> list = new ArrayList<ActionCommand<? extends Action>>();

		list.add( sms );
		list.add( emailCommand );
		list.add( volume );

		return list;
	}

	public static List<EventCommand<? extends Event>> createMockEvents()
	{
		Calendar instance = Calendar.getInstance();
		instance.add( Calendar.MINUTE, 1 );

		Date date = instance.getTime();

		String str = "Dispara na hora";
		Time time = new Time( date );
		time.setDescription( str );
		time.setName( str );

		TimerCommand timer = new TimerCommand( time );
		timer.setDescription( str );
		timer.setName( str );

		List<EventCommand<? extends Event>> list = new ArrayList<EventCommand<? extends Event>>();
		list.add( timer );

		return list;
	}
}
