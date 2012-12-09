package br.inf.pucrio.actomatic.event.region;

import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.model.Region;

public abstract class RegionCommand extends EventCommand<Region>
{
	public RegionCommand(Region region)
	{
		super( region );
	}
}
