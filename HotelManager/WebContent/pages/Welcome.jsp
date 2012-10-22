<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<sx:head />
<title>Welcome</title>
<link href="<s:url value="/css/examplecss"/>" rel="stylesheet" type="text/css"/>
</head>

<body>

<s:if test="hasActionMessages()">
	<div class="confirmation">
		<s:actionmessage/>
	</div>
</s:if>

<sx:tabbedpanel id="tabContainer">
	<sx:div label="Cliente">
		<ul>
			<li>
	    		<a href="<s:url action="ClientForm"/>">Cadastrar Cliente</a>
	    	</li>
	    	<li>
	    		<a href="<s:url action="ClientSearchForm"/>">Consultar Cliente</a>
	    	</li>
	    	<li>
	    		<a href="<s:url action="ListAllClients"/>">Listar Todos Clientes</a>
	    	</li>
		</ul>
	</sx:div>
	<sx:div label="Quartos">
		<ul>
			<li>
		    	<a href="<s:url action="RoomSearchForm"/>">Consultar Quarto</a>
		    </li>
		    <li>
		    	<a href="<s:url action="ListAllRooms"/>">Listar Todos Quartos</a>
		    </li>
		</ul>
	</sx:div>
	<sx:div label="Reservas">
		<ul>
			<li>
		    	<a href="<s:url action="BookingForm"/>">Cadastrar Reserva</a>
		    </li>
		    <li>
		    	<a href="<s:url action="ListAllBookings"/>">Listar Todas Reservas</a>
		    </li>
		</ul>
	</sx:div>
	<sx:div label="Check-In">
		<ul>
			<li>
		    	<a href="<s:url action="StayForm"/>">Realizar Check-In</a>
		    </li>
		    <li>
		    	<a href="<s:url action="StayBookedForm"/>">Realizar Check-In com Reserva Prévia</a>
		    </li>
		</ul>
	</sx:div>
	<sx:div label="Check-Out">
		<ul>
			<li>
		    	<a href="<s:url action="LeaveForm"/>">Realizar Check-Out</a>
		    </li>
		</ul>
	</sx:div>
	<sx:div label="Relatórios">
		<ul>
			<li>
    			<a href="<s:url action="HotelReport"/>">Relatorio do Hotel</a>
    		</li>
		</ul>
	</sx:div>
</sx:tabbedpanel>
    <!--
<ul>
    <li>
    	<a href="<s:url action="ListAllStays"/>">Listar Todas Ocupações</a>
    </li>
</ul>
     -->
     
</body>
</html>
