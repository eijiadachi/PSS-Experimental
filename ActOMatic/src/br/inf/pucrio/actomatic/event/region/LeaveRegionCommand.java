package br.inf.pucrio.actomatic.event.region;

import br.inf.pucrio.actomatic.model.Region;

public class LeaveRegionCommand extends RegionCommand
{

	public LeaveRegionCommand(Region region)
	{
		super( region );
	}

	public void run()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute(Region targetArgument)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getObjectType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void notifyObservers()
	{
		// TODO Auto-generated method stub

	}

}
