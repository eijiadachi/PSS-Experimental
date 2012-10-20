package br.inf.pucrio.hotel.model;

import br.inf.pucrio.hotel.util.HotelUtil;

public class LeaveResult
{
	private Booking stay;

	public Long getDays()
	{
		Long subtractionInDays = HotelUtil.subtractionInDays( stay.getCheckin(), stay.getCheckout() );
		return subtractionInDays;
	}

	public Float getPrice()
	{
		Room room = stay.getRoom();

		Float pricePerDay = room.getPrice();

		boolean hasExtraBed = stay.hasExtraBed();

		Long days = getDays();

		Float total = pricePerDay * days;

		if (hasExtraBed)
		{
			total = total * 0.3f;
		}

		return total;
	}

	public Booking getStay()
	{
		return stay;
	}

	public void setStay(Booking stay)
	{
		this.stay = stay;
	}
}
