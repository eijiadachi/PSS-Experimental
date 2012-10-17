package br.inf.pucrio.hotel.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import br.inf.pucrio.hotel.model.Bean;

import com.opensymphony.xwork2.ActionSupport;

public abstract class HotelBaseAction<T extends Bean> extends ActionSupport implements ServletRequestAware
{
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;

	public abstract String add();

	public abstract String listAll();

	public void saveOnRequest(final String attributeKey, final Object attributeValue)
	{
		request.setAttribute( attributeKey, attributeValue );
	}

	public void saveOnSession(final String attributeKey, final Object attributeValue)
	{
		request.getSession().setAttribute( attributeKey, attributeValue );
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.request = arg0;
	}
}
