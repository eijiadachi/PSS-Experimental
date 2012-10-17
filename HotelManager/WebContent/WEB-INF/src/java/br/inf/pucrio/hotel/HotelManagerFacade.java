package br.inf.pucrio.hotel;

import java.util.List;

import br.inf.pucrio.hotel.dao.ClientDAO;
import br.inf.pucrio.hotel.dao.RoomDAO;
import br.inf.pucrio.hotel.model.Client;
import br.inf.pucrio.hotel.model.Room;

public class HotelManagerFacade
{
	private static ClientDAO clientDAO;

	private static RoomDAO roomDAO;

	static
	{
		clientDAO = new ClientDAO();
		roomDAO = new RoomDAO();
	}

	public static void addClient(Client client)
	{
		clientDAO.add( client );
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
