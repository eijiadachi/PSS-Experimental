package br.inf.pucrio.actomatic.model;

public class ConfigurationAction extends Action
{
	public enum ConfigurationType
	{
		SCREEN, VOLUME
	};

	private Double setting;

	private ConfigurationType type;

	public Double getSetting()
	{
		return setting;
	}

	public void setSetting(Double setting)
	{
		this.setting = setting;
	}

	public ConfigurationType getType()
	{
		return type;
	}

	public void setType(ConfigurationType type)
	{
		this.type = type;
	}

	@Override
	public String getObjectType()
	{
		return "ConfigurationAction";
	}
}
