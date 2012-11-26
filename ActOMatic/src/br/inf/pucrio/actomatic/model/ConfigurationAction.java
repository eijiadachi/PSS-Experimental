package br.inf.pucrio.actomatic.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.cordova.api.LOG;
import org.json.JSONException;
import org.json.JSONStringer;

import br.inf.pucrio.actomatic.util.Util;

public class ConfigurationAction extends Action
{
	public enum ConfigurationType
	{
		SCREEN, VOLUME
	};

	private Double setting;

	private ConfigurationType type;

	public Double getSetting()
	{
		return setting;
	}

	public void setSetting(Double setting)
	{
		this.setting = setting;
	}

	public ConfigurationType getType()
	{
		return type;
	}

	public void setType(ConfigurationType type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return this.toJSON();
	}

	@Override
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

			jsonStringer.key( "objectType" ).value( "Configuration" );

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
}
