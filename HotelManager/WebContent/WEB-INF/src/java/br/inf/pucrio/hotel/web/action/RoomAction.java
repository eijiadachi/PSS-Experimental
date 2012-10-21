package br.inf.pucrio.hotel.web.action;

import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Room;
import br.inf.pucrio.hotel.model.RoomSearchResult;

public class RoomAction extends HotelBaseAction
{

	private static final long serialVersionUID = 1L;

	private Integer id;

	@Override
	public String add()
	{
		return SUCCESS;
	}

	public Integer getId()
	{
		return id;
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
		if (id == null)
		{
			addFieldError( "id", "C—digo do quarto Ž obrigat—rio." );
			return INPUT;
		}
		Room room = HotelManagerFacade.getRoomById( id );

		List<Booking> bookingsOfRoom = HotelManagerFacade.getBookingsOfRoom( id );

		List<Booking> staysOfRoom = HotelManagerFacade.getStaysOfRoom( id );

		RoomSearchResult result = new RoomSearchResult();
		result.setBookings( bookingsOfRoom );
		result.setRoom( room );
		result.setStays( staysOfRoom );

		saveOnSession( HotelConstants.RESULT_ROOM_ATTR, result );

		return SUCCESS;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public void validate()
	{
		if (id != null)
		{
			Room room = HotelManagerFacade.getRoomById( id );
			if (room == null)
			{
				addFieldError( "id", String.format( "N‹o existe quarto com o c—digo %s.", id ) );
			}
		}
	}

}
