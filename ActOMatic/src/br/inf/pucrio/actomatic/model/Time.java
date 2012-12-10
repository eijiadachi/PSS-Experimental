package br.inf.pucrio.actomatic.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends Event
{
	private Date date;

	private String dateStr;

	private String time;

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
		this.setDateStr( date );
		this.setTime( date );
	}

	private void setTime(Date date2)
	{
		String timeStr = new SimpleDateFormat( "HH:mm" ).format( date2 );
		this.setTime( timeStr );
	}

	public String getDateStr()
	{
		return dateStr;
	}

	private void setDateStr(Date date)
	{
		String dateStr = new SimpleDateFormat( "dd-MM-yyyy" ).format( date );

		this.setDateStr( dateStr );
	}

	private void setDateStr(String dateStr)
	{
		this.dateStr = dateStr;
	}

	public String getTime()
	{
		return time;
	}

	private void setTime(String time)
	{
		this.time = time;
	}

}
