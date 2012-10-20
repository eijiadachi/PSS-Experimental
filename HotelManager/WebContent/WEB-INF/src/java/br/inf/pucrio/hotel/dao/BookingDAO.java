package br.inf.pucrio.hotel.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.inf.pucrio.hotel.model.Booking;

public class BookingDAO extends TransientDAOImpl<Booking>
{
	private final Map<Integer, List<Booking>> bookingsPerClientIdMap;

	private final Map<Integer, List<Booking>> bookingsPerRoomIdMap;

	public BookingDAO()
	{
		bookingsPerClientIdMap = new TreeMap<Integer, List<Booking>>();
		bookingsPerRoomIdMap = new TreeMap<Integer, List<Booking>>();
	}

	@Override
	public void add(Booking booking)
	{
		super.add( booking );

		Integer roomId = booking.getRoom().getId();

		addOrUpdate( bookingsPerRoomIdMap, roomId, booking );

		Integer clientId = booking.getClient().getId();

		addOrUpdate( bookingsPerClientIdMap, clientId, booking );
	}

	private <T> void addOrUpdate(Map<Integer, List<T>> map, Integer key, T element)
	{
		List<T> list = map.get( key );
		if (list == null)
		{
			list = new ArrayList<T>();
			map.put( key, list );
		}
		list.add( element );
	}

	public List<Booking> getBookingsOfClient(Integer clientId)
	{
		List<Booking> clientBookingsList = bookingsPerClientIdMap.get( clientId );
		return clientBookingsList;
	}

	public List<Booking> getBookingsOfRoom(Integer roomId)
	{
		List<Booking> bookingsList = bookingsPerRoomIdMap.get( roomId );
		return bookingsList;
	}

	@Override
	public Integer initCounter()
	{
		return 0;
	}
}
