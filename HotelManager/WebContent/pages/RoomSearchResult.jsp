<%@page import="java.util.List"%>
<%@page import="br.inf.pucrio.hotel.model.*"%>
<%@page import="br.inf.pucrio.hotel.HotelConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalhes do quarto</title>
</head>
<body>

<%
		RoomSearchResult result = (RoomSearchResult) session.getAttribute( HotelConstants.RESULT_ROOM_ATTR );
		Room room = result.getRoom();
		List<Booking> bookings = result.getBookings();
		List<Booking> stays = result.getStays();
%>
		Dados do Quarto:<p>
		<ul>
			<li>C�digo: <%= room.getId() %></li>
			<li>Tipo: <%= room.getCategory() %></li>
			<li>Valor da di�ria: <%= room.getPrice() %></li>
			<li>Capacidade: <%= room.getMaximumCapacity() %></li>
		</ul>
		
		Reservas:<p>
		<ul>
<%		
		if( bookings != null )
		{
			for(Booking booking : bookings )
			{
%>
				<li>Cliente: <%= booking.getClient().toString() %></li>
				<li>Data de checkin: <%= booking.getCheckin() %></li>
				<li>Data de checkout: <%= booking.getCheckout() %></li>
				<li>N�mero de h�spedes: <%= booking.getGuests() %></li>
<%
			}
		}
		else
		{
%>
			<li>Quarto sem reservas.</li>
<%			
		}
%>
		</ul>
		
		Hist�rico de Ocupa��es:<p>
		<ul>
<%
		if( stays != null)
		{
			for(Booking booking : stays )
			{
%>
				<li><%= booking.getRoom().toString() %></li>
				<li><%= booking.getCheckin() %></li>
				<li><%= booking.getCheckout() %></li>
				<li><%= booking.getGuests() %></li>
			
<%
			}
		}
		else
		{
%>
			<li>Quarto sem ocupa��es.</li>
<%			
		}
%>
		</ul>

</body>
</html>