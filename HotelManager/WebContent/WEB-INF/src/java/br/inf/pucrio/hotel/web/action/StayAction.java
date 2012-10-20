package br.inf.pucrio.hotel.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.exception.HotelException;
import br.inf.pucrio.hotel.model.Booking;

public class StayAction extends BookingAction
{

	private static final long serialVersionUID = 1L;

	private Date copyWithoutHourMinuteSecondMilisecond(Date date)
	{
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat( "dd-MM-yyyy" );
			String dateStr = formatter.format( date );
			Date dateFormatted = formatter.parse( dateStr );
			return dateFormatted;
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			throw new HotelException( e );
		}

	}

	@Override
	public String getAllElementsKey()
	{
		return HotelConstants.ALL_STAYS_ATTR;
	}

	@Override
	public void performAddOperation(Booking stay)
	{
		HotelManagerFacade.addStay( stay );
		addActionMessage( String.format( "Checkin realizado com sucesso." ) );
	}

	@Override
	protected List<Booking> performListAllOperation()
	{
		List<Booking> allStays = HotelManagerFacade.listAllStays();
		return allStays;
	}

	@Override
	protected void validateCheckin()
	{

		// Data de checkin deve ser data corrente
		Date checkin = getBooking().getCheckin();

		Date checkinFormatted = copyWithoutHourMinuteSecondMilisecond( checkin );

		Calendar currentCalendar = Calendar.getInstance();
		Date currentTime = currentCalendar.getTime();

		Date currentTimeFormatted = copyWithoutHourMinuteSecondMilisecond( currentTime );

		if (checkinFormatted.compareTo( currentTimeFormatted ) != 0)
		{
			addFieldError( "booking.checkin", "Data de checkin deve ser a data de hoje." );
		}
	}

	@Override
	protected void validateCheckout()
	{
		// A data de checkout nao eh preenchida no momento do checkin. Por isso,
		// nao ha validacao.
	}

}
