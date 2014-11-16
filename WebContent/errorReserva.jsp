<%@page import="bean.ReservaException"%>
<%@page import="bean.LoginException"%>
<%@page import="bean.ConexionBDException"%>
<%@ page isErrorPage="true" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="datos.Servicio"%>
<%@page import="datos.Subservicio"%>
<%@ page import="datos.DispServ"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TeleRes</title>
<link href="3regiones.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="cabecera.jsp"%>
<%@include file="sidebar.jsp"%>

<div id="errorReserva">
<h1 class="error">No se pudo llevar a cabo la solicitud: </h1>
<%if (exception.getClass() == LoginException.class) {%>
	<p class="mensaje"> 
		El login con el que se ha intentado hacer la reserva no existe <br>
		<a href="WebRes">Pulse para volver a su página de inicio</a>
	</p>
<%} else if(exception.getClass() == ReservaException.class ) {%>
	<p class="mensaje">
		El servicio o subservicio ha sido reservado previamente por otra persona o no existe <br>
		<a href="WebRes">Pulse para volver a su página de inicio</a>
	</p>
<%} else if(exception.getClass() == ConexionBDException.class ) {%>
	<p class="mensaje"> 
		Servicio de reservas no disponible en estos momentos inténtelo más tarde <br>
		<a href="WebRes">Pulse para volver a su página de inicio</a>
	</p>
	<%} %>

</div>
</body>
</html>