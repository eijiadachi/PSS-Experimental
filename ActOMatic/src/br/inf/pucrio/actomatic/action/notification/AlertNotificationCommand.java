package br.inf.pucrio.actomatic.action.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import br.inf.pucrio.actomatic.MainActivity;
import br.inf.pucrio.actomatic.R;
import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.model.CommandArgument;
import br.inf.pucrio.actomatic.model.Notification;

public class AlertNotificationCommand extends NotificationCommand
{

	public AlertNotificationCommand(Notification argument)
	{
		super( argument );
	}

	@Override
	public boolean execute(Notification argument)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getObjectType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void update(ActionCommand<?> observable, CommandArgument argument)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final Activity activity)
	{
		Notification notification = this.getArgument();
		final String message = notification.getMessage();
		final String name = notification.getName();

		activity.runOnUiThread( new Runnable()
		{

			@SuppressLint("NewApi")
			public void run()
			{
				// Toast.makeText( activity, message, Toast.LENGTH_LONG
				// ).show();
				NotificationCompat.Builder mBuilder = new NotificationCompat.Builder( activity )
						.setSmallIcon( R.drawable.ic_launcher ).setContentTitle( name )
						.setContentText( message );
				// Creates an explicit intent for an Activity in your app
				Intent resultIntent = new Intent( activity, MainActivity.class );

				// The stack builder object will contain an artificial back
				// stack for the
				// started Activity.
				// This ensures that navigating backward from the Activity leads
				// out of
				// your application to the Home screen.
				TaskStackBuilder stackBuilder = TaskStackBuilder.create( activity );
				// Adds the back stack for the Intent (but not the Intent
				// itself)
				stackBuilder.addParentStack( MainActivity.class );
				// Adds the Intent that starts the Activity to the top of the
				// stack
				stackBuilder.addNextIntent( resultIntent );
				PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0,
						PendingIntent.FLAG_UPDATE_CURRENT );
				mBuilder.setContentIntent( resultPendingIntent );
				NotificationManager mNotificationManager = (NotificationManager) activity
						.getSystemService( Context.NOTIFICATION_SERVICE );
				// mId allows you to update the notification later on.
				mNotificationManager.notify( 0, mBuilder.build() );
			}

		} );
	}

}
