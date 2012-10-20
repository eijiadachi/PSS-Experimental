package br.inf.pucrio.hotel.dao;

import java.util.Date;
import java.util.List;

import br.inf.pucrio.hotel.exception.HotelException;
import br.inf.pucrio.hotel.model.Client;

public class ClientDAO extends TransientDAOImpl<Client>
{

	public ClientDAO()
	{
		Client client = new Client();

		client.setCpf( "063.931.944-05" );
		client.setName( "Eiji Adachi Medeiros Barbosa" );
		client.setBirthday( new Date( "17/10/95" ) );

		this.add( client );
	}

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

		super.add( client );
	}

	@Override
	public Integer initCounter()
	{
		return 100;
	}
}
