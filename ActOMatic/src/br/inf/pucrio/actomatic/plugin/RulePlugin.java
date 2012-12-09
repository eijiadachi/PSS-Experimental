package br.inf.pucrio.actomatic.plugin;

import org.json.JSONArray;
import org.json.JSONException;

import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.dao.FactoryDAO;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.event.region.RegionCommand;
import br.inf.pucrio.actomatic.event.timer.TimerCommand;
import br.inf.pucrio.actomatic.model.Rule;
import br.inf.pucrio.actomatic.model.Time;

public class RulePlugin extends AbstractActOMaticPlugin<Rule>
{

	private static String TAG = "RulePlugin";

	public RulePlugin()
	{
		super( TAG );
	}

	@Override
	protected IAbstractDAO<Rule> getDaoInstance()
	{
		IAbstractDAO<Rule> ruleDAO = FactoryDAO.getRuleDAOInstance();
		return ruleDAO;
	}

	@Override
	protected Rule createEntity(JSONArray args)
	{

		try
		{
			int counter = 0;

			String ruleIdStr = args.getString( counter++ );

			String name = args.getString( counter++ );

			String description = args.getString( counter++ );

			Integer eventId = args.getInt( counter++ );

			Integer actionId = args.getInt( counter++ );

			Rule rule = new Rule();
			rule.setName( name );
			rule.setDescription( description );

			IAbstractDAO<ActionCommand<?>> actionDAO = FactoryDAO.getActionDAOInstance();
			ActionCommand<?> action = actionDAO.getById( actionId );

			IAbstractDAO<EventCommand<?>> eventDAO = FactoryDAO.getEventDAOInstance();
			EventCommand<?> event = eventDAO.getById( eventId );

			event.addObserver( action );

			rule.setAction( action );
			rule.setEvent( event );

			if (ruleIdStr != null && !"".equals( ruleIdStr.trim() ))
			{
				Integer ruleId = Integer.parseInt( ruleIdStr );
				rule.setId( ruleId );
			}

			if (event instanceof RegionCommand)
			{

			}
			else if (event instanceof TimerCommand)
			{
				EventCommand<Time> timerEventCommand = (EventCommand<Time>) event;
				getTimerSource().addObserver( timerEventCommand );
			}

			return rule;
		}
		catch (JSONException e)
		{
			throw new RuntimeException( e );
		}
	}
}
