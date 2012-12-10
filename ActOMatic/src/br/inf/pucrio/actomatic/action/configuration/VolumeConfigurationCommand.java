package br.inf.pucrio.actomatic.action.configuration;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import br.inf.pucrio.actomatic.action.ActionCommand;
import br.inf.pucrio.actomatic.model.Configuration;

public class VolumeConfigurationCommand extends ActionCommand<Configuration>
{

	public VolumeConfigurationCommand(Configuration argument)
	{
		super( argument );
	}

	@Override
	public String getObjectType()
	{
		return "VolumeConfigurationCommand";
	}

	@Override
	public void update(Activity activity)
	{
		AudioManager audio = (AudioManager) activity.getSystemService( Context.AUDIO_SERVICE );

		Configuration configuration = this.getArgument();

		Double setting = configuration.getSetting();

		int settingIntValue = setting.intValue();

		audio.setStreamVolume( AudioManager.STREAM_MUSIC, settingIntValue,
				AudioManager.FLAG_SHOW_UI );
	}

}
