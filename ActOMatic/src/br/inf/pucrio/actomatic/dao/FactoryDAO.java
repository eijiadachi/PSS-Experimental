package br.inf.pucrio.actomatic.dao;

import java.util.List;

import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.Event;
import br.inf.pucrio.actomatic.model.Rule;

public final class FactoryDAO
{
	private static TransientDAO<ActionCommand<?>> actionDAO;

	private static TransientDAO<EventCommand<?>> eventDAO;

	private static TransientDAO<Rule> ruleDAO;

	private static final boolean IS_TEST = true;

	static
	{

		actionDAO = new TransientDAO<ActionCommand<?>>();

		eventDAO = new TransientDAO<EventCommand<?>>();

		ruleDAO = new TransientDAO<Rule>();

		if (IS_TEST)
		{
			List<ActionCommand<? extends Action>> actions = MockDAO.createMockActions();
			actionDAO.addAll( actions );

			List<EventCommand<? extends Event>> events = MockDAO.createMockEvents();
			eventDAO.addAll( events );
		}
	}

	public static IAbstractDAO<ActionCommand<?>> getActionDAOInstance()
	{
		if (actionDAO != null)
		{
			return actionDAO;
		}
		return new TransientDAO<ActionCommand<?>>();
	}

	public static IAbstractDAO<EventCommand<?>> getEventDAOInstance()
	{
		if (eventDAO != null)
		{
			return eventDAO;
		}
		return new TransientDAO<EventCommand<?>>();
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
