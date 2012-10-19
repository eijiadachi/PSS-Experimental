package br.inf.pucrio.hotel;

import java.util.List;

import br.inf.pucrio.hotel.dao.BookingDAO;
import br.inf.pucrio.hotel.dao.ClientDAO;
import br.inf.pucrio.hotel.dao.RoomDAO;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Client;
import br.inf.pucrio.hotel.model.Room;

public class HotelManagerFacade
{
	private static ClientDAO clientDAO;

	private static RoomDAO roomDAO;

	private static BookingDAO bookingDAO;

	static
	{
		clientDAO = new ClientDAO();
		roomDAO = new RoomDAO();
		bookingDAO = new BookingDAO();
	}

	public static void addBooking(Booking booking)
	{
		bookingDAO.add( booking );
	}

	public static void addClient(Client client)
	{
		clientDAO.add( client );
	}

	public static Client getClientById(Integer id)
	{
		Client client = clientDAO.getById( id );
		return client;
	}

	public static Room getRoomById(Integer roomId)
	{
		Room room = roomDAO.getById( roomId );
		return room;
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
}
