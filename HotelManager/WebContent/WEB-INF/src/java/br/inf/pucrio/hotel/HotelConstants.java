package br.inf.pucrio.hotel;

import java.text.SimpleDateFormat;

public final class HotelConstants
{
	public static final String ALL_BOOKINGS_ATTR = "all_bookings";

	public static final String ALL_CLIENTS_ATTR = "all_clients";

	public static final String ALL_ROOMS_ATTR = "all_rooms";

	public static final String ALL_STAYS_ATTR = "all_stays";

	public static final String DATE_FORMAT_STR = "dd-MM-yy";

	public static final SimpleDateFormat DATE_FORMATTER;

	public static final String ERROR_MSG_ATTR = "error_msg";

	public static final String RESULT_CLIENT_ATTR = "result_client";

	public static final String RESULT_HOTEL_REPORT_ATTR = "result_hotel_report";

	public static final String RESULT_LEAVE_ATTR = "result_leave";

	public static final String RESULT_ROOM_ATTR = "result_room";

	public static final String RESULT_STAY_BOOKED_ATTR = "result_stay_booked";

	public static final String STAY_BOOKED_ID = "stay_booked_id";

	public static final String SUCCESS_MSG_ATTR = "success_msg";

	static
	{
		DATE_FORMATTER = new SimpleDateFormat( DATE_FORMAT_STR );
	}
}
