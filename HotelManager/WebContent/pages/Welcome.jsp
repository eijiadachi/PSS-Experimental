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
    	<a href="<s:url action="ClientForm"/>">Cadastrar Novo Cliente</a>
    </li>
	<li>
    	<a href="<s:url action="ListAllClients"/>">Listar Todos Clientes</a>
    </li>
    <li>
    	<a href="<s:url action="ListAllRooms"/>">Listar Todos Quartos</a>
    </li>
</ul>

</body>
</html>
