package br.inf.pucrio.actomatic.model;

public abstract class Event extends Entity implements CommandArgument
{
	public Event()
	{
		super();
	}

	public Event(String name, String description)
	{
		super( name, description );
	}
}
