package br.inf.pucrio.hotel.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.exception.HotelException;
import br.inf.pucrio.hotel.model.Client;

import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction extends ActionSupport
{

	public static final String MESSAGE = "Welcome.message";

	private static final long serialVersionUID = 1L;

	static
	{
		loadInitialValues();
	}

	private static void loadInitialClients()
	{
		String[] names = { "Reginaldo Farias", "Samanta Souza", "Augusto Santos", "Claudio Silva", "Mariano Santana" };
		String[] phones = { "(51) 96186617", "(21) 81534599", "(81) 92097644", "(48) 99764831", "(11) 94523456" };
		String[] cpfs = { "000.000.000-00", "000.000.000-01", "000.000.000-02", "000.000.000-03", "000.000.000-04" };
		String[] addrs = { "Rua 0", "Rua 1", "Rua 2", "Rua 3", "Rua 4" };
		String[] birthdays = { "01-01-80", "01-01-81", "01-01-82", "01-01-83", "01-01-84" };

		SimpleDateFormat formatter = new SimpleDateFormat( "dd-MM-yy" );

		try
		{
			for (int i = 0; i < names.length; i++)
			{
				Client client = new Client();
				client.setName( names[i] );
				client.setPhone( phones[i] );
				client.setCpf( cpfs[i] );
				client.setAddress( addrs[i] );
				client.setBirthday( formatter.parse( birthdays[i] ) );

				HotelManagerFacade.addClient( client );
			}
		}
		catch (ParseException e)
		{
			throw new HotelException( e );
		}

	}

	private static void loadInitialValues()
	{
		loadInitialClients();

	}

	private String message;

	@Override
	public String execute() throws Exception
	{
		setMessage( getText( MESSAGE ) );
		return SUCCESS;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
