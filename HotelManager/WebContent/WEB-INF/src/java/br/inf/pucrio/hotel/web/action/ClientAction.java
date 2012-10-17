package br.inf.pucrio.hotel.web.action;

import java.util.Date;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Client;

public class ClientAction extends HotelBaseAction<Client>
{
	private static final long serialVersionUID = 1L;

	private String address;

	private Date birthday;

	private String name;

	private String cpf;

	private String phone;

	@Override
	public String add()
	{
		HotelManagerFacade.addClient( name, cpf, birthday, address, phone );

		String successMsg = String.format( "Cliente '%s - %s' cadastrado com sucesso.", name, cpf );

		request.getSession().setAttribute( HotelConstants.SUCCESS_MSG_ATTR, successMsg );

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

	public String getCpf()
	{
		return cpf;
	}

	public String getName()
	{
		return name;
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

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
}
