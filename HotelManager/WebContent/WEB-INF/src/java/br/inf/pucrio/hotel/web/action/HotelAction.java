package br.inf.pucrio.hotel.web.action;

import java.util.Calendar;
import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Booking.Status;
import br.inf.pucrio.hotel.model.HotelReportResult;
import br.inf.pucrio.hotel.model.Room;

public class HotelAction extends HotelBaseAction
{

	@Override
	public String add()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String execute() throws Exception
	{
		List<Room> allRooms = HotelManagerFacade.listAllRooms();
		int roomsSize = allRooms.size();

		int roomsOccupied = 0;
		List<Booking> allStays = HotelManagerFacade.listAllStays();
		for (Booking stay : allStays)
		{
			if (Status.OCCUPIED.equals( stay.getStatus() ))
			{
				roomsOccupied++;
			}
		}

		int month = Calendar.getInstance().get( Calendar.MONTH );

		Float incomingOfMonth = HotelManagerFacade.getIncomingOfMonth( month );

		Integer guestsOfMonth = HotelManagerFacade.getGuestsOfMonth( month );

		HotelReportResult result = new HotelReportResult();
		result.setGuestsOfMonth( guestsOfMonth );
		result.setIncomingOfMonth( incomingOfMonth );
		result.setRoomsOccupied( roomsOccupied );
		result.setRoomsTotal( roomsSize );

		saveOnSession( HotelConstants.RESULT_HOTEL_REPORT_ATTR, result );

		return SUCCESS;
	}

	@Override
	public String listAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String search()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
