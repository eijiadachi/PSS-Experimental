package br.inf.pucrio.actomatic.dao;

import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.Event;
import br.inf.pucrio.actomatic.model.Rule;

public final class FactoryDAO
{
	private static TransientDAO<Action> actionDAO;

	private static TransientDAO<Event> eventDAO;

	private static TransientDAO<Rule> ruleDAO;

	static
	{
		actionDAO = new TransientDAO<Action>();
		eventDAO = new TransientDAO<Event>();
		ruleDAO = new TransientDAO<Rule>();
	}

	public static IAbstractDAO<Action> getActionDAOInstance()
	{
		if (actionDAO != null)
		{
			return actionDAO;
		}
		return new TransientDAO<Action>();
	}

	public static IAbstractDAO<Event> getEventDAOInstance()
	{
		if (eventDAO != null)
		{
			return eventDAO;
		}
		return new TransientDAO<Event>();
	}

	public static IAbstractDAO<Rule> getRuleDAOInstance()
	{
		if (ruleDAO != null)
		{
			return ruleDAO;
		}
		return new TransientDAO<Rule>();
	}
}
