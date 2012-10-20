package br.inf.pucrio.hotel.web.action;

import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;
import br.inf.pucrio.hotel.model.Booking.Status;
import br.inf.pucrio.hotel.model.LeaveResult;

public class LeaveAction extends HotelBaseAction<Booking>
{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Override
	public String add()
	{
		List<Booking> stays = HotelManagerFacade.getStaysOfRoom( id );

		Booking checkout = null;
		for (Booking stay : stays)
		{
			if (Status.OCCUPIED.equals( stay.getStatus() ))
			{
				checkout = stay;
				break;
			}
		}

		if (checkout != null)
		{
			checkout.setStatus( Status.FINISHED );
			LeaveResult result = new LeaveResult();
			result.setStay( checkout );
			saveOnSession( HotelConstants.RESULT_LEAVE_ATTR, result );
		}

		return DETAIL;
	}

	public Integer getId()
	{
		return id;
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

	public void setId(Integer id)
	{
		this.id = id;
	}

}
