package br.inf.pucrio.actomatic;

import org.apache.cordova.DroidGap;

import android.os.Bundle;
import android.view.Menu;
import br.inf.pucrio.actomatic.event.region.LocationTracker;
import br.inf.pucrio.actomatic.event.region.RegionSource;
import br.inf.pucrio.actomatic.event.timer.TimerSource;

public class MainActivity extends DroidGap
{

	private LocationTracker gpsTracker;

	private TimerSource timerSource;

	private RegionSource regionSource;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		super.init();
		super.loadUrl( "file:///android_asset/www/index.html" );

		setGpsTracker( new LocationTracker( this ) );

		setTimerSource( new TimerSource( this ) );
		new Thread( getTimerSource() ).start();

		setRegionSource( new RegionSource( this ) );
		new Thread( getRegionSource() ).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate( R.menu.activity_main, menu );
		return true;
	}

	public LocationTracker getGpsTracker()
	{
		return gpsTracker;
	}

	public void setGpsTracker(LocationTracker gpsTracker)
	{
		this.gpsTracker = gpsTracker;
	}

	public TimerSource getTimerSource()
	{
		return timerSource;
	}

	public void setTimerSource(TimerSource timerSource)
	{
		this.timerSource = timerSource;
	}

	public RegionSource getRegionSource()
	{
		return regionSource;
	}

	public void setRegionSource(RegionSource regionSource)
	{
		this.regionSource = regionSource;
	}
}
