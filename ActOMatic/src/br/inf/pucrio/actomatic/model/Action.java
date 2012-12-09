package br.inf.pucrio.actomatic.model;

public abstract class Action extends Entity implements CommandArgument
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
