package br.inf.pucrio.hotel.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.inf.pucrio.hotel.model.Booking;

public class BookingDAO extends TransientDAOImpl<Booking>
{
	private static Integer counter = 0;

	private static final Map<Integer, List<Booking>> bookingsPerRoomId = new TreeMap<Integer, List<Booking>>();

	@Override
	public void add(Booking booking)
	{
		booking.setId( ++counter );

		Integer roomId = booking.getRoom().getId();

		List<Booking> bookingsList = bookingsPerRoomId.get( roomId );
		if (bookingsList == null)
		{
			bookingsList = new ArrayList<Booking>();
			bookingsPerRoomId.put( roomId, bookingsList );
		}

		bookingsList.add( booking );

		super.add( booking );
	}

	public List<Booking> getBookings(Integer roomId)
	{
		List<Booking> bookingsList = bookingsPerRoomId.get( roomId );
		return bookingsList;
	}
}
