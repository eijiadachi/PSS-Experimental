package br.inf.pucrio.hotel.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.inf.pucrio.hotel.HotelConstants;
import br.inf.pucrio.hotel.HotelManagerFacade;
import br.inf.pucrio.hotel.model.Booking;
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
			if (stay.getCheckout() == null)
			{
				Date currentDate = Calendar.getInstance().getTime();
				stay.setCheckout( currentDate );
				checkout = stay;
				break;
			}
		}

		if (checkout != null)
		{
			LeaveResult result = new LeaveResult();
			result.setStay( checkout );
			saveOnSession( HotelConstants.RESULT_LEAVE_ATTR, result );
		}

		return "detail";
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
