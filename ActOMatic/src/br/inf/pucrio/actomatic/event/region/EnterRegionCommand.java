package br.inf.pucrio.actomatic.event.region;

import br.inf.pucrio.actomatic.model.Region;

public class EnterRegionCommand extends RegionCommand
{
	public EnterRegionCommand(Region region)
	{
		super( region );
		setType( RegionEventType.ENTER );
	}

	@Override
	public boolean execute(Region argument)
	{
		return true;
	}

	@Override
	public String getObjectType()
	{
		return "EnterRegionCommand";
	}

	public void run()
	{
		// TODO Auto-generated method stub

	}

	public void notifyObservers()
	{
		// TODO Auto-generated method stub

	}

}
