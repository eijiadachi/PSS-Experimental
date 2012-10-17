package br.inf.pucrio.hotel.exception;

public class HotelException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public HotelException()
	{
		super();
	}

	public HotelException(String msg)
	{
		super( msg );
	}

	public HotelException(String msg, Throwable cause)
	{
		super( msg, cause );
	}

	public HotelException(Throwable cause)
	{
		super( cause );
	}

}
