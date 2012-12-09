package br.inf.pucrio.actomatic;

import org.apache.cordova.DroidGap;

import android.os.Bundle;
import android.view.Menu;
import br.inf.pucrio.actomatic.location.GPSTracker;

public class MainActivity extends DroidGap
{

	private GPSTracker gpsTracker;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		super.init();
		super.loadUrl( "file:///android_asset/www/index.html" );

		setGpsTracker( new GPSTracker( this ) );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate( R.menu.activity_main, menu );
		return true;
	}

	public GPSTracker getGpsTracker()
	{
		return gpsTracker;
	}

	public void setGpsTracker(GPSTracker gpsTracker)
	{
		this.gpsTracker = gpsTracker;
	}
}
