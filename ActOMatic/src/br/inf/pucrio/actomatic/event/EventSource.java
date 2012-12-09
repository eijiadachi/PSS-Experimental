package br.inf.pucrio.actomatic.event;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import br.inf.pucrio.actomatic.Observable;
import br.inf.pucrio.actomatic.model.Event;

public abstract class EventSource<T extends Event> implements Runnable, Observable<EventCommand<T>>
{
	private final Activity activity;

	private final Long timeToWait;

	public EventSource(Activity activity)
	{
		observers = new ArrayList<EventCommand<T>>();
		this.activity = activity;
		timeToWait = Long.valueOf( 5000 );
	}

	public void run()
	{
		synchronized (this)
		{
			while (true)
			{
				performInnerRun();

				try
				{
					this.wait( timeToWait );
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	protected abstract void performInnerRun();

	public void addObserver(EventCommand<T> observer)
	{
		getObservers().add( observer );
	}

	public boolean deleteObserver(EventCommand<T> observer)
	{
		boolean result = getObservers().remove( observer );
		return result;
	}

	public void notifyObservers(final Activity activity)
	{
		for (EventCommand<T> observer : getObservers())
		{
			observer.notifyObservers( activity );
		}
	}

	public List<EventCommand<T>> getObservers()
	{
		return observers;
	}

	public Activity getActivity()
	{
		return activity;
	}

	private final List<EventCommand<T>> observers;
}
