package br.inf.pucrio.actomatic.event.timer;

import java.util.Date;

import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.model.CommandArgument;
import br.inf.pucrio.actomatic.model.Time;

public class TimerCommand extends EventCommand<Time>
{
	public TimerCommand(Time argument)
	{
		super( argument );
	}

	public void run()
	{
		// TODO Auto-generated method stub

	}

	public void update(CommandArgument argument)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute(Time targetArgument)
	{
		Time argument = this.getArgument();

		Date date = argument.getDate();

		Date targetDate = targetArgument.getDate();

		return true;
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
