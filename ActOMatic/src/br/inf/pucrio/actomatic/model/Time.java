package br.inf.pucrio.actomatic.model;

public class Time extends Event
{
	private String day;

	private String hour;

	public String getDay()
	{
		return day;
	}

	public void setDay(String day)
	{
		this.day = day;
	}

	public String getHour()
	{
		return hour;
	}

	public void setHour(String hour)
	{
		this.hour = hour;
	}

	@Override
	public String getObjectType()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
