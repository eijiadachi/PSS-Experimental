package br.inf.pucrio.hotel.util;

import java.util.Date;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Room;

public final class HotelUtil
{
	public static Float calculateStayPrice(Booking stay)
	{
		Room room = stay.getRoom();

		Float pricePerDay = room.getPrice();

		boolean hasExtraBed = stay.hasExtraBed();

		Long days = HotelUtil.subtractionInDays( stay.getCheckin(), stay.getCheckout() );

		Float total = pricePerDay * days;

		if (hasExtraBed)
		{
			total = total * 0.3f;
		}

		return total;
	}

	public static String getDateStr(Date date)
	{
		if (date == null)
		{
			return "Data n‹o cadastrada";
		}

		return HotelConstants.DATE_FORMATTER.format( date );
	}

	public static boolean hasMoreThanOneDayBetween(Date begin, Date end)
	{
		Long subtractionInDays = subtractionInDays( begin, end );

		return subtractionInDays > 1.0;
	}

	public static Long subtractionInDays(Date begin, Date end)
	{
		Long subtractionInMilisec = subtractionInMilisec( begin, end );

		double subtractionInSec = subtractionInMilisec / 1000.00;

		double subtractionInMin = subtractionInSec / 60.00;

		double subtractionInHours = subtractionInMin / 60.00;

		Double subtractionInDays = subtractionInHours / 24.00;

		Long days = subtractionInDays.longValue();

		return days;
	}

	public static Long subtractionInMilisec(Date begin, Date end)
	{
		long initTimeInMillis = begin.getTime();

		long endTimeInMillis = end.getTime();

		long diff = endTimeInMillis - initTimeInMillis;

		return diff;
	}

	private HotelUtil()
	{
		super();
	}
}
