package br.inf.pucrio.actomatic.event;

import br.inf.pucrio.actomatic.model.Command;
import br.inf.pucrio.actomatic.model.Event;

public abstract class EventCommand<T extends Event> extends Command<T>
{
	public EventCommand(T argument)
	{
		super( argument );
	}
}
