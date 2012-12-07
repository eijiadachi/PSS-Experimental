package br.inf.pucrio.actomatic;

import org.json.JSONArray;

import br.inf.pucrio.actomatic.dao.FactoryDAO;
import br.inf.pucrio.actomatic.dao.IAbstractDAO;
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
		// TODO Auto-generated method stub
		return null;
	}

}
