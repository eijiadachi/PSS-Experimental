package br.inf.pucrio.actomatic.model;

public abstract class Event extends Entity
{
	public Event()
	{

	}

	public Event(String name, String description)
	{
		super( name, description );
	}
}
