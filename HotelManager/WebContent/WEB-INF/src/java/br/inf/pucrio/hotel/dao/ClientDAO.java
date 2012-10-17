package br.inf.pucrio.hotel.dao;

import java.util.List;

import br.inf.pucrio.hotel.exception.HotelException;
import br.inf.pucrio.hotel.model.Client;

public class ClientDAO extends TransientDAOImpl<Client>
{
	private static Integer counter = 100;

	@Override
	public void add(Client client)
	{
		List<Client> repository = this.getRepository();
		for (Client client2 : repository)
		{
			String cpf = client.getCpf();
			String cpf2 = client2.getCpf();

			if (cpf != null && cpf2 != null && cpf2.equals( cpf ))
			{
				String msg = String.format( "CPF cadastrado - %s - já encontra-se no sistema.", cpf );
				throw new HotelException( msg );
			}
		}

		client.setId( ++counter );

		super.add( client );
	}
}
