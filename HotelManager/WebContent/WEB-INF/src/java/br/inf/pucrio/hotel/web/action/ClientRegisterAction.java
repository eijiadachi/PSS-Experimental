package br.inf.pucrio.hotel.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ClientRegisterAction extends ActionSupport implements ServletRequestAware
{
	private static final long serialVersionUID = 1L;

	private String address;

	private Date birthday;

	private String clientName;

	private String cpf;

	private String phone;

	private HttpServletRequest request;

	@Override
	public String execute()
	{
		request.getSession().setAttribute( "success_msg",
				String.format( "Cliente '%s' cadastrado com sucesso.", clientName ) );

		return SUCCESS;
	}

	public String getAddress()
	{
		return address;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public String getClientName()
	{
		return clientName;
	}

	public String getCpf()
	{
		return cpf;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.request = arg0;
	}

}
