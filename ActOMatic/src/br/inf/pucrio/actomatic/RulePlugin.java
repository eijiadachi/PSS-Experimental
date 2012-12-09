package br.inf.pucrio.actomatic;

import org.json.JSONArray;
import org.json.JSONException;

import br.inf.pucrio.actomatic.dao.FactoryDAO;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.Event;
import br.inf.pucrio.actomatic.model.Rule;

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

			IAbstractDAO<Action> actionDAO = FactoryDAO.getActionDAOInstance();
			Action action = actionDAO.getById( actionId );

			IAbstractDAO<Event> eventDAO = FactoryDAO.getEventDAOInstance();
			Event event = eventDAO.getById( eventId );

			rule.setAction( action );
			rule.setEvent( event );

			if (ruleIdStr != null && !"".equals( ruleIdStr.trim() ))
			{
				Integer ruleId = Integer.parseInt( ruleIdStr );
				rule.setId( ruleId );
			}

			return rule;
		}
		catch (JSONException e)
		{
			throw new RuntimeException( e );
		}
	}

}
