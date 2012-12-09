package br.inf.pucrio.actomatic.action.notification;

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

}
