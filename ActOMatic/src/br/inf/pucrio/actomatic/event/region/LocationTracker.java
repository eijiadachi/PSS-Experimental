package br.inf.pucrio.actomatic.event.region;

import org.apache.cordova.api.LOG;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class LocationTracker extends Service implements LocationListener
{
	private final Context mContext;

	private Location location;

	private double latitude;

	private double longitude;

	public LocationTracker(Context context)
	{
		this.mContext = context;
		getLocation();
	}

	public void updateLocation()
	{
		LocationManager locationManager = (LocationManager) mContext
				.getSystemService( LOCATION_SERVICE );

		// getting GPS status
		boolean isGPSEnabled = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );

		// getting network status
		boolean isNetworkEnabled = locationManager
				.isProviderEnabled( LocationManager.NETWORK_PROVIDER );

		final String provider;
		if (isGPSEnabled)
		{
			provider = LocationManager.GPS_PROVIDER;
		}
		else if (isNetworkEnabled)
		{
			provider = LocationManager.NETWORK_PROVIDER;
		}
		else
		{
			LOG.d( "GPSTracker", "No network provider enabled." );
			return;
		}

		locationManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER,
				MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this );

		location = locationManager.getLastKnownLocation( provider );
		if (location != null)
		{
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}
	}

	public Location getLocation()
	{
		updateLocation();
		return location;
	}

	public double getLatitude()
	{
		if (location != null)
		{
			latitude = location.getLatitude();
		}

		return latitude;
	}

	public double getLongitude()
	{
		if (location != null)
		{
			longitude = location.getLongitude();
		}

		return longitude;
	}

	public void onLocationChanged(Location location)
	{

		// TODO Auto-generated method stub

	}

	public void registerCommand()
	{

	}

	public void onProviderDisabled(String provider)
	{
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider)
	{
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute

}
