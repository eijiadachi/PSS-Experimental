package br.inf.pucrio.hotel;

import java.util.Date;
import java.util.List;

import br.inf.pucrio.hotel.dao.ClientDAO;
import br.inf.pucrio.hotel.model.Client;

public class HotelManagerFacade
{
	private static ClientDAO clientDAO;

	public static void addClient(final String name, final String cpf, final Date birthday, final String address,
			final String phone)
	{
		Client client = new Client();
		client.setAddress( address );
		client.setBirthday( birthday );
		client.setName( name );
		client.setPhone( phone );

		addClient( client );
	}

	public static List<Client> listAllClients()
	{
		List<Client> allClients = clientDAO.getAll();
		return allClients;
	}

	private static void addClient(Client client)
	{
		clientDAO.add( client );
	}

	static
	{
		clientDAO = new ClientDAO();
	}
}
