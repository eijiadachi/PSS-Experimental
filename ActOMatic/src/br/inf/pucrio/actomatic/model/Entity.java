package br.inf.pucrio.actomatic.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.cordova.api.LOG;
import org.json.JSONException;
import org.json.JSONStringer;

import br.inf.pucrio.actomatic.util.Util;

public abstract class Entity
{
	private Integer id;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String toJSON()
	{
		try
		{
			Map<String, Object> map = Util.describeEntity( this );

			Set<Entry<String, Object>> entrySet = map.entrySet();

			JSONStringer jsonStringer = new JSONStringer().object();
			for (Entry<String, Object> entry : entrySet)
			{
				String key = entry.getKey();
				Object value = entry.getValue();

				jsonStringer.key( key ).value( value );
			}

			jsonStringer.key( "objectType" ).value( getObjectType() );

			jsonStringer.endObject();
			String str = String.valueOf( jsonStringer );

			return str;
		}
		catch (JSONException e)
		{
			LOG.d( ConfigurationAction.class.getCanonicalName(), e.getMessage(), e );
			throw new RuntimeException( e );
		}
	}

	@Override
	public String toString()
	{
		return toJSON();
	}

	public abstract String getObjectType();
}
