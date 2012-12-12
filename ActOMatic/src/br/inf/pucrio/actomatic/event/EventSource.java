package br.inf.pucrio.actomatic.event;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import br.inf.pucrio.actomatic.Observable;
import br.inf.pucrio.actomatic.model.Event;

public abstract class EventSource<T extends Event> implements Runnable, Observable<EventCommand<T>>
{
	private final Activity activity;

	private Long timeToWait;

	public EventSource(Activity activity)
	{
		observers = new ArrayList<EventCommand<T>>();
		this.activity = activity;
		setTimeToWait( Long.valueOf( 120000 ) );
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
					this.wait( getTimeToWait() );
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

	public Long getTimeToWait()
	{
		return timeToWait;
	}

	public void setTimeToWait(Long timeToWait)
	{
		this.timeToWait = timeToWait;
	}

	private final List<EventCommand<T>> observers;
}
