package br.inf.pucrio.hotel.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception
	{
		setMessage( getText( MESSAGE ) );
		return SUCCESS;
	}

	public static final String MESSAGE = "Welcome.message";

	private String message;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
