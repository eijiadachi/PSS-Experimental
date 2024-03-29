package br.inf.pucrio.hotel.model.result;

import java.util.List;

import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Room;

public class RoomSearchResult
{
	private List<Booking> bookings;

	private Room room;

	private List<Booking> stays;

	public List<Booking> getBookings()
	{
		return bookings;
	}

	public Room getRoom()
	{
		return room;
	}

	public List<Booking> getStays()
	{
		return stays;
	}

	public void setBookings(List<Booking> bookings)
	{
		this.bookings = bookings;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	public void setStays(List<Booking> stays)
	{
		this.stays = stays;
	}
}
