package br.inf.pucrio.actomatic.action.configuration;

import android.app.Activity;
import android.view.WindowManager;
import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.model.Configuration;

public class ScreenConfigurationCommand extends ActionCommand<Configuration>
{

	public ScreenConfigurationCommand(Configuration argument)
	{
		super( argument );
	}

	@Override
	public String getObjectType()
	{
		return "ScreeConfigurationCommand";
	}

	@Override
	public void update(final Activity activity)
	{
		Configuration configuration = this.getArgument();
		final Double setting = configuration.getSetting();

		Runnable run = new Runnable()
		{

			public void run()
			{
				WindowManager.LayoutParams layout = activity.getWindow().getAttributes();
				layout.screenBrightness = setting.floatValue();
				activity.getWindow().setAttributes( layout );
			}
		};

		activity.runOnUiThread( run );

	}
}
