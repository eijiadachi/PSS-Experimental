package br.inf.pucrio.actomatic.action;

import br.inf.pucrio.actomatic.MainActivity;
import br.inf.pucrio.actomatic.model.Configuration;

public class VolumeConfigurationCommand extends ActionCommand<Configuration>
{

	public VolumeConfigurationCommand(Configuration argument)
	{
		super( argument );
	}

	@Override
	public String getObjectType()
	{
		return "VolumeConfigurationCommand";
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
