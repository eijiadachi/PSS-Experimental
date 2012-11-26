package br.inf.pucrio.actomatic;

import org.json.JSONArray;
import org.json.JSONException;

import br.inf.pucrio.actomatic.dao.FactoryDAO;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.ConfigurationAction;
import br.inf.pucrio.actomatic.model.ConfigurationAction.ConfigurationType;
import br.inf.pucrio.actomatic.model.NotificationAction;
import br.inf.pucrio.actomatic.model.NotificationAction.NotificationType;

public class ActionPlugin extends AbstractActOMaticPlugin<Action>
{
	public ActionPlugin()
	{
		super( TAG );
	}

	public static final String TAG = "ActionPlugin";

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

			int counter = 0;

			String actionType = args.getString( counter++ );

			String idStr = args.getString( counter++ );

			String name = args.getString( counter++ );
			String description = args.getString( counter++ );

			if ("CONFIGURATION".equals( actionType ))
			{
				result = new ConfigurationAction();

				String settingStr = args.getString( counter++ );
				Double setting = Double.parseDouble( settingStr );
				( (ConfigurationAction) result ).setSetting( setting );

				String settingType = args.getString( counter++ );

				ConfigurationType type = ConfigurationType.valueOf( settingType );

				( (ConfigurationAction) result ).setType( type );
			}
			else
			{
				result = new NotificationAction();

				String sendTo = args.getString( counter++ );
				( (NotificationAction) result ).setSendTo( sendTo );

				String notificationTypeStr = args.getString( counter++ );

				NotificationType type = NotificationType.valueOf( notificationTypeStr );
				( (NotificationAction) result ).setType( type );

				String message = args.getString( counter++ );
				( (NotificationAction) result ).setMessage( message );
			}

			if (idStr != null && !idStr.isEmpty())
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
