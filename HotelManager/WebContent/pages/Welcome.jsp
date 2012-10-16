<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Welcome</title>
    <link href="<s:url value="/css/examplecss"/>" rel="stylesheet"
          type="text/css"/>
</head>

<body>

<h1>
	
</h1>

<ul>
    <li>
    	<a href="<s:url action="HotelReport"/>">Gerar Relatório do Hotel</a>
    </li>
    <li>
    	<a href="<s:url action="ClientSearch"/>">Buscar por Cliente</a>
    </li>
    <li>
    	<a href="<s:url action="RoomSearch"/>">Buscar por Quarto</a>
    </li>
</ul>

</body>
</html>
