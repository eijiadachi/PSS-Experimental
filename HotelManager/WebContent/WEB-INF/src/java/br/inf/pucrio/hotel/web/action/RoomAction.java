package br.inf.pucrio.hotel.web.action;

import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Room;

public class RoomAction extends HotelBaseAction<Room>
{

	private static final long serialVersionUID = 1L;

	@Override
	public String add()
	{
		return SUCCESS;
	}

	@Override
	public String listAll()
	{
		List<Room> allRooms = HotelManagerFacade.listAllRooms();

		saveOnSession( HotelConstants.ALL_ROOMS_ATTR, allRooms );

		return SUCCESS;
	}

	@Override
	public String search()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
