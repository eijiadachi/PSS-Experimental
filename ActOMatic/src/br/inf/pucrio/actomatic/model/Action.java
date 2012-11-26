package br.inf.pucrio.actomatic.model;

public abstract class Action extends Entity
{
	public Action()
	{
		super();
	}

	public Action(String name, String description)
	{
		super( name, description );
	}
}
