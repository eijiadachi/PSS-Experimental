package br.inf.pucrio.hotel.model;

import java.util.Date;

import br.inf.pucrio.hotel.util.HotelUtil;

public class Client extends Bean
{
	private String address;

	private Date birthday;

	private String cpf;

	private String name;

	private String phone;

	public String getAddress()
	{
		return address;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public String getBirthdayStr()
	{
		String dateStr = HotelUtil.getDateStr( getBirthday() );
		return dateStr;

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

	public String toHtml()
	{
		String html = String
				.format(
						"<ul class='client'><li>Código: %1$s</li><li>Nome: %2$s</li><li>CPF: %3$s</li><li>Endereço: %4$s</li><li>Telefone: %5$s</li><li>Data de Nascimento: %6$s</li></ul>",
						getId(), getName(), getCpf(), getAddress(), getPhone(), getBirthdayStr() );

		return html;
	}

	@Override
	public String toString()
	{
		String toString = String.format( "%s - %s - %s", getId(), name, cpf );
		return toString;
	}
}
