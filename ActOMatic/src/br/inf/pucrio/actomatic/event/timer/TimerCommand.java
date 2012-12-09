package br.inf.pucrio.actomatic.event.timer;

import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.model.Time;

public class TimerCommand extends EventCommand<Time>
{

	public TimerCommand(Time argument)
	{
		super( argument );
	}

	@Override
	public boolean execute(Time argument)
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

}
