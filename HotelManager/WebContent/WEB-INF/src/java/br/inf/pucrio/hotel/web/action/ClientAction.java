package br.inf.pucrio.hotel.web.action;

import java.util.List;

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

			saveOnRequest( HotelConstants.SUCCESS_MSG_ATTR, successMsg );

			return SUCCESS;
		}
		catch (HotelException e)
		{
			return ERROR;
		}
	}

	public Client getClient()
	{
		return client;
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

	// @Override
	// public void validate()
	// {
	// String cpf = getClient().getCpf();
	// if (!HotelManagerFacade.isCpfUnique( cpf ))
	// {
	// addFieldError( "client.cpf", String.format(
	// "CPF %s já está cadastrado no sistema.", cpf ) );
	// }
	// }

}
