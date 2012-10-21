<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="br.inf.pucrio.hotel.model.*" %>
<%@ page import="br.inf.pucrio.hotel.HotelConstants" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quartos cadastrados</title>
</head>
<body>
	<h1>Quartos cadastrados</h1>
	<%
		Object object = session.getAttribute( HotelConstants.ALL_ROOMS_ATTR );
		if( object != null )
		{
			List<Room> allRooms = (List<Room>)object;
			for(Room room : allRooms )
			{
	%>
		<%= room.toHtml() %><p>
	<%	
			}
		}
	%>
</body>
</html>