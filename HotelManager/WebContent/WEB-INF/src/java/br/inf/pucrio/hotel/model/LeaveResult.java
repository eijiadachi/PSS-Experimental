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
		return HotelUtil.calculateStayPrice( stay );
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
