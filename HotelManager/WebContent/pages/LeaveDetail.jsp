<%@page import="java.util.Date"%>
<%@page import="br.inf.pucrio.hotel.model.*"%>
<%@page import="br.inf.pucrio.hotel.HotelConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalhes da estadia</title>
</head>
<body>

<%
	LeaveResult result = (LeaveResult)session.getAttribute( HotelConstants.RESULT_LEAVE_ATTR );
	Booking stay = result.getStay();
	Client client = stay.getClient();
	Room room = stay.getRoom();
	Date checkin = stay.getCheckin();
	Date checkout = stay.getCheckout();
%>

	Detalhes da estadia:<p>
	
	<ul>
		<li>C�digo do ciente <%= client.getId(  ) %></li>
		<li>Nome do ciente <%= client.getName(  ) %></li>
		<li>N�mero do quarto <%= room.getId(  ) %></li>
		<li>Tipo do quarto <%= room.getCategory(  ) %></li>
		<li>Valor da di�ria <%= room.getPrice(  ) %></li>
		<li>Data de entrada <%= checkin.toString() %></li>
		<li>Data de sa�da <%= checkout.toString() %></li>
		<li>N�mero de h�spedes <%= stay.getGuests(  ) %></li>
		<li>N�mero de di�rias <%= result.getDays(  ) %></li>
		<li>Valor total da estadia <%= result.getPrice() %></li>
	</ul>

</body>
</html>