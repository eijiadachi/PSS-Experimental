package br.inf.pucrio.hotel.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.exception.HotelException;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Client;
import br.inf.pucrio.hotel.model.ClientSearchResult;

public class ClientAction extends HotelBaseAction<Client>
{
	private static final long serialVersionUID = 1L;

	private Client client;

	private Integer id;

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

	public Integer getId()
	{
		return id;
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

	@Override
	public String search()
	{
		Integer clientId = getId();

		Client client = HotelManagerFacade.getClientById( clientId );

		List<Booking> bookings = HotelManagerFacade.getBookingsOfClient( clientId );

		ClientSearchResult result = new ClientSearchResult();

		result.setClient( client );
		result.setBookings( bookings );

		saveOnSession( HotelConstants.RESULT_CLIENT_ATTR, result );

		return SUCCESS;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public void validate()
	{
		Client localClient = getClient();
		if (localClient == null)
		{
			if (getId() == null)
			{
				addFieldError( "id", "C—digo do Cliente Ž obrigat—rio." );
			}
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

		if (birthday != null)
		{
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
}
