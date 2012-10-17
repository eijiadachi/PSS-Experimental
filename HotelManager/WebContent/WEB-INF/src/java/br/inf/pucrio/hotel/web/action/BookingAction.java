package br.inf.pucrio.hotel.web.action;

import java.util.Calendar;
import java.util.Date;

import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Client;
import br.inf.pucrio.hotel.model.Room;

public class BookingAction extends HotelBaseAction<Booking>
{
	private static final long serialVersionUID = 1L;

	private Booking booking;

	private Integer clientCode;

	private Integer roomNumber;

	private String room;

	private Integer guests;

	public BookingAction()
	{
		super();
	}

	@Override
	public String add()
	{
		HotelManagerFacade.addBooking( booking );
		return SUCCESS;
	}

	public Booking getBooking()
	{
		return booking;
	}

	public Integer getClientCode()
	{
		return clientCode;
	}

	public Integer getGuests()
	{
		return guests;
	}

	public String getRoom()
	{
		return room;
	}

	public Integer getRoomNumber()
	{
		return roomNumber;
	}

	private boolean hasMoreThanOneDayBetween(Date checkin, Date checkout)
	{
		Long subtractionInDays = subtractionInDays( checkin, checkout );

		return subtractionInDays > 1.0;
	}

	private boolean isCheckinAfterCurrent(Date checkin)
	{
		Calendar checkinCalendar = Calendar.getInstance();
		checkinCalendar.setTime( checkin );

		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTimeInMillis( System.currentTimeMillis() );

		boolean isCheckinAfterCurrent = checkinCalendar.after( currentCalendar );

		return isCheckinAfterCurrent;
	}

	private boolean isCheckinBeforeCheckout(Date checkin, Date checkout)
	{
		Calendar checkinCalendar = Calendar.getInstance();
		checkinCalendar.setTime( checkin );

		Calendar checkoutCalendar = Calendar.getInstance();
		checkoutCalendar.setTime( checkout );

		boolean isCheckinBeforeCheckout = checkinCalendar.before( checkoutCalendar );

		return isCheckinBeforeCheckout;
	}

	private boolean isValidClient(Integer clientId)
	{
		Client client = HotelManagerFacade.getClientById( clientId );

		return client != null;
	}

	private boolean isValidRoom(Integer roomId)
	{
		Room room = HotelManagerFacade.getRoomById( roomId );
		return room != null;
	}

	@Override
	public String listAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setBooking(Booking booking)
	{
		this.booking = booking;
	}

	public void setClientCode(Integer clientCode)
	{
		this.clientCode = clientCode;
	}

	public void setGuests(Integer guests)
	{
		this.guests = guests;
	}

	public void setRoom(String room)
	{
		this.room = room;
	}

	public void setRoomNumber(Integer roomNumber)
	{
		this.roomNumber = roomNumber;
	}

	public Long subtractionInDays(Date checkin, Date checkout)
	{
		Long subtractionInMilisec = subtractionInMilisec( checkin, checkout );

		double subtractionInSec = subtractionInMilisec / 1000.00;

		double subtractionInMin = subtractionInSec / 60.00;

		double subtractionInHours = subtractionInMin / 60.00;

		Double subtractionInDays = subtractionInHours / 24.00;

		Long days = subtractionInDays.longValue();

		return days;
	}

	private Long subtractionInMilisec(Date checkin, Date checkout)
	{
		Calendar checkinCalendar = Calendar.getInstance();
		checkinCalendar.setTime( checkin );
		long checkinTimeInMillis = checkinCalendar.getTimeInMillis();

		Calendar checkoutCalendar = Calendar.getInstance();
		checkoutCalendar.setTime( checkout );
		long checkoutTimeInMillis = checkoutCalendar.getTimeInMillis();

		return checkoutTimeInMillis - checkinTimeInMillis;
	}

	@Override
	public void validate()
	{
		if (getBooking() == null)
		{
			return;
		}

		if (clientCode == null)
		{
			addFieldError( "clientCode", "Código do Cliente é obrigatório" );
		}
		else if (!isValidClient( clientCode ))
		{
			String msg = String.format( "Não existe cliente cadastrado com o código '%s'.", clientCode );
			addFieldError( "clientCode", msg );
		}

		if (roomNumber == null)
		{
			addFieldError( "roomNumber", "Número do Quarto é obrigatório" );
		}
		else if (!isValidRoom( roomNumber ))
		{
			String msg = String.format( "Não existe quarto com o número '%s'.", roomNumber );
			addFieldError( "roomNumber", msg );
		}

		Date checkin = booking.getCheckin();
		if (checkin == null)
		{
			addFieldError( "booking.checkin", "Data de checkin é obrigatório." );
		}
		else if (!isCheckinAfterCurrent( checkin ))
		{
			addFieldError( "booking.checkin", "Data de checkin deve ser após a data corrente." );
		}

		Date checkout = booking.getCheckout();
		if (checkout == null)
		{
			addFieldError( "booking.checkout", "Data de checkout é obrigatório." );
		}

		if (!isCheckinBeforeCheckout( checkin, checkout ))
		{
			addFieldError( "booking.checkout", "Data de checkout deve ser posterior a data de checkin." );
		}
		else if (!hasMoreThanOneDayBetween( checkin, checkout ))
		{
			addFieldError( "booking.checkout", "Data de checkout deve ser pelo menos um dia após a data de checkin." );
		}

	}
}
