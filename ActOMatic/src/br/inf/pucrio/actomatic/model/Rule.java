package br.inf.pucrio.actomatic.model;

public class Rule extends Entity
{
	private Action action;

	private Event event;

	public Action getAction()
	{
		return action;
	}

	public void setAction(Action action)
	{
		this.action = action;
	}

	public Event getEvent()
	{
		return event;
	}

	public void setEvent(Event event)
	{
		this.event = event;
	}

	@Override
	public String getObjectType()
	{
		return "Rule";
	}

}
