<%@page import="datos.IdTipoServicioException"%>
<%@page import="datos.FechaException"%>
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

<div id="errordispServ">
<h1 class="error">No se pudo llevar a cabo la solicitud: </h1>
<%if (exception.getClass() == FechaException.class) {%>
	<p class="mensaje"> 
		El formato de la fecha es incorrecto:
		Escriba: dd/mm/aaaa Por ejemplo: 12 de Enero de 2010 sería 12/01/2010 <br>
		<a href="WebRes">Pulse para volver a su página de inicio</a>		
	</p>
<%} else if(exception.getClass() == IdTipoServicioException.class ) {%>
	<p class="mensaje"> 
		El identificador del servicio es incorrecto. Esto puede ser debido a que haya modificado 
		la url. Para solucionarlo vuelva a <a href="WebRes"> su página de inicio</a>
		<%} %>
	</p>
</div>
</body>
</html>