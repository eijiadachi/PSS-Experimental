package br.inf.pucrio.hotel.dao;

import br.inf.pucrio.hotel.model.Booking;

public class BookingDAO extends TransientDAOImpl<Booking>
{
	private static Integer counter = 0;

	@Override
	public void add(Booking booking)
	{
		booking.setId( ++counter );
		super.add( booking );
	}
}
