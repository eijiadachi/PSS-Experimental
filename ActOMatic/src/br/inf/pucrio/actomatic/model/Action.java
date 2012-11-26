package br.inf.pucrio.actomatic.model;

public abstract class Action extends Entity
{

	public Action()
	{
		super();
	}

	public Action(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	private String name;

	private String description;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
