
<div id="calendario">
	<%@ page import="bean.*" %>
	<jsp:useBean class="bean.GestorReservas" id="bean" scope="request" />
	<%@ page import="java.util.*" %>
	
	<p class="titulo2">
		CALENDARIO DE RESERVAS
	</p>

	<%GregorianCalendar cal = new GregorianCalendar();	
	cal.set(GregorianCalendar.DATE, 1);
	int mes=cal.get(Calendar.MONTH) + 1;
	if(request.getParameter("Mes") != null && request.getParameter("Ano") != null) {
		mes = Integer.parseInt(request.getParameter("Mes"));
		int ano = Integer.parseInt(request.getParameter("Ano"));
		cal.set(GregorianCalendar.MONTH, mes-1);
		cal.set(GregorianCalendar.YEAR, ano);
	}	
	%>
	
	<form class="formulario" name="fechas" action="">

		<table border="1" cellspacing="0" cellpadding="2">
		<tr>
			<td>

			<select name="Mes">
				<option value="01">Enero</option>
				<option value="02">Febrero</option>
				<option value="03">Marzo</option>
				<option value="04">Abril</option>
				<option value="05">Mayo</option>
				<option value="06">Junio</option>
				<option value="07">Julio</option>
				<option value="08">Agosto</option>
				<option value="09">Septiembre</option>
				<option value="10">Octubre</option>
				<option value="11">Noviembre</option>
				<option value="12">Diciembre</option>
			</select>

			<select name="Ano">
				<option value="2010">2010</option>
				<option value="2011">2011</option>
				<option value="2012">2012</option>
			</select>
			<INPUT TYPE="SUBMIT" VALUE="Ver">
			</td>
		</tr>
		</table>

	</form>
	
	<table class="calendario" border='1' cellpadding='1' cellspacing='0'>
	<caption>
		<% switch (mes) {
			case 1:  %>Enero <% break;
			case 2:  %>Febrero <% break;
			case 3:  %>Marzo <% break;
			case 4:  %>Abril <% break;
			case 5:  %>Mayo <% break;
			case 6:  %>Junio <% break;
			case 7:  %>Julio <% break;
			case 8:  %>Agosto <% break;
			case 9:  %>Septiembre <% break;
			case 10: %>Octubre <% break;
			case 11: %>Noviembre <% break;
			case 12: %>Diciembre <% break; 
		}%>del
		<%=cal.get(Calendar.YEAR) %>
	</caption>	

	<tr>
		<th align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>Lunes</th>
		<th align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>Martes</th>
		<th align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>Miércoles</th>
		<th align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>Jueves</th>
		<th align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>Viernes</th>
		<th align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>Sábado</th>
		<th align='center' style='FONT-FAMILY:Arial;FONT-SIZE:12px;FONT-WEIGHT: bold'>Domingo</th>
	</tr>

	<%
	int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
	dayWeek=dayWeek-1;
	if (dayWeek==0)
		dayWeek=7;
	
	String d="0";
	String m="0";
	String dia;
	int i = 1;
	
	for (int ifila=0; ifila < 6; ifila ++) {
	%>
	<tr>
	<%	
		for (int icol=0; icol < 7; icol ++) { 
			if (ifila == 0 && icol+1 < dayWeek) {
				%><td>
				</td><%
			}
			else if (i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
				%><td>
				<%=i %><br>
				<% 
				if((cal.get(Calendar.MONTH)+1) < 10) {
					m = "0" + (cal.get(Calendar.MONTH)+1);
				}
				else {
					m = "" + (cal.get(Calendar.MONTH)+1);
				}
				if(i < 10) {
					d = "0" + i;
					dia= cal.get(Calendar.YEAR) + "/" + m + "/" + d;
				} else {
					dia= cal.get(Calendar.YEAR) + "/" + m + "/" + i;
				}
				
				Vector<Reserva> v=bean.getReserva(dia);
				if (v!=null) {
					for (int j=0; j<v.size(); j++) {
						%><%=v.get(j).getNombreServ()  %><br>
						<%=v.get(j).getDesSubServ() %><br>
						Hora: <%=v.get(j).getHora() %><br>
						Estado: <%=v.get(j).getEstado() %><br>
						<% if (v.get(j).getLogin() != null) { %>
							Cliente: <%=v.get(j).getLogin() %><br> <%
						}
					}
				}
				%> </td> <%
				i++;
			}
			%>
	<% 	} %>
	</tr>
	<% 
	} %>	
	</table>

</div>
