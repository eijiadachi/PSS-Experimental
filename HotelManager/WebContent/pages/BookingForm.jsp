<%@page import="br.inf.pucrio.hotel.HotelManagerFacade"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page import="br.inf.pucrio.hotel.HotelConstants" %>
<%@ page import="br.inf.pucrio.hotel.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Reserva</title>
</head>
<body>
	<h3> Cadastro de Reserva </h3>

	<s:if test="hasActionErrors()">
		<div class="error">
			<s:actionerror/>
		</div>
	</s:if>

	<s:div id="bookingRegister">
		<s:form action="AddBooking" validate="true">
			<s:textfield name="clientCode" label="Código do Cliente"/>
			<s:textfield name="roomNumber" label="Número do Quarto"/>
			<sx:datetimepicker name="booking.checkin" label="Data de Checkin" displayFormat="dd/MM/yyyy"/>
			<sx:datetimepicker name="booking.checkout" label="Data de Checkout" displayFormat="dd/MM/yyyy"/>
			<s:textfield name="booking.guests" label="Número de Hóspedes"/>
			
			<s:submit value="Submit" targets="bookingRegister" notifyTopics="/ajaxloginCancel"/>
			<s:submit action="Welcome" value="Cancel" onclick="form.onsubmit=null"/>
		</s:form>
	</s:div>
</body>
</html>