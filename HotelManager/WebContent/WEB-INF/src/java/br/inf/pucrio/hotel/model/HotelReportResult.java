package br.inf.pucrio.hotel.model;

public class HotelReportResult
{
	private Integer guestsOfMonth;

	private Float incomingOfMonth;

	private Integer roomsOccupied;

	private Integer roomsTotal;

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
