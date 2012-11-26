package br.inf.pucrio.actomatic.dao;

import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.Event;

public final class FactoryDAO
{
	private static TransientDAO<Action> actionDAO;

	private static TransientDAO<Event> eventDAO;

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
}
