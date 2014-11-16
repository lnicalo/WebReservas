<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="errorDispServ.jsp"%>
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

<div id="dispServ">
	<h1 class="dispServTitulo">Servicios de <jsp:getProperty name="dispServBean"
	property="descripcion" /> disponibles entre <jsp:getProperty
	name="dispServBean" property="fechaInicio" /> y <jsp:getProperty
	name="dispServBean" property="fechaFin" /></h1>
	<%
	DispServ dispServ = new DispServ(dispServBean.getIdTipoServicio(),
	dispServBean.getFechaInicio(), dispServBean.getFechaFin());

	if (dispServ.isEmpty()) {
	%>
		<h2 class="servicio">No se produjo ningún resultado</h2>
		<p class="texto">Sugerencia: Emplee un periodo de tiempo más grande</p>
	<%
	}
	else {
		while (dispServ.getNext()) {
			Servicio servicio = dispServ.getServicio();
			%>
			<h2 class="servicio"><%=servicio.getNombre()%></h2>
			<h3 class="enlace"><a href="Ocupacion?idServicio=<%=servicio.getIdServicio()%>"><img class="cal" src="Images/icono_calendario.jpg"></a></h3>
			<p class="texto2"><b><%=servicio.getDescripcion()%></b><br>
			Máximo <b><%=servicio.getCapacidad()%></b> personas</p>
			<%
			while (servicio.getNextSubServicio()) {
				Subservicio subservicio = servicio.getSubservicio();
				%>
				<h3 class="enlace2"><a href="Ocupacion?idSubServicio=<%=subservicio.getIdSubservicio()%>"><img class="cal" src="Images/icono_calendario.jpg"></a></h3>
				<p class="texto3"><em><%=subservicio.getDescripcion()%></em><br>
					Máximo <b><%=subservicio.getCapacidad()%></b> personas</p>
				<%
			}
			%>
			<%if (servicio.getUrl()!= null) { %>
				<p class="url">
					<a href="http://<%=servicio.getUrl()%>"><%=servicio.getUrl()%>
					</a>
				</p><%
			} else { %>
				<p class="url">
					El servicio no tiene página Web
				</p><%
			}
		}
	}
	%>
</div>
</body>
</html>