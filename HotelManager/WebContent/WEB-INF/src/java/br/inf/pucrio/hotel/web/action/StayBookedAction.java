package br.inf.pucrio.hotel.web.action;

import javax.servlet.http.HttpSession;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;

public class StayBookedAction extends HotelBaseAction
{

	private static final long serialVersionUID = 1L;

	private Integer bookingId;

	private final String CONFIRMATION = "confirmation";

	@Override
	public String add()
	{
		if (bookingId == null)
		{
			addFieldError( "bookingId", "Código da reserva é obrigatório." );
			return INPUT;
		}

		Booking booking = HotelManagerFacade.getBookingById( bookingId );

		if (booking == null)
		{
			String msg = String.format( "Não existe reserva com código %s.", bookingId );
			addFieldError( "bookingId", msg );
			return INPUT;
		}

		saveOnSession( HotelConstants.RESULT_STAY_BOOKED_ATTR, booking );

		return CONFIRMATION;
	}

	public String confirm()
	{
		HttpSession session = request.getSession();

		Integer bookingId = (Integer) session.getAttribute( HotelConstants.STAY_BOOKED_ID );

		Booking booking = HotelManagerFacade.getBookingById( bookingId );

		HotelManagerFacade.addStay( booking );
		HotelManagerFacade.removeBooking( booking );

		addActionMessage( String.format( "Ocupação (checkin) cadastrada com sucesso." ) );

		return SUCCESS;
	}

	public Integer getBookingId()
	{
		return bookingId;
	}

	@Override
	public String listAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String search()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setBookingId(Integer bookingId)
	{
		this.bookingId = bookingId;
	}

}
