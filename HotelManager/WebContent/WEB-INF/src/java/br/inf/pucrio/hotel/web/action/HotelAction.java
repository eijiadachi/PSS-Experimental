package br.inf.pucrio.hotel.web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Booking.Status;
import br.inf.pucrio.hotel.model.HotelReportResult;
import br.inf.pucrio.hotel.model.Room;

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

		Calendar currentCalendar = Calendar.getInstance();
		int currentMonth = currentCalendar.get( Calendar.MONTH );

		Float incomingOfMonth = HotelManagerFacade.getIncomingOfMonth( currentMonth );

		Integer guestsOfMonth = HotelManagerFacade.getGuestsOfMonth( currentMonth );

		List<Booking> bookingsForToday = new ArrayList<Booking>();

		int currentDay = currentCalendar.get( Calendar.DAY_OF_MONTH );
		int currentYear = currentCalendar.get( Calendar.YEAR );

		List<Booking> allBookings = HotelManagerFacade.listAllBookings();
		for (Booking booking : allBookings)
		{
			Date checkin = booking.getCheckin();
			Calendar checkinCalendar = Calendar.getInstance();
			checkinCalendar.setTime( checkin );

			int checkinMonth = checkinCalendar.get( Calendar.MONTH );
			int checkinDay = checkinCalendar.get( Calendar.DAY_OF_MONTH );
			int checkinYear = checkinCalendar.get( Calendar.YEAR );

			if (currentDay == checkinDay && currentMonth == checkinMonth && currentYear == checkinYear)
			{
				bookingsForToday.add( booking );
			}
		}

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
