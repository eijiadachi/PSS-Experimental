package br.inf.pucrio.hotel.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking extends Bean
{
	private Date checkin;

	private Date checkout;

	private Client client;

	private Integer guests;

	private Room room;

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
		String checkinStr = checkin == null ? "" : new SimpleDateFormat( dateFormat ).format( checkin );
		String checkoutStr = checkout == null ? "" : new SimpleDateFormat( dateFormat ).format( checkout );
		return String.format( "Cliente: %s - Quarto: %s - Checkin: %s - Checkout: %s - H—spedes: %s - Cama extra: %s",
				client, room, checkinStr, checkoutStr, guests, hasExtraBed() ? "Sim" : "N‹o" );
	}
}
