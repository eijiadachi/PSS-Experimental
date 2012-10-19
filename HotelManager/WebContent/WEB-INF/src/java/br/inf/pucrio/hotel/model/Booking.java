package br.inf.pucrio.hotel.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking extends Bean
{
	private Client client;

	private Room room;

	private Date checkin;

	private Date checkout;

	private Integer guests;

	public Date getCheckin()
	{
		return checkin;
	}

	public Date getCheckout()
	{
		return checkout;
	}

	public Client getClient()
	{
		return client;
	}

	public Integer getGuests()
	{
		return guests;
	}

	public Room getRoom()
	{
		return room;
	}

	public boolean hasExtraBed()
	{
		return guests > getRoom().getMaximumCapacity();
	}

	public void setCheckin(Date checkin)
	{
		this.checkin = checkin;
	}

	public void setCheckout(Date checkout)
	{
		this.checkout = checkout;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public void setGuests(Integer guests)
	{
		this.guests = guests;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	@Override
	public String toString()
	{
		String dateFormat = "dd-MM-yyyy";
		String checkinStr = new SimpleDateFormat( dateFormat ).format( checkin );
		String checkoutStr = new SimpleDateFormat( dateFormat ).format( checkout );
		return String.format( "Cliente: %s - Quarto: %s - Checkin: %s - Checkout: %s - Guests: %s - Extra bed: %s",
				client, room, checkinStr, checkoutStr, guests, hasExtraBed() );
	}
}
