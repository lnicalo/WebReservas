<%@ page import="datos.TiposServicios" %>
<%@ page import="java.util.*" %>
<jsp:useBean class="bean.DispServBean" id="dispServBean" scope="request" />
<jsp:setProperty property="fechaInicio" name="dispServBean"/>
<jsp:setProperty property="fechaFin" name="dispServBean"/>
<jsp:setProperty property="idTipoServicio" name="dispServBean"/>
<div id="sidenavigation">
	
	<div id="contenido">
	<p class="bus">
	Búsqueda de Servicios
	</p>
		
	<form class= "form" action="DispServ" method="get">
			
			<p class="desp">
			Seleccione un Servicio:
			</p>
			
			<SELECT class="idTipoServicio" NAME="idTipoServicio" > 
				<% 	TiposServicios ts = new TiposServicios();
					while((ts.getNext())) {
					%><OPTION VALUE="<%= ts.getIdTipoServicio()%>"  
						<%if( ts.getIdTipoServicio().compareTo(dispServBean.getIdTipoServicio()) == 0) {%>
						SELECTED
						<% }%>><%= ts.getTipoServicio()%>
						<%
					}
				%>
			</SELECT>
					
			<p class="desp">
			Fecha de inicio:
			</p>
						
			<input class= "fecha" type="text" name="fechaInicio" size="10" value="<jsp:getProperty property="fechaInicio" name="dispServBean"/>"></input>
						
			<p class="desp">
			Fecha de fin:
			</p>
			
			<input class= "fecha" type="text" size="10" name="fechaFin" value="<jsp:getProperty property="fechaFin" name="dispServBean"/>"></input>
						
			<INPUT class="boton" TYPE="submit"  VALUE="Buscar !"> 
			
	</form>
	</div>	
	
</div>