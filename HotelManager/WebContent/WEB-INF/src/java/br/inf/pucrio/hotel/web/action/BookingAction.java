package br.inf.pucrio.hotel.web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Client;
import br.inf.pucrio.hotel.model.Room;
import br.inf.pucrio.hotel.util.HotelUtil;

public class BookingAction extends HotelBaseAction
{
	private static final long serialVersionUID = 1L;

	private Booking booking;

	private Integer clientCode;

	private Integer guests;

	private Integer roomNumber;

	public BookingAction()
	{
		super();
	}

	@Override
	public String add()
	{
		Client client = HotelManagerFacade.getClientById( clientCode );
		Room room = HotelManagerFacade.getRoomById( roomNumber );

		booking.setClient( client );
		booking.setRoom( room );

		performAddOperation( booking );

		return SUCCESS;
	}

	public String getAllElementsKey()
	{
		return HotelConstants.ALL_BOOKINGS_ATTR;
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

	public Integer getRoomNumber()
	{
		return roomNumber;
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

	private boolean isRoomAvailable(Date checkin, Date checkout)
	{
		List<Booking> bookings = HotelManagerFacade.getBookingsOfRoom( roomNumber );
		List<Booking> stays = HotelManagerFacade.getStaysOfRoom( roomNumber );

		if ((bookings == null || bookings.isEmpty()) && (stays == null || stays.isEmpty()))
		{
			return true;
		}

		long checkinTime = checkin.getTime();
		long checkoutTime = checkout.getTime();

		List<Booking> occupations = new ArrayList<Booking>();
		if (stays != null)
		{
			occupations.addAll( stays );
		}
		if (bookings != null)
		{
			occupations.addAll( bookings );
		}

		for (Booking occupation : occupations)
		{
			Date checkinBooked = occupation.getCheckin();
			long checkinBookedTime = checkinBooked.getTime();

			Date checkoutBooked = occupation.getCheckout();
			long checkoutBookedTime = checkoutBooked.getTime();

			// Se o inicio que se deseja reservar cair entre um periodo ocupado
			boolean isCheckinAfterCheckinBooked = checkinTime >= checkinBookedTime;
			boolean isCheckinBeforeCheckoutBooked = checkinTime < checkoutBookedTime;
			if (isCheckinAfterCheckinBooked && isCheckinBeforeCheckoutBooked)
			{
				return false;
			}

			// Se o fim que se deseja reservar cair entre um periodo ocupado
			boolean isCheckoutAfterCheckinBooked = checkoutTime > checkinBookedTime;
			boolean isCheckoutBeforeCheckoutBooked = checkoutTime <= checkoutBookedTime;
			if (isCheckoutAfterCheckinBooked && isCheckoutBeforeCheckoutBooked)
			{
				return false;
			}

		}
		return true;
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
		List<Booking> allBookings = performListAllOperation();
		String key = getAllElementsKey();
		saveOnSession( key, allBookings );
		return SUCCESS;
	}

	protected void performAddOperation(Booking booking)
	{
		HotelManagerFacade.addBooking( booking );
		addActionMessage( String.format( "Reserva cadastrada com sucesso." ) );
	}

	protected List<Booking> performListAllOperation()
	{
		List<Booking> allBookings = HotelManagerFacade.listAllBookings();
		return allBookings;
	}

	@Override
	public String search()
	{
		throw new IllegalStateException( "Booking search is not implemented and should not be invoked." );
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

	public void setRoomNumber(Integer roomNumber)
	{
		this.roomNumber = roomNumber;
	}

	@Override
	public void validate()
	{
		if (getBooking() == null)
		{
			return;
		}

		validateClientCode();
		validateCheckin();
		validateCheckout();
		validatePeriodBetweenCheckinAndCheckout();
		validateRoom();
		validateGuests();
	}

	protected void validateCheckin()
	{
		Date checkin = booking.getCheckin();
		if (checkin == null)
		{
			addFieldError( "booking.checkin", "Data de checkin Ž obrigat—rio." );
		}
		else if (!isCheckinAfterCurrent( checkin ))
		{
			addFieldError( "booking.checkin", "Data de checkin deve ser ap—s a data corrente." );
		}
	}

	protected void validateCheckout()
	{
		Date checkout = booking.getCheckout();
		if (checkout == null)
		{
			addFieldError( "booking.checkout", "Data de checkout é obrigatório." );
		}
	}

	protected void validateClientCode()
	{
		if (clientCode == null)
		{
			addFieldError( "clientCode", "Código do Cliente é obrigatório" );
		}
		else if (!isValidClient( clientCode ))
		{
			String msg = String.format( "Não existe cliente cadastrado com o código '%s'.", clientCode );
			addFieldError( "clientCode", msg );
		}

	}

	protected void validateGuests()
	{
		Integer guests2 = booking.getGuests();
		if (guests2 == null || guests2 <= 0)
		{
			addFieldError( "booking.guests", "Número de hóspedes é obrigatório." );
		}
		else
		{
			Room room = HotelManagerFacade.getRoomById( roomNumber );
			Integer maximumCapacity = room.getMaximumCapacity();

			if (guests2 > maximumCapacity + 1)
			{
				String msg = String
						.format(
								"Número de hóspedes excede a capacidade máxima do quarto. Quarto suporta %s hóspedes, mais uma cama extra.",
								maximumCapacity );
				addFieldError( "booking.guests", msg );
			}
		}
	}

	protected void validatePeriodBetweenCheckinAndCheckout()
	{
		Date checkin = booking.getCheckin();
		Date checkout = booking.getCheckout();
		if (checkin != null && checkout != null)
		{
			if (!isCheckinBeforeCheckout( checkin, checkout ))
			{
				addFieldError( "booking.checkout", "Data de checkout deve ser posterior a data de checkin." );
			}
			else if (!HotelUtil.hasMoreThanOneDayBetween( checkin, checkout ))
			{
				addFieldError( "booking.checkout",
						"Data de checkout deve ser pelo menos um dia após a data de checkin." );
			}
		}

	}

	protected void validateRoom()
	{
		Date checkin = booking.getCheckin();
		Date checkout = booking.getCheckout();

		if (roomNumber == null)
		{
			addFieldError( "roomNumber", "Numero do Quarto Ž obrigat—rio" );
		}
		else if (!isValidRoom( roomNumber ))
		{
			String msg = String.format( "Nao existe quarto com o numero '%s'.", roomNumber );
			addFieldError( "roomNumber", msg );
		}

		if (!isRoomAvailable( checkin, checkout ))
		{
			addFieldError( "roomNumber", "Quarto indisponivel no periodo desejado." );
		}

	}
}
