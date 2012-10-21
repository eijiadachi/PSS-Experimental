<%@page import="br.inf.pucrio.hotel.HotelConstants"%>
<%@page import="br.inf.pucrio.hotel.model.HotelReportResult"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relatório Hotel</title>
</head>
<body>

<%
	
	final HotelReportResult result = (HotelReportResult)session.getAttribute( HotelConstants.RESULT_HOTEL_REPORT_ATTR );
	Integer roomsTotal = result.getRoomsTotal();
	Integer roomsOccupied = result.getRoomsOccupied();
	Float incomingOfMonth = result.getIncomingOfMonth();
	Integer guestsOfMonth = result.getGuestsOfMonth();
%>

<h1>
Relatório
</h1>
	<ul>
		<li>Total de quartos: <%= roomsTotal %></li>
		<li>Quartos ocupados: <%= roomsOccupied %></li>
		<li>Valor total recebido no mês: <%= incomingOfMonth %></li>
		<li>Hóspedes recebidos no mês: <%= guestsOfMonth %></li>
	</ul>

</body>
</html>