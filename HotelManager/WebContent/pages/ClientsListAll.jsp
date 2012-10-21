<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="br.inf.pucrio.hotel.model.*" %>
<%@ page import="br.inf.pucrio.hotel.HotelConstants" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clientes cadastrados</title>
</head>
<body>
	
	<%
	Object object = session.getAttribute( HotelConstants.ALL_CLIENTS_ATTR );
	if( object != null )
	{
%>
	<h1>Clientes cadastrados</h1>
<%
		List<Client> allClients = (List<Client>)object;
		for(Client client : allClients )
		{
			Integer id = client.getId();
			String name = client.getName();
			String cpf = client.getCpf();
			String phone = client.getPhone();
			String address = client.getAddress();
			Date birthday = client.getBirthday();
			String birthdayStr = HotelConstants.DATE_FORMATTER.format( birthday );
%>
	<%= client.toHtml() %>
<%
		}//for
	}//if
	%>
</body>
</html>