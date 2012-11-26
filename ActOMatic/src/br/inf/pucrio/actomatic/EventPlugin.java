package br.inf.pucrio.actomatic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import br.inf.pucrio.actomatic.dao.FactoryDAO;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.model.Event;
import br.inf.pucrio.actomatic.model.RegionEvent;
import br.inf.pucrio.actomatic.model.TimerEvent;

public class EventPlugin extends AbstractActOMaticPlugin<Event>
{

	private static String TAG = "EventPlugin";

	public EventPlugin()
	{
		super( TAG );
	}

	@Override
	protected IAbstractDAO<Event> getDaoInstance()
	{
		IAbstractDAO<Event> eventDAOInstance = FactoryDAO.getEventDAOInstance();
		return eventDAOInstance;
	}

	@Override
	protected Event createEntity(JSONArray args)
	{
		try
		{
			Event result = null;

			int counter = 0;

			String actionType = args.getString( counter++ );

			String idStr = args.getString( counter++ );

			String name = args.getString( counter++ );
			String description = args.getString( counter++ );

			if ("REGION".equals( actionType ))
			{
				result = new RegionEvent();

				Double latitude = args.getDouble( counter++ );
				( (RegionEvent) result ).setLatitude( latitude );

				Double longitude = args.getDouble( counter++ );
				( (RegionEvent) result ).setLongitude( longitude );

				Double radius = args.getDouble( counter++ );
				( (RegionEvent) result ).setRadius( radius );
			}
			else
			{
				result = new TimerEvent();

				String dateStr = args.getString( counter++ );
				Date date = new SimpleDateFormat().parse( dateStr );
				( (TimerEvent) result ).setDate( date );

				String timeStr = args.getString( counter++ );
				Date time = new SimpleDateFormat().parse( timeStr );
				( (TimerEvent) result ).setTime( time );
			}

			if (idStr != null && !idStr.isEmpty())
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
