<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Cliente</title>
</head>
<body>
	<h3> Cadastro de Cliente </h3>
	
	<s:div id="clientRegister">
		<s:form action="ClientRegister" validate="true">
			<s:textfield name="clientName" label="Nome do Cliente"/>
			<s:textfield name="cpf" label="CPF"/>
			<s:textfield name="birthday" label="Data de Nascimento"/>
			<s:textfield name="address" label="Endereço"/>
			<s:textfield name="phone" label="Telefone"/>
			<s:submit value="Submit" targets="loginDiv" notifyTopics="/ajaxloginCancel"/>
			<s:submit action="ClientForm" value="Cancel" onclick="form.onsubmit=null"/>
		</s:form>
	</s:div>
</body>
</html>