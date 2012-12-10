package br.inf.pucrio.actomatic.event.region;

import br.inf.pucrio.actomatic.model.Region;

public class LeaveRegionCommand extends RegionCommand
{

	public LeaveRegionCommand(Region region)
	{
		super( region );
		setType( RegionEventType.LEAVE );
	}

	@Override
	public boolean execute(Region targetArgument)
	{
		return true;
	}

	@Override
	public String getObjectType()
	{
		return "LeaveRegionCommand";
	}
}
