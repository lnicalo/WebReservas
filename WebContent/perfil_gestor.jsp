<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bean.InfoPerfil" %>
<%@ page import="java.util.Vector" %>
<jsp:useBean class="bean.InfoPerfil" id="bean_perfil" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TeleRes</title>
<link href="2regiones.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="cabecera.jsp" %>
<div id="perfil_gestor">
	<p class="perfil"> Perfil de <%=bean_perfil.getlogin()%></p>
	<p class="saludo">¡¡Hola <%=bean_perfil.getNombre()%>!!</p>
	
	<% switch (bean_perfil.getIdUsuario()) {
		case(9): %> <img class="imagen" src="Images/luisfe.JPG"> <% break;
		case(11): %> <img class="imagen" src="Images/luisfe.JPG"> <% break;
		case(15): %> <img class="imagen" src="Images/conan.jpg"> <% break;
		case(16): %> <img class="imagen" src="Images/ronaldo.jpeg"> <% break;
		case(17): %> <img class="imagen" src="Images/panoramix.gif"> <% break;
		default: %>  <img class="imagen" src="Images/mister-x.jpg">	<% break;
		}
	%>
	
	<p class="servicios"> Eres el jefe de los siguientes servicios: <br>
	<% Vector<String> v = new Vector<String>(); 
		v=bean_perfil.getNomServ();
		for (int i=0; i<v.size(); i++) {%>
			<%=v.get(i)%><br><%
		}
	%>
	</p>
	
	<p class="reservas"> Tienes  <%=bean_perfil.getNumReservas() %> reservas a tu 
						cargo a partir de hoy. 
	</p>
	
	<p class="reserva"> 
	<% if (bean_perfil.getReserva() == null) {
		%> Ninguna de las reservas a tu cargo está reservada<%
	}
	else {%>
		La próxima reserva de la que tienes que ocuparte es: <br>
		Servicio: <%=bean_perfil.getReserva().getNombreServ() %> <br>
		Subservicio: <%=bean_perfil.getReserva().getDesSubServ() %> <br>
		Dia: <%=bean_perfil.getReserva().getDia() %> <br>
		Hora: <%=bean_perfil.getReserva().getHora() %>	<br>
		Cliente: <%=bean_perfil.getReserva().getLogin() %>
	<% }%>
	</p>
	<% if (bean_perfil.getConflicto() == 1) { %>
			 <span class="cuidado">¡Cuidado: tienes varias reservas de las que ocuparte a la vez!<br>
			 ¡Revisa tu calendario de reservas!</span> <%
		}
	%>
	
	<p class="inicio"> Pulsa para ir a tu calendario de reservas: 
	<a href="/WebReservas/WebRes"><img class="inicio" src="Images/icono_calendario.jpg"></a>
	<br>
	<br>
	<br>
	<br>
	</p>
		
</div>
</body>
</html>