<%@page import="br.inf.pucrio.hotel.model.result.HotelReportResult"%>
<%@page import="java.util.List"%>
<%@page import="br.inf.pucrio.hotel.HotelConstants"%>
<%@page import="br.inf.pucrio.hotel.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relat�rio Hotel</title>
</head>
<body>

<%
	
	final HotelReportResult result = (HotelReportResult)session.getAttribute( HotelConstants.RESULT_HOTEL_REPORT_ATTR );
	Integer roomsTotal = result.getRoomsTotal();
	Integer roomsOccupied = result.getRoomsOccupied();
	Float incomingOfMonth = result.getIncomingOfMonth();
	Integer guestsOfMonth = result.getGuestsOfMonth();
	List<Booking> bookingsForToday = result.getBookingsForToday();
	Integer bookingsForTodayTotal = 0;
	if(bookingsForToday != null)
	{
		bookingsForTodayTotal = bookingsForToday.size();	
	}
%>

<h1>
Relat�rio
</h1>
	<ul>
		<li>Total de quartos: <%= roomsTotal %></li>
		<li>Quartos ocupados: <%= roomsOccupied %></li>
		<li>Valor total recebido no m�s: <%= incomingOfMonth %></li>
		<li>H�spedes recebidos no m�s: <%= guestsOfMonth %></li>
		<li>N�mero de reservas com entrada hoje: <%= bookingsForTodayTotal %></li>
	</ul>

</body>
</html>