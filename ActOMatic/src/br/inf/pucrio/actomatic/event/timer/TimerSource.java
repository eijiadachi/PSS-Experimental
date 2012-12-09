package br.inf.pucrio.actomatic.event.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.event.EventSource;
import br.inf.pucrio.actomatic.model.Time;

public class TimerSource extends EventSource<Time>
{
	public TimerSource(Activity activity)
	{
		super( activity );
	}

	@Override
	protected void performInnerRun()
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
				eventCommand.notifyObservers( getActivity() );
			}
		}

	}
}
