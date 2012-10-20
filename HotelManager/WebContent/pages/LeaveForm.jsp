<%@page import="br.inf.pucrio.hotel.HotelManagerFacade"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="br.inf.pucrio.hotel.HotelConstants" %>
<%@ page import="br.inf.pucrio.hotel.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Realizar Check-Out</title>
</head>
<body>
	<h3> Realizar Check-Out </h3>

	<s:if test="hasActionErrors()">
		<div class="error">
			<s:actionerror/>
		</div>
	</s:if>

	<s:div id="checkoutRegister">
		<s:form action="AddLeave" validate="true">
			<s:textfield name="id" label="Número do Quarto"/>
			
			<s:submit value="Submit" targets="checkinRegister" notifyTopics="/ajaxloginCancel"/>
			<s:submit action="Welcome" value="Cancel" onclick="form.onsubmit=null"/>
		</s:form>
	</s:div>
</body>
</html>