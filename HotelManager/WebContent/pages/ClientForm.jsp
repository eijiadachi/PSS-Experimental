<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page import="br.inf.pucrio.hotel.HotelConstants" %>
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Cliente</title>
</head>
<body>
	<h3> Cadastro de Cliente </h3>

	<s:if test="hasActionErrors()">
		<div class="error">
			<s:actionerror/>
		</div>
	</s:if>

	<s:div id="clientRegister">
		<s:form action="AddClient" validate="true">
			<s:textfield name="client.name" label="Nome do Cliente"/>
			<s:textfield name="client.cpf" label="CPF"/>
			<sx:datetimepicker name="client.birthday" label="Data de Nascimento" displayFormat="dd/MM/yyyy"/>
			<s:textfield name="client.address" label="Endere�o"/>
			<s:textfield name="client.phone" label="Telefone"/>
			<s:submit value="Submit" targets="clientRegister" notifyTopics="/ajaxloginCancel"/>
			<s:submit action="Welcome" value="Cancel" onclick="form.onsubmit=null"/>
		</s:form>
	</s:div>
</body>
</html>