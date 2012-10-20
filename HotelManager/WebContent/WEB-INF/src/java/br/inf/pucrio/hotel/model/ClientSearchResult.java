package br.inf.pucrio.hotel.model;

import java.util.List;

public class ClientSearchResult
{
	private List<Booking> bookings;

	private Client client;

	private List<Booking> stays;

	public List<Booking> getBookings()
	{
		return bookings;
	}

	public Client getClient()
	{
		return client;
	}

	public List<Booking> getStays()
	{
		return stays;
	}

	public void setBookings(List<Booking> bookings)
	{
		this.bookings = bookings;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public void setStays(List<Booking> stays)
	{
		this.stays = stays;
	}
}
