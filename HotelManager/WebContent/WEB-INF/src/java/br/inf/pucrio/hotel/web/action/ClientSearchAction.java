package br.inf.pucrio.hotel.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Client;

import com.opensymphony.xwork2.ActionSupport;

public class ClientSearchAction extends ActionSupport implements ServletRequestAware
{
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	public String execute()
	{
		List<Client> allClients = HotelManagerFacade.listAllClients();

		request.getSession().setAttribute( "all_clients", allClients );

		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.request = arg0;

	}
}
