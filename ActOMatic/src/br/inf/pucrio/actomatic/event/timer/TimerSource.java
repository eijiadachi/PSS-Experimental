package br.inf.pucrio.actomatic.event.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.inf.pucrio.actomatic.MainActivity;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.event.EventSource;
import br.inf.pucrio.actomatic.model.Time;

public class TimerSource extends EventSource<Time>
{

	private final MainActivity activity;

	public TimerSource(MainActivity activity)
	{
		this.activity = activity;
	}

	public void run()
	{
		synchronized (this)
		{
			while (true)
			{
				Calendar instance = Calendar.getInstance();
				Date date = instance.getTime();
				Time time = new Time( date );

				List<EventCommand<Time>> observers = getObservers();
				for (EventCommand<Time> eventCommand : observers)
				{
					boolean execute = eventCommand.execute( time );
					if (execute)
					{
						eventCommand.notifyObservers( activity );
					}
				}

				try
				{
					this.wait( 5000 );
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	public MainActivity getActivity()
	{
		return activity;
	}

}
