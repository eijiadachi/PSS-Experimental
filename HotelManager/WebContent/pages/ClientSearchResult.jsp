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
<title>Detalhes do cliente</title>
</head>
<body>

<%
		ClientSearchResult result = (ClientSearchResult) session.getAttribute( HotelConstants.RESULT_CLIENT_ATTR );
		Client client = result.getClient();
		List<Booking> bookings = result.getBookings();
%>
		Dados do Cliente:<p>
		<ul>
			<li>C�digo: <%= client.getId() %></li>
			<li>Nome: <%= client.getName() %></li>
			<li>CPF: <%= client.getCpf() %></li>
			<li>Telefone: <%= client.getPhone() != null ? client.getPhone() : "Telefone n�o cadastrado" %></li>
		</ul>
		
		Reservas:<p>
		<ul>
<%		
		if( bookings != null )
		{
			for(Booking booking : bookings )
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
			<li>Cliente sem reservas.</li>
<%			
		}
%>
		</ul>


</body>
</html>