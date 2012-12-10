package br.inf.pucrio.actomatic.action.notification;

import android.app.Activity;
import android.content.Intent;
import br.inf.pucrio.actomatic.model.Notification;

public class EmailNotificationCommand extends NotificationCommand
{

	public EmailNotificationCommand(Notification argument)
	{
		super( argument );
	}

	@Override
	public String getObjectType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(final Activity activity)
	{
		Notification notification = this.getArgument();
		final String message = notification.getMessage();
		final String name = notification.getName();
		final String sendTo = notification.getSendTo();

		Intent i = new Intent( Intent.ACTION_SEND );
		i.setType( "message/rfc822" );
		i.putExtra( Intent.EXTRA_EMAIL, new String[] { sendTo } );
		i.putExtra( Intent.EXTRA_SUBJECT, name );
		i.putExtra( Intent.EXTRA_TEXT, message );

		activity.startActivity( Intent.createChooser( i, "Send mail..." ) );
	}

}
