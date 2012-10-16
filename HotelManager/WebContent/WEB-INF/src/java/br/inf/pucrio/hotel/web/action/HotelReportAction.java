package br.inf.pucrio.hotel.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class HotelReportAction extends ActionSupport implements ServletRequestAware
{

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	public String execute()
	{
		request.setAttribute( "eiji", "adachi" );

		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.request = arg0;

	}

}
