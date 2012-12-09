package br.inf.pucrio.actomatic.action.notification;

import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.model.Notification;

public abstract class NotificationCommand extends ActionCommand<Notification>
{
	public NotificationCommand(Notification argument)
	{
		super( argument );
	}
}
