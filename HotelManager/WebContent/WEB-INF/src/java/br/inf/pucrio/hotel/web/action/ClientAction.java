package br.inf.pucrio.hotel.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.exception.HotelException;
import br.inf.pucrio.hotel.model.Client;

public class ClientAction extends HotelBaseAction<Client>
{
	private static final long serialVersionUID = 1L;

	private Client client;

	@Override
	public String add()
	{
		try
		{
			Client localClient = getClient();

			HotelManagerFacade.addClient( localClient );

			String successMsg = String.format( "Cliente '%s' cadastrado com sucesso.", localClient.toString() );

			addActionMessage( successMsg );

			return SUCCESS;
		}
		catch (HotelException e)
		{
			addActionError( e.getMessage() );
			return ERROR;
		}
	}

	public Client getClient()
	{
		return client;
	}

	private boolean isEmptyString(final String str)
	{
		return str == null || str.isEmpty();
	}

	private boolean isValidCpf(String cpf)
	{
		Pattern p = Pattern.compile( "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}" );

		Matcher matcher = p.matcher( cpf );

		boolean matches = matcher.matches();

		return matches;
	}

	@Override
	public String listAll()
	{
		List<Client> listAllClients = HotelManagerFacade.listAllClients();

		saveOnSession( HotelConstants.ALL_CLIENTS_ATTR, listAllClients );

		return SUCCESS;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	@Override
	public void validate()
	{
		Client localClient = getClient();
		if (localClient == null)
		{
			return;
		}

		String name = localClient.getName();

		if (isEmptyString( name ))
		{
			addFieldError( "client.name", "Nome é obrigatório" );
		}

		String cpf = localClient.getCpf();
		if (isEmptyString( cpf ))
		{
			addFieldError( "client.cpf", "CPF é obrigatório" );
		}
		else if (!isValidCpf( cpf ))
		{
			addFieldError( "client.cpf", "CPF deve estar no padrão ###.###.###-##, em que # é um dígito." );
		}

		Date birthday = localClient.getBirthday();

		Calendar birthdayCalendar = Calendar.getInstance();
		birthdayCalendar.setTime( birthday );

		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTimeInMillis( System.currentTimeMillis() );

		boolean isAfter = birthdayCalendar.after( currentCalendar );

		if (isAfter)
		{
			addFieldError( "client.birthday", "Data de Nascimento deve ser posterior a data de hoje." );
		}
	}
}
