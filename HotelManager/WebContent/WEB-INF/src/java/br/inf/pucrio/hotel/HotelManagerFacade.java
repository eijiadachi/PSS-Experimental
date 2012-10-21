package br.inf.pucrio.hotel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.inf.pucrio.hotel.dao.BookingDAO;
import br.inf.pucrio.hotel.dao.ClientDAO;
import br.inf.pucrio.hotel.dao.RoomDAO;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Booking.Status;
import br.inf.pucrio.hotel.model.Client;
import br.inf.pucrio.hotel.model.Room;
import br.inf.pucrio.hotel.util.HotelUtil;

public class HotelManagerFacade
{
	private static BookingDAO bookingDAO;

	private static ClientDAO clientDAO;

	private static Map<Integer, Integer> guestsAmountPerMonth;

	private static Map<Integer, Float> incomingPerMonth;

	private static RoomDAO roomDAO;

	private static BookingDAO stayDAO;

	static
	{
		bookingDAO = new BookingDAO();
		clientDAO = new ClientDAO();
		roomDAO = new RoomDAO();
		stayDAO = new BookingDAO();

		guestsAmountPerMonth = new TreeMap<Integer, Integer>();

		incomingPerMonth = new TreeMap<Integer, Float>();
	}

	public static void addBooking(Booking booking)
	{
		booking.setStatus( Status.RESERVED );
		bookingDAO.add( booking );
	}

	public static void addClient(Client client)
	{
		clientDAO.add( client );
	}

	public static void addRoom(Room room)
	{
		roomDAO.add( room );
	}

	public static void addStay(Booking stay)
	{
		stay.setStatus( Status.OCCUPIED );
		stayDAO.add( stay );
	}

	public static Booking getBookingById(Integer bookingId)
	{
		Booking booking = bookingDAO.getById( bookingId );

		return booking;
	}

	public static List<Booking> getBookingsOfClient(Integer clientId)
	{
		List<Booking> bookings = bookingDAO.getBookingsOfClient( clientId );
		return bookings;
	}

	public static List<Booking> getBookingsOfRoom(Integer roomId)
	{
		List<Booking> bookings = bookingDAO.getBookingsOfRoom( roomId );
		return bookings;
	}

	public static Client getClientById(Integer id)
	{
		Client client = clientDAO.getById( id );
		return client;
	}

	public static Integer getGuestsOfMonth(int month)
	{
		Integer guestsOfMonth = guestsAmountPerMonth.get( month );
		if (guestsOfMonth == null)
		{
			guestsOfMonth = new Integer( 0 );
		}
		return guestsOfMonth;
	}

	public static Float getIncomingOfMonth(int month)
	{
		Float incoming = incomingPerMonth.get( month );

		if (incoming == null)
		{
			incoming = new Float( 0.0 );
		}

		return incoming;

	}

	public static Room getRoomById(Integer roomId)
	{
		Room room = roomDAO.getById( roomId );
		return room;
	}

	public static List<Booking> getStaysOfClient(Integer clientId)
	{
		List<Booking> bookings = stayDAO.getBookingsOfClient( clientId );
		return bookings;
	}

	public static List<Booking> getStaysOfRoom(Integer roomId)
	{
		List<Booking> bookings = stayDAO.getBookingsOfRoom( roomId );
		return bookings;
	}

	public static boolean isCpfUnique(String cpf)
	{
		List<Client> allClients = clientDAO.getAll();

		for (Client client : allClients)
		{
			if (client.getCpf().equals( cpf ))
			{
				return false;
			}
		}

		return true;

	}

	public static List<Booking> listAllBookings()
	{
		List<Booking> allBookings = bookingDAO.getAll();
		return allBookings;
	}

	public static List<Client> listAllClients()
	{
		List<Client> allClients = clientDAO.getAll();
		return allClients;
	}

	public static List<Room> listAllRooms()
	{
		List<Room> allRooms = roomDAO.getAll();
		return allRooms;
	}

	public static List<Booking> listAllStays()
	{
		List<Booking> allStays = stayDAO.getAll();
		return allStays;
	}

	public static void removeBooking(Booking booking)
	{
		bookingDAO.delete( booking );
	}

	public static void removeStay(Booking stay)
	{
		stay.setStatus( Status.FINISHED );
		stayDAO.delete( stay );

		Date checkout = stay.getCheckout();

		Calendar checkoutCalendar = Calendar.getInstance();
		checkoutCalendar.setTime( checkout );

		int month = checkoutCalendar.get( Calendar.MONTH );

		Integer guestsAmount = guestsAmountPerMonth.get( month );
		if (guestsAmount == null)
		{
			guestsAmount = new Integer( 0 );
		}

		Integer guests = stay.getGuests();
		guestsAmount += guests;

		guestsAmountPerMonth.put( month, guestsAmount );

		Float incoming = incomingPerMonth.get( month );
		if (incoming == null)
		{
			incoming = new Float( 0.0 );
		}

		incoming += HotelUtil.calculateStayPrice( stay );

		incomingPerMonth.put( month, incoming );
	}
}
