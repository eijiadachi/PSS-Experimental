package br.inf.pucrio.actomatic.model;

import java.util.Date;

public class TimerEvent extends Event
{

	private Date date;

	private Date time;

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	@Override
	public String getObjectType()
	{
		return "TimerEvent";
	}

}
