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
	String checkin = stay.getCheckinStr();
	String checkout = stay.getCheckoutStr();
%>

	<h1>Detalhes da estadia:</h1>
	
	<ul>
		<li>Estadia: <%= stay.toHtml() %></li>
		<li>Número de diárias <%= result.getDays(  ) %></li>
		<li>Valor total da estadia <%= result.getPrice() %></li>
	</ul>

</body>
</html>