package br.inf.pucrio.actomatic.event.timer;

import java.util.Calendar;
import java.util.Date;

import br.inf.pucrio.actomatic.event.EventCommand;
import br.inf.pucrio.actomatic.model.Time;

public class TimerCommand extends EventCommand<Time>
{
	public TimerCommand(Time argument)
	{
		super( argument );
	}

	@Override
	public boolean execute(Time targetArgument)
	{
		Time argument = this.getArgument();

		Date registeredDate = argument.getDate();

		int[] valuesRegistered = getValues( registeredDate );

		Date currentDate = targetArgument.getDate();

		int[] valuesCurrent = getValues( currentDate );

		int size = valuesRegistered.length;

		for (int i = 0; i < size; i++)
		{
			int valueRegistered = valuesRegistered[i];
			int valueCurrent = valuesCurrent[i];
			if (valueRegistered != valueCurrent)
			{
				// return false;
			}
		}

		return true;
	}

	private int[] getValues(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( date );

		int year = calendar.get( Calendar.YEAR );
		int month = calendar.get( Calendar.MONTH );
		int day = calendar.get( Calendar.DAY_OF_MONTH );
		int hour = calendar.get( Calendar.HOUR );
		int minute = calendar.get( Calendar.MINUTE );

		int[] result = new int[] { year, month, day, hour, minute };

		return result;
	}

	@Override
	public String getObjectType()
	{
		return "TimerCommand";
	}

}
