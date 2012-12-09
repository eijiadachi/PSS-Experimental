package br.inf.pucrio.actomatic.plugin;

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

			String actionType = args.getString( counter++ );

			String idStr = args.getString( counter++ );

			String name = args.getString( counter++ );
			String description = args.getString( counter++ );

			String regionEventType = args.getString( counter++ );

			if ("REGION".equals( actionType ))
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
				String day = args.getString( counter++ );

				String hour = args.getString( counter++ );

				Time time = new Time();
				time.setDay( day );
				time.setHour( hour );

				result = new TimerCommand( time );

			}

			if (idStr != null && idStr.trim().length() == 0)
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
	}
}
