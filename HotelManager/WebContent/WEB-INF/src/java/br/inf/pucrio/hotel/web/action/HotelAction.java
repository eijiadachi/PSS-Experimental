package br.inf.pucrio.hotel.web.action;

import java.util.Calendar;
import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.result.HotelReportResult;

public class HotelAction extends HotelBaseAction
{

	private static final long serialVersionUID = 1L;

	@Override
	public String add()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String execute() throws Exception
	{
		int roomsSize = HotelManagerFacade.getTotalRooms();

		int roomsOccupied = HotelManagerFacade.getRoomsOccupied();

		Calendar currentCalendar = Calendar.getInstance();
		int currentMonth = currentCalendar.get( Calendar.MONTH );

		Float incomingOfMonth = HotelManagerFacade.getIncomingOfMonth( currentMonth );

		Integer guestsOfMonth = HotelManagerFacade.getGuestsOfMonth( currentMonth );

		List<Booking> bookingsForToday = HotelManagerFacade.getBookingsForToday();

		HotelReportResult result = new HotelReportResult();
		result.setGuestsOfMonth( guestsOfMonth );
		result.setIncomingOfMonth( incomingOfMonth );
		result.setRoomsOccupied( roomsOccupied );
		result.setRoomsTotal( roomsSize );
		result.setBookingsForToday( bookingsForToday );

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
