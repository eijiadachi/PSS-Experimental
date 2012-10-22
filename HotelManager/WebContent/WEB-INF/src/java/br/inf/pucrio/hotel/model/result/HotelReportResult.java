package br.inf.pucrio.hotel.model.result;

import java.util.List;

import br.inf.pucrio.hotel.model.Booking;

public class HotelReportResult
{
	private List<Booking> bookingsForToday;

	private Integer guestsOfMonth;

	private Float incomingOfMonth;

	private Integer roomsOccupied;

	private Integer roomsTotal;

	public List<Booking> getBookingsForToday()
	{
		return bookingsForToday;
	}

	public Integer getGuestsOfMonth()
	{
		return guestsOfMonth;
	}

	public Float getIncomingOfMonth()
	{
		return incomingOfMonth;
	}

	public Integer getRoomsOccupied()
	{
		return roomsOccupied;
	}

	public Integer getRoomsTotal()
	{
		return roomsTotal;
	}

	public void setBookingsForToday(List<Booking> bookingsForToday)
	{
		this.bookingsForToday = bookingsForToday;
	}

	public void setGuestsOfMonth(Integer guestsOfMonth)
	{
		this.guestsOfMonth = guestsOfMonth;
	}

	public void setIncomingOfMonth(Float incomingOfMonth)
	{
		this.incomingOfMonth = incomingOfMonth;
	}

	public void setRoomsOccupied(Integer roomsOccupied)
	{
		this.roomsOccupied = roomsOccupied;
	}

	public void setRoomsTotal(Integer roomsTotal)
	{
		this.roomsTotal = roomsTotal;
	}
}
