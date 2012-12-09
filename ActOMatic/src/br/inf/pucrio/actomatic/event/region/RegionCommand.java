package br.inf.pucrio.actomatic.event.region;

import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.model.Region;

public abstract class RegionCommand extends EventCommand<Region>
{
	public RegionCommand(Region region)
	{
		super( region );
	}

	public RegionEventType getType()
	{
		return type;
	}

	protected void setType(RegionEventType type)
	{
		this.type = type;
	}

	public enum RegionEventType
	{
		ENTER, LEAVE
	}

	private RegionEventType type;
}
