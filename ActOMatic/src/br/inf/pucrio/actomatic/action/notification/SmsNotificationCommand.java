package br.inf.pucrio.actomatic.action.notification;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import br.inf.pucrio.actomatic.MainActivity;
import br.inf.pucrio.actomatic.model.Notification;

public class SmsNotificationCommand extends NotificationCommand
{
	public SmsNotificationCommand(Notification argument)
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

		final String phoneNumber = notification.getSendTo();
		final String message = notification.getMessage();

		activity.runOnUiThread( new Runnable()
		{

			public void run()
			{
				PendingIntent pi = PendingIntent.getActivity( activity, 0, new Intent( activity,
						MainActivity.class ), 0 );

				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage( phoneNumber, null, message, pi, null );

			}
		} );

	}
}
