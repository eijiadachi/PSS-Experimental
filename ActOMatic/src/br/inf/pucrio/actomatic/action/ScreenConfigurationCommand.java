package br.inf.pucrio.actomatic.action;

import br.inf.pucrio.actomatic.MainActivity;
import br.inf.pucrio.actomatic.model.Configuration;

public class ScreenConfigurationCommand extends ActionCommand<Configuration>
{

	public ScreenConfigurationCommand(Configuration argument)
	{
		super( argument );
	}

	@Override
	public String getObjectType()
	{
		return "ScreeConfigurationCommand";
	}

	@Override
	public boolean execute(Configuration argument)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void run()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MainActivity activity)
	{
		// TODO Auto-generated method stub

	}

}
