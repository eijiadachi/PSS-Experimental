package br.inf.pucrio.actomatic.model;

public class Notification extends Action
{

	@Override
	public String getObjectType()
	{
		return "Notification";
	}

	public enum NotificationType
	{
		ALERT, EMAIL, SMS
	}

	private String message;

	private String sendTo;

	private NotificationType type;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getSendTo()
	{
		return sendTo;
	}

	public void setSendTo(String sendTo)
	{
		this.sendTo = sendTo;
	}

	public NotificationType getType()
	{
		return type;
	}

	public void setType(NotificationType type)
	{
		this.type = type;
	}

}
