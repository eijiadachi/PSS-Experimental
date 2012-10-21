<%@page import="br.inf.pucrio.hotel.model.Booking"%>
<%@page import="br.inf.pucrio.hotel.HotelConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmação</title>
</head>
<body>

<%
	Booking stayBooked = (Booking)session.getAttribute( HotelConstants.RESULT_STAY_BOOKED_ATTR );
	String stayBookedHtml = stayBooked.toHtml();
	Integer bookingId = stayBooked.getId();
	session.setAttribute( HotelConstants.STAY_BOOKED_ID, bookingId );
	
%>

	<h1>Confirmação</h1>
	<h2>Os dados abaixo estão corretos?</h2>
	<%= stayBookedHtml %>

	<s:form action="ConfirmStayBooked">
		<s:submit value="Submit"/>
		<s:submit action="Welcome" value="Cancel" onclick="form.onsubmit=null"/>
	</s:form>

</body>
</html>