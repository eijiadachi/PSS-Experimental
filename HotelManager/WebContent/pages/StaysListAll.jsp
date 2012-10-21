<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="br.inf.pucrio.hotel.model.*" %>
<%@ page import="br.inf.pucrio.hotel.HotelConstants" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ocupações</title>
</head>
<body>
<h1> Ocupações Cadastradas</h1>
	<%
		Object object = session.getAttribute( HotelConstants.ALL_STAYS_ATTR );
		if( object != null )
		{
			List<Booking> allStays = (List<Booking>)object;
			for(Booking stay : allStays )
			{
	%>
		<span class="bookingId">Ocupação <%= stay.getId() %></span>
		<%= stay.toHtml() %><p>
	<%	
			}
		}
	%>
</body>
</html>