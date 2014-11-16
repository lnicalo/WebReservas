<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bean.InfoPerfil" %>
<jsp:useBean class="bean.InfoPerfil" id="bean_perfil" scope="request" />
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
<div id="perfil_publico">
	<p class="perfil"> Perfil de <%=bean_perfil.getlogin()%></p>
	<p class="saludo">¡¡Hola <%=bean_perfil.getNombre()%>!!</p>
	
	<% switch (bean_perfil.getIdUsuario()) {
		case(12): %> <img class="imagen" src="Images/alvaro.JPG"> <% break;
		case(18): %> <img class="imagen" src="Images/anibal.JPG"> <% break;
		default: %>  <img class="imagen" src="Images/mister-x.jpg">	<% break;
		}
	%>
	
	<p class="reservas"> Llevas un total de <%=bean_perfil.getNumReservas() %> reservas </p>
	
	<p class="reserva"> Tu próxima reserva es: <br>
	<% if (bean_perfil.getReserva() == null) {
		%> No tienes reservas<%
	}
	else {%>
		Servicio: <%=bean_perfil.getReserva().getNombreServ() %> <br>
		Subservicio: <%=bean_perfil.getReserva().getDesSubServ() %> <br>
		Dia: <%=bean_perfil.getReserva().getDia() %> <br>
		Hora: <%=bean_perfil.getReserva().getHora() %>	<br>
	<% }%>
    </p>
    <% if (bean_perfil.getConflicto() == 1) { %>
		 <span class="cuidado">¡Cuidado: tienes varias reservas a la vez!<br>
		 ¡Revisa tu calendario de reservas!</span> <%
	}
	%>
	
	<p class="inicio"> Pulsa para ir a tu calendario de reservas: </p>
	<a href="/WebReservas/WebRes"><img class="inicio" src="Images/icono_calendario.jpg"></a>  
	
</div>
</body>
</html>