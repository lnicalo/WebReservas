<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorReserva.jsp"%>
<%@ page import="bean.GestorReservas" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TeleRes</title>
<link href="3regiones.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="cabecera.jsp" %>
<%@include file="sidebar.jsp" %>

<div id="ReservaOK">

	<%
	String idDisponibilidad = request.getParameter("idDisponibilidad");
	String login = request.getRemoteUser();
	
	GestorReservas gr = new GestorReservas();
	gr.reservar(idDisponibilidad, login); 
	%>

	<p class="mensaje">
		Reserva realizada.
		<a href="WebRes">Pulse para volver a su página de inicio</a>
	</p>
</div>
</body>
</html>