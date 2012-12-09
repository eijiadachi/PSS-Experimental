package br.inf.pucrio.actomatic.plugin;

import java.util.List;
import java.util.Map;

import org.apache.cordova.api.LOG;
import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import br.inf.pucrio.actomatic.MainActivity;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.event.timer.TimerSource;
import br.inf.pucrio.actomatic.location.LocationTracker;
import br.inf.pucrio.actomatic.model.Entity;
import br.inf.pucrio.actomatic.util.Util;

public abstract class AbstractActOMaticPlugin<T extends Entity> extends Plugin
{
	private final IAbstractDAO<T> dao;

	private final String tag;

	protected LocationTracker getGpsTracker()
	{
		MainActivity activity = getActivity();
		LocationTracker gpsTracker = activity.getGpsTracker();
		return gpsTracker;
	}

	protected MainActivity getActivity()
	{
		MainActivity activity = (MainActivity) this.cordova;
		return activity;
	}

	protected void runOnUiThread(Runnable runnable)
	{
		MainActivity activity = getActivity();
		activity.runOnUiThread( runnable );
	}

	protected Context getContext()
	{
		MainActivity activity = getActivity();
		Context context = activity.getContext();
		return context;
	}

	protected TimerSource getTimerSource()
	{
		MainActivity activity = getActivity();
		TimerSource timerSource = activity.getTimerSource();
		return timerSource;
	}

	protected AbstractActOMaticPlugin(String tag)
	{
		super();

		this.dao = getDaoInstance();
		this.tag = tag;
	}

	protected abstract IAbstractDAO<T> getDaoInstance();

	private PluginResult add(JSONArray args)
	{
		T entity = createEntity( args );
		dao.add( entity );

		return new PluginResult( PluginResult.Status.OK );
	}

	private PluginResult remove(JSONArray args)
	{
		try
		{
			Integer id = args.getInt( 0 );
			boolean result = dao.delete( id );

			if (result)
			{
				return new PluginResult( PluginResult.Status.OK );
			}

			return new PluginResult( PluginResult.Status.ERROR, "It was not possible to delete." );
		}
		catch (JSONException e)
		{
			LOG.d( AbstractActOMaticPlugin.class.getCanonicalName(), e.getMessage(), e );
			throw new RuntimeException( e );
		}

	}

	private PluginResult update(JSONArray args)
	{
		T entity = createEntity( args );
		dao.update( entity );

		return new PluginResult( PluginResult.Status.OK );
	}

	private PluginResult getById(JSONArray args)
	{
		try
		{
			Integer id = args.getInt( 0 );
			T entity = dao.getById( id );

			Map<String, Object> entityDescribed = Util.describeEntity( entity );
			JSONObject object = new JSONObject( entityDescribed );

			return new PluginResult( PluginResult.Status.OK, object );
		}
		catch (JSONException e)
		{
			LOG.d( tag, "JSONException while performing getById", e );
			return new PluginResult( PluginResult.Status.ERROR, e.getMessage() );
		}

	}

	private PluginResult listAll()
	{
		List<T> listAll = dao.listAll();

		JSONArray array = new JSONArray( listAll );

		return new PluginResult( PluginResult.Status.OK, array );
	}

	protected abstract T createEntity(JSONArray args);

	@Override
	public PluginResult execute(String action, JSONArray args, String callbackId)
	{
		if ("add".equalsIgnoreCase( action ))
		{
			return add( args );
		}
		else if ("remove".equalsIgnoreCase( action ))
		{
			return remove( args );
		}
		else if ("update".equalsIgnoreCase( action ))
		{
			return update( args );
		}
		else if ("listAll".equalsIgnoreCase( action ))
		{
			return listAll();
		}
		else if ("getById".equalsIgnoreCase( action ))
		{
			return getById( args );
		}

		return new PluginResult( Status.INVALID_ACTION );
	}

	public String getTag()
	{
		return tag;
	}
}
