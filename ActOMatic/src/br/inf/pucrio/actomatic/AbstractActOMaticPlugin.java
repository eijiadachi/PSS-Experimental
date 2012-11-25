package br.inf.pucrio.actomatic;

import java.util.List;
import java.util.Map;

import org.apache.cordova.api.LOG;
import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.model.Entity;
import br.inf.pucrio.actomatic.util.Util;

public abstract class AbstractActOMaticPlugin<T extends Entity> extends Plugin
{
	private final IAbstractDAO<T> dao;

	private final String tag;

	protected AbstractActOMaticPlugin(String tag)
	{
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
		T entity = createEntity( args );
		dao.delete( entity );

		return new PluginResult( PluginResult.Status.OK );
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
