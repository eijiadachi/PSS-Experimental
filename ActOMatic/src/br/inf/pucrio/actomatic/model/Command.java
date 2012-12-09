package br.inf.pucrio.actomatic.model;

public abstract class Command<T extends CommandArgument> extends Entity
{
	private T argument;

	public Command(T argument)
	{
		this.argument = argument;
	}

	public abstract boolean execute(T targetArgument);

	public Command(String name, String description)
	{
		super( name, description );
	}

	public T getArgument()
	{
		return argument;
	}

	public void setArgument(T argument)
	{
		this.argument = argument;
	}
}
