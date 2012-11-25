package br.inf.pucrio.actomatic;

import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.inf.pucrio.actomatic.dao.FactoryDAO;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.ConfigurationAction;
import br.inf.pucrio.actomatic.model.ConfigurationAction.ConfigurationType;

public class ActionPlugin extends AbstractActOMaticPlugin<Action>
{
	public ActionPlugin()
	{
		super( TAG );
	}

	public static final String TAG = "ActionPlugin";

	public String mProcessDataCallbackId;

	private void startProcessingData(final JSONArray args)
	{
		new Thread()
		{
			@Override
			public void run()
			{

			}
		}.start();

	}

	private void onProcessDataReadSuccess(double processedData)
	{
		Log.d( TAG, "onCardDataReadSuccess() called. Processed data: " + processedData );

		PluginResult result;

		try
		{
			JSONObject resultJSON = new JSONObject();
			resultJSON.put( "processedData", processedData );
			result = new PluginResult( Status.OK, resultJSON );
		}
		catch (JSONException jsonEx)
		{
			Log.e( TAG, "Got JSON Exception " + jsonEx.getMessage() );
			jsonEx.printStackTrace();
			result = new PluginResult( Status.JSON_EXCEPTION );
		}

		// callback to the javascript layer with our result
		// using the held callbackId via success(PluginResult result, String
		// callbackId)
		result.setKeepCallback( false );
		this.success( result, this.mProcessDataCallbackId );
	}

	@Override
	protected IAbstractDAO<Action> getDaoInstance()
	{
		IAbstractDAO<Action> dao = FactoryDAO.getActionDAOInstance();
		return dao;
	}

	@Override
	protected Action createEntity(JSONArray args)
	{
		try
		{
			Action result = null;

			String actionType = args.getString( 0 );
			String name = args.getString( 1 );
			String description = args.getString( 2 );

			if ("CONFIGURATION".equals( actionType ))
			{
				result = new ConfigurationAction();

				String settingStr = args.getString( 3 );
				Double setting = Double.parseDouble( settingStr );
				( (ConfigurationAction) result ).setSetting( setting );

				String settingType = args.getString( 4 );

				ConfigurationType type = ConfigurationAction.ConfigurationType
						.valueOf( settingType );

				( (ConfigurationAction) result ).setType( type );

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
