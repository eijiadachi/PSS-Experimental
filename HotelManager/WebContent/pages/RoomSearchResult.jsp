<%@page import="java.util.List"%>
<%@page import="br.inf.pucrio.hotel.model.*"%>
<%@page import="br.inf.pucrio.hotel.model.result.*"%>
<%@page import="br.inf.pucrio.hotel.HotelConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			<li>Código: <%= room.getId() %></li>
			<li>Tipo: <%= room.getCategory() %></li>
			<li>Valor da diária: <%= room.getPrice() %></li>
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
				<li>Reserva: <%= booking.toHtml(  ) %></li>
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
		
		Histórico de Ocupações:<p>
		<ul>
<%
		if( stays != null)
		{
			for(Booking booking : stays )
			{
%>
				<li>Ocupação <%= booking.toHtml(  ) %></li>
			
<%
			}
		}
		else
		{
%>
			<li>Quarto sem ocupações.</li>
<%			
		}
%>
		</ul>

</body>
</html>