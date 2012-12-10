package br.inf.pucrio.actomatic.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.action.notification.AlertNotificationCommand;
import br.inf.pucrio.actomatic.action.notification.EmailNotificationCommand;
import br.inf.pucrio.actomatic.action.notification.SmsNotificationCommand;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.event.timer.TimerCommand;
import br.inf.pucrio.actomatic.model.Action;
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
		nSMS.setSendTo( "+558487296563" );
		nSMS.setType( NotificationType.SMS );

		SmsNotificationCommand sms = new SmsNotificationCommand( nSMS );
		sms.setDescription( "Acao de enviar SMS para malu" );
		sms.setName( "Enviar SMS para Malu" );

		AlertNotificationCommand alert = new AlertNotificationCommand( nSMS );
		alert.setDescription( "Acao para criar alerta" );
		alert.setName( "Alertar nativo" );

		Notification email = new Notification();
		email.setDescription( "Teste para enviar email" );
		email.setMessage( "Mensagem de texto para email." );
		email.setName( "Titulo: subject" );
		email.setSendTo( "eijiadachi@gmail.com" );
		email.setType( NotificationType.EMAIL );

		EmailNotificationCommand emailCommand = new EmailNotificationCommand( email );
		emailCommand.setDescription( "Email command description" );
		emailCommand.setName( "Email Command 1" );

		List<ActionCommand<? extends Action>> list = new ArrayList<ActionCommand<? extends Action>>();

		list.add( sms );
		// list.add( alert );
		list.add( emailCommand );

		return list;
	}

	public static List<EventCommand<? extends Event>> createMockEvents()
	{
		Calendar instance = Calendar.getInstance();
		instance.add( Calendar.MINUTE, 1 );

		Date date = instance.getTime();

		Time time = new Time( date );
		time.setDescription( "Daqui a 1 min" );
		time.setName( "Daqui a 1 min" );

		TimerCommand timer = new TimerCommand( time );
		timer.setDescription( "Dispara em 1min" );
		timer.setName( "Dispara em 1min" );

		List<EventCommand<? extends Event>> list = new ArrayList<EventCommand<? extends Event>>();
		list.add( timer );

		return list;
	}
}
