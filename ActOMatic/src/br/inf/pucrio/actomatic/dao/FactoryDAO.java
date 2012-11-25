package br.inf.pucrio.actomatic.dao;

import br.inf.pucrio.actomatic.model.Action;

public final class FactoryDAO
{
	private static TransientDAO<Action> actionDAO;

	public static IAbstractDAO<Action> getActionDAOInstance()
	{
		if (actionDAO != null)
		{
			return actionDAO;
		}
		return new TransientDAO<Action>();
	}
}
