package br.inf.pucrio.actomatic.event.region;

import java.util.List;

import android.content.Context;
import android.location.Location;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.event.EventSource;
import br.inf.pucrio.actomatic.location.LocationTracker;
import br.inf.pucrio.actomatic.model.Region;

public class RegionSource extends EventSource<Region>
{
	LocationTracker tracker;

	public RegionSource(Context context)
	{
		tracker = new LocationTracker( context );
	}

	public void run()
	{
		while (true)
		{
			Location location = tracker.getLocation();

			List<EventCommand<Region>> observers = getObservers();
			for (EventCommand<Region> eventCommand : observers)
			{
				Region region = new Region();
				region.setLatitude( location.getLatitude() );
				region.setLongitude( location.getLongitude() );
				region.setRadius( 10.0 );
				boolean result = eventCommand.execute( region );
				if (result)
				{
					eventCommand.notifyObservers();
				}
			}
		}
	}
}
