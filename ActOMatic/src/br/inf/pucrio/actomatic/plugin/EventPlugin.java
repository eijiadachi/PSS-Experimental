package br.inf.pucrio.actomatic.plugin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import br.inf.pucrio.actomatic.dao.FactoryDAO;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.event.region.EnterRegionCommand;
import br.inf.pucrio.actomatic.event.region.LeaveRegionCommand;
import br.inf.pucrio.actomatic.event.timer.TimerCommand;
import br.inf.pucrio.actomatic.model.Region;
import br.inf.pucrio.actomatic.model.Time;

public class EventPlugin extends AbstractActOMaticPlugin<EventCommand<?>>
{

	private static String TAG = "EventPlugin";

	public EventPlugin()
	{
		super( TAG );
	}

	@Override
	protected IAbstractDAO<EventCommand<?>> getDaoInstance()
	{
		IAbstractDAO<EventCommand<?>> eventDAOInstance = FactoryDAO.getEventDAOInstance();
		return eventDAOInstance;
	}

	@Override
	protected EventCommand<?> createEntity(JSONArray args)
	{
		try
		{
			EventCommand<?> result = null;

			int counter = 0;

			String eventType = args.getString( counter++ );
			String regionEventType = args.getString( counter++ );
			String idStr = args.getString( counter++ );
			String name = args.getString( counter++ );
			String description = args.getString( counter++ );

			if ("REGION".equals( eventType ))
			{
				Double latitude = args.getDouble( counter++ );

				Double longitude = args.getDouble( counter++ );

				Double radius = args.getDouble( counter++ );

				Region region = new Region();
				region.setDescription( description );
				region.setLatitude( latitude );
				region.setLongitude( longitude );
				region.setRadius( radius );

				if ("Enter".equalsIgnoreCase( regionEventType ))
				{
					result = new EnterRegionCommand( region );
				}
				else
				{
					result = new LeaveRegionCommand( region );
				}
			}
			else
			{
				String dateStr = args.getString( counter++ );
				Date date = new SimpleDateFormat( "dd-MM-yyyy" ).parse( dateStr );
				Calendar dateCalendar = Calendar.getInstance();
				dateCalendar.setTime( date );
				dateCalendar.set( Calendar.HOUR, 0 );
				dateCalendar.set( Calendar.MINUTE, 0 );

				String hourStr = args.getString( counter++ );
				Date hour = new SimpleDateFormat( "HH:mm" ).parse( hourStr );
				Calendar hourCalendar = Calendar.getInstance();
				hourCalendar.setTime( hour );

				int amPm = hourCalendar.get( Calendar.AM_PM );

				int hourInt = hourCalendar.get( Calendar.HOUR );

				if (amPm == Calendar.PM)
				{
					hourInt += 12;
				}

				dateCalendar.add( Calendar.HOUR, hourInt );

				int minuteInt = hourCalendar.get( Calendar.MINUTE );
				dateCalendar.add( Calendar.MINUTE, minuteInt );

				Time time = new Time( dateCalendar.getTime() );

				result = new TimerCommand( time );
			}

			if (idStr != null && !"".equals( idStr.trim() ))
			{
				Integer id = Integer.parseInt( idStr );
				result.setId( id );
			}

			result.setName( name );
			result.setDescription( description );

			return result;
		}
		catch (JSONException e)
		{
			throw new RuntimeException( e );
		}
		catch (ParseException e)
		{
			throw new RuntimeException( e );
		}
	}
}
