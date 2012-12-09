package br.inf.pucrio.actomatic.model;

import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.event.EventCommand;

public class Rule extends Entity
{
	private ActionCommand<?> action;

	private EventCommand<?> event;

	public ActionCommand<?> getAction()
	{
		return action;
	}

	public void setAction(ActionCommand<?> action)
	{
		this.action = action;
	}

	public EventCommand<?> getEvent()
	{
		return event;
	}

	public void setEvent(EventCommand<?> event)
	{
		this.event = event;
	}

	@Override
	public String getObjectType()
	{
		return "Rule";
	}

}
