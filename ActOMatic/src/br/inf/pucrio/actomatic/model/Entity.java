package br.inf.pucrio.actomatic.model;

public abstract class Entity
{
	private Integer id;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public abstract String toJSON();
}
