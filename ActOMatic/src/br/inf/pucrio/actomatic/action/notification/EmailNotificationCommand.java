package br.inf.pucrio.actomatic.action.notification;

import br.inf.pucrio.actomatic.MainActivity;
import br.inf.pucrio.actomatic.model.Notification;

public class EmailNotificationCommand extends NotificationCommand
{

	public EmailNotificationCommand(Notification argument)
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
	public void update(MainActivity activity)
	{
		// TODO Auto-generated method stub

	}

}
