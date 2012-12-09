package br.inf.pucrio.actomatic.event;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import br.inf.pucrio.actomatic.Observable;
import br.inf.pucrio.actomatic.Observer;
import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.model.Command;
import br.inf.pucrio.actomatic.model.CommandArgument;
import br.inf.pucrio.actomatic.model.Event;

public abstract class EventCommand<T extends Event> extends Command<T> implements
		Observable<ActionCommand<?>>, Observer<CommandArgument>
{
	public EventCommand(T argument)
	{
		super( argument );
		observers = new ArrayList<ActionCommand<?>>();
	}

	public void addObserver(ActionCommand<?> observer)
	{
		getObservers().add( observer );

	}

	public boolean deleteObserver(ActionCommand<?> observer)
	{
		boolean result = getObservers().remove( observer );
		return result;
	}

	public void notifyObservers(Activity activity)
	{
		for (ActionCommand<?> observer : getObservers())
		{
			observer.update( activity );
		}
	}

	public List<ActionCommand<?>> getObservers()
	{
		return observers;
	}

	private final List<ActionCommand<?>> observers;
}
