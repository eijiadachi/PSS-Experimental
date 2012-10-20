package br.inf.pucrio.hotel.model;

import java.util.List;

public class ClientSearchResult
{
	private List<Booking> bookings;

	private Client client;

	public List<Booking> getBookings()
	{
		return bookings;
	}

	public Client getClient()
	{
		return client;
	}

	public void setBookings(List<Booking> bookings)
	{
		this.bookings = bookings;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}
}
