package br.inf.pucrio.actomatic.action.notification;

import android.app.Activity;
import br.inf.pucrio.actomatic.model.Notification;

public class SmsNotificationCommand extends NotificationCommand
{
	public SmsNotificationCommand(Notification argument)
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

	public void run()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Activity activity)
	{
		// TODO Auto-generated method stub

	}

}
