<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta de Quarto</title>
</head>
<body>
	<h3> Consulta de Quarto </h3>

	<s:if test="hasActionErrors()">
		<div class="error">
			<s:actionerror/>
		</div>
	</s:if>

	<s:div id="roomSearch">
		<s:form action="RoomSearch" validate="false">
			<s:textfield name="id" label="Código do Quarto"/>
			<s:submit value="Submit" targets="roomSearch" notifyTopics="/ajaxloginCancel"/>
			<s:submit action="Welcome" value="Cancel" onclick="form.onsubmit=null"/>
		</s:form>
	</s:div>
</body>
</html>