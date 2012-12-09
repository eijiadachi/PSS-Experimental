package br.inf.pucrio.actomatic.plugin;

import org.json.JSONArray;
import org.json.JSONException;

import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.action.ScreenConfigurationCommand;
import br.inf.pucrio.actomatic.action.VolumeConfigurationCommand;
import br.inf.pucrio.actomatic.action.notification.AlertNotificationCommand;
import br.inf.pucrio.actomatic.action.notification.EmailNotificationCommand;
import br.inf.pucrio.actomatic.action.notification.SmsNotificationCommand;
import br.inf.pucrio.actomatic.dao.FactoryDAO;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.model.Configuration;
import br.inf.pucrio.actomatic.model.Configuration.ConfigurationType;
import br.inf.pucrio.actomatic.model.Notification;
import br.inf.pucrio.actomatic.model.Notification.NotificationType;

public class ActionPlugin extends AbstractActOMaticPlugin<ActionCommand<?>>
{
	public ActionPlugin()
	{
		super( TAG );
	}

	public static final String TAG = "ActionPlugin";

	@Override
	protected IAbstractDAO<ActionCommand<?>> getDaoInstance()
	{
		IAbstractDAO<ActionCommand<?>> dao = FactoryDAO.getActionDAOInstance();
		return dao;
	}

	@Override
	protected ActionCommand<?> createEntity(JSONArray args)
	{
		try
		{
			ActionCommand<?> result = null;

			int counter = 0;

			String actionType = args.getString( counter++ );

			String idStr = args.getString( counter++ );

			String name = args.getString( counter++ );
			String description = args.getString( counter++ );

			if ("CONFIGURATION".equals( actionType ))
			{
				Configuration configuration = new Configuration();

				String settingStr = args.getString( counter++ );
				Double setting = Double.parseDouble( settingStr );
				configuration.setSetting( setting );

				String settingType = args.getString( counter++ );

				ConfigurationType type = ConfigurationType.valueOf( settingType );

				configuration.setType( type );

				switch (type)
				{
					case SCREEN:
						result = new ScreenConfigurationCommand( configuration );
						break;

					case VOLUME:
						result = new VolumeConfigurationCommand( configuration );
						break;

					default:
						break;
				}
			}
			else
			{
				Notification notification = new Notification();

				String sendTo = args.getString( counter++ );
				notification.setSendTo( sendTo );

				String notificationTypeStr = args.getString( counter++ );

				NotificationType type = NotificationType.valueOf( notificationTypeStr );
				notification.setType( type );

				String message = args.getString( counter++ );
				notification.setMessage( message );

				switch (type)
				{
					case SMS:
						result = new SmsNotificationCommand( notification );
						break;

					case EMAIL:
						result = new EmailNotificationCommand( notification );
						break;

					case ALERT:
						result = new AlertNotificationCommand( notification );
						break;

					default:
						break;
				}
			}

			if (idStr != null && !( idStr.trim().length() == 0 ))
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
