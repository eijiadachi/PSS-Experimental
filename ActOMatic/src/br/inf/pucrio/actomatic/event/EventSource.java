package br.inf.pucrio.actomatic.event;

import java.util.ArrayList;
import java.util.List;

import br.inf.pucrio.actomatic.Observable;
import br.inf.pucrio.actomatic.model.Event;

public abstract class EventSource<T extends Event> implements Runnable, Observable<EventCommand<T>>
{

	public EventSource()
	{
		observers = new ArrayList<EventCommand<T>>();
	}

	public void addObserver(EventCommand<T> observer)
	{
		getObservers().add( observer );
	}

	public boolean deleteObserver(EventCommand<T> observer)
	{
		boolean result = getObservers().remove( observer );
		return result;
	}

	public void notifyObservers()
	{
		for (EventCommand<T> observer : getObservers())
		{
			// observer.run();
		}
	}

	public List<EventCommand<T>> getObservers()
	{
		return observers;
	}

	private final List<EventCommand<T>> observers;
}
