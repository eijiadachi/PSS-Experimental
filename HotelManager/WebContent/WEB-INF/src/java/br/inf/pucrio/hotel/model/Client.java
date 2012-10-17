package br.inf.pucrio.hotel.model;

import java.util.Date;

public class Client extends Bean
{
	private String address;

	private Date birthday;

	private String name;

	private String cpf;

	private String phone;

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

	@Override
	public String toString()
	{
		String toString = String.format( "%s - %s - %s", getId(), name, cpf );
		return toString;
	}
}
