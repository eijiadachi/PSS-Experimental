package br.inf.pucrio.actomatic.model;

import java.util.Date;

public class Time extends Event
{
	private Date date;

	public Time(Date date)
	{
		this.setDate( date );
	}

	@Override
	public String getObjectType()
	{
		return "Time";
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

}
