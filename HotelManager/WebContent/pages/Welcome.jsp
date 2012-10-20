<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Welcome</title>
    <link href="<s:url value="/css/examplecss"/>" rel="stylesheet"
          type="text/css"/>
</head>

<body>

<s:if test="hasActionMessages()">
	<div class="confirmation">
		<s:actionmessage/>
	</div>
</s:if>

<ul>
	<li>
    	<a href="<s:url action="ClientForm"/>">Cadastrar Cliente</a>
    </li>
    <li>
    	<a href="<s:url action="BookingForm"/>">Cadastrar Reserva</a>
    </li>
    <li>
    	<a href="<s:url action="StayForm"/>">Cadastrar Ocupação (Fazer Check-In)</a>
    </li>
    <li>
    	<a href="<s:url action="ClientSearchForm"/>">Consultar Cliente</a>
    </li>
	<li>
    	<a href="<s:url action="ListAllClients"/>">Listar Todos Clientes</a>
    </li>
    <li>
    	<a href="<s:url action="ListAllRooms"/>">Listar Todos Quartos</a>
    </li>
    <li>
    	<a href="<s:url action="ListAllBookings"/>">Listar Todas Reservas</a>
    </li>
    <li>
    	<a href="<s:url action="ListAllStays"/>">Listar Todas Ocupações</a>
    </li>
</ul>

</body>
</html>
