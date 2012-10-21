package br.inf.pucrio.hotel.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.exception.HotelException;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Client;
import br.inf.pucrio.hotel.model.Room;
import br.inf.pucrio.hotel.model.Room.RoomType;

import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction extends ActionSupport
{

	public static final String MESSAGE = "Welcome.message";

	private static final long serialVersionUID = 1L;

	static
	{
		loadInitialValues();
	}

	private static void addRoom(RoomType type, Integer quantity)
	{
		for (int i = 0; i < quantity; i++)
		{
			HotelManagerFacade.addRoom( new Room( type ) );
		}
	}

	private static Date getDateFromToday(Integer shift)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add( Calendar.DAY_OF_MONTH, shift );
		Date time = calendar.getTime();
		return time;
	}

	private static void loadInitialBookings()
	{
		Client client103 = HotelManagerFacade.getClientById( 103 );
		Room room10 = HotelManagerFacade.getRoomById( 10 );

		Date fiveDaysAfterToday = getDateFromToday( 5 );
		Date sevenDaysAfterToday = getDateFromToday( 7 );

		Booking booking = new Booking();
		booking.setClient( client103 );
		booking.setRoom( room10 );
		booking.setGuests( 2 );
		booking.setCheckin( fiveDaysAfterToday );
		booking.setCheckout( sevenDaysAfterToday );

		Client client102 = HotelManagerFacade.getClientById( 102 );
		Room room9 = HotelManagerFacade.getRoomById( 9 );

		Date today = getDateFromToday( 0 );

		Booking booking2 = new Booking();
		booking2.setClient( client102 );
		booking2.setRoom( room9 );
		booking2.setGuests( 2 );
		booking2.setCheckin( today );
		booking2.setCheckout( sevenDaysAfterToday );

		HotelManagerFacade.addBooking( booking );
		HotelManagerFacade.addBooking( booking2 );

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

	private static void loadInitialRooms()
	{
		addRoom( RoomType.STANDARD, 2 );
		addRoom( RoomType.WOODLAND, 3 );
		addRoom( RoomType.VALLEY, 3 );
		addRoom( RoomType.SUITE, 2 );

	}

	private static void loadInitialValues()
	{
		loadInitialClients();
		loadInitialRooms();
		loadtInitialStays();
		loadInitialBookings();
	}

	private static void loadtInitialStays()
	{
		Client client104 = HotelManagerFacade.getClientById( 104 );
		Client client101 = HotelManagerFacade.getClientById( 101 );
		Room room1 = HotelManagerFacade.getRoomById( 1 );
		Room room4 = HotelManagerFacade.getRoomById( 4 );

		Date today = getDateFromToday( 0 );
		Date twoDaysAfterToday = getDateFromToday( 2 );
		Date yesterday = getDateFromToday( -1 );
		Date threeDaysAfterToday = getDateFromToday( 3 );

		Booking stay1 = new Booking();
		stay1.setClient( client104 );
		stay1.setRoom( room1 );
		stay1.setGuests( 3 );
		stay1.setCheckin( today );
		stay1.setCheckout( twoDaysAfterToday );

		Booking stay2 = new Booking();
		stay2.setClient( client101 );
		stay2.setRoom( room4 );
		stay2.setGuests( 4 );
		stay2.setCheckin( yesterday );
		stay2.setCheckout( threeDaysAfterToday );

		HotelManagerFacade.addStay( stay1 );
		HotelManagerFacade.addStay( stay2 );

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
