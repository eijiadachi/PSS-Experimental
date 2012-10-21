package br.inf.pucrio.hotel.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.inf.pucrio.hotel.util.HotelUtil;

public class Booking extends Bean
{
	public enum Status
	{
		FINISHED, OCCUPIED, RESERVED
	}

	private Date checkin;

	private Date checkout;

	private Client client;

	private Integer guests;

	private Room room;

	private Status status;

	public Date getCheckin()
	{
		return checkin;
	}

	public String getCheckinStr()
	{
		return HotelUtil.getDateStr( getCheckin() );
	}

	public Date getCheckout()
	{
		return checkout;
	}

	public String getCheckoutStr()
	{
		return HotelUtil.getDateStr( getCheckout() );
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

	public Status getStatus()
	{
		return status;
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

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public String toHtml()
	{
		String html = String.format( "<ul class='booking'>" + "<li>Cliente: %1$s</li>" + "<li>Quarto: %2$s</li>"
				+ "<li>Nœmero de H—spedes: %3$s</li>" + "<li>Cama extra: %4$s</li>" + "<li>Data de entrada: %5$s</li>"
				+ "<li>Data de sa’da: %6$s</li>" + "</ul>", getClient().toHtml(), getRoom().toHtml(), getGuests(),
				hasExtraBed() ? "Sim" : "N‹o", getCheckinStr(), getCheckoutStr() );

		return html;
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
