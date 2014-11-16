package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;


import servlet.ConexionBD;

public class DispServ {
	private Vector<Servicio> dispServ = new Vector<Servicio>();
	
	private int index = -1;

	public DispServ(String idtiposervicio, String fechainicio, String fechafin) throws FechaException, IdTipoServicioException {
		// Damos la vuelta a las fechas inicio y final dd/mm/aaaa => aaaa/mm/dd
		fechainicio = formateoFechas(fechainicio);
		fechafin = formateoFechas(fechafin);
		
		// Comprobamos idtiposervicio
		try {
			Integer.parseInt(idtiposervicio);
		} catch (NumberFormatException e) {
			throw new IdTipoServicioException();
		}
		
		Connection con = ConexionBD.CrearConexion();

		try {
			// Obtenemos los servicios
			Statement stm1 = con.createStatement();
			
			
			ResultSet rset1 = stm1.executeQuery("SELECT IdServicio, Nombre, Descripción, Url, Capacidad FROM SERVICIOS WHERE IdTipoServicio = " + 
					idtiposervicio + " AND IdServicio IN (SELECT IdServicio FROM DISPONIBILIDAD_SERVICIOS WHERE Estado='Libre' AND Dia BETWEEN '" +
					fechainicio + "' AND '" + fechafin + "') ORDER BY Nombre;");

			while(rset1.next()) {
				String idServicio = rset1.getString(1);
				String nombre = rset1.getString(2);
				String descripcion = rset1.getString(3);
				String url = rset1.getString(4);
				String capacidad = rset1.getString(5);
				
				// Para cada servicio introducimos los subservicios
				Statement stm2 = con.createStatement();
				ResultSet rset2 = stm2.executeQuery("SELECT * FROM SUBSERVICIOS TSS WHERE IdSubServicio IN (SELECT IdSubServicio FROM " +
						"DISPONIBILIDAD_SERVICIOS WHERE Dia BETWEEN '" + fechainicio + "' AND '" + fechafin + "' AND Estado = 'libre' AND " +
								"IdServicio = " + idServicio + ");");
				Vector<Subservicio> subServicios = null;
				while(rset2.next()) {
					if (subServicios == null) {
						subServicios = new Vector<Subservicio>();
					}
					String idSubServicio = rset2.getString(1);
					String ssDescripcion = rset2.getString(3);
					String sscapacidad = rset2.getString(4);
					subServicios.add(new Subservicio(idSubServicio, idServicio, ssDescripcion, sscapacidad));
				}
				
				dispServ.add( new Servicio(idServicio, nombre, descripcion, url, capacidad, subServicios) );
				rset2.close();
				stm2.close();
				
			}
			rset1.close();
			stm1.close();
			
			ConexionBD.CerrarConexion(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String formateoFechas(String fecha) throws FechaException {
		// es null?
		if(fecha == null) 
			throw new FechaException();
		
		// Max:dd/mm/aaaa Min: d/m/aa
		if(fecha.length() > 10 || fecha.length() < 6)
			throw new FechaException();
		
		// Tiene que tener tres campos numericos separados por /
		String campos[] = fecha.split("/");
		if(campos.length < 3)
			throw new FechaException();
		try{
			Integer.parseInt(campos[0]);
			Integer.parseInt(campos[1]);
			Integer.parseInt(campos[2]);
		} catch (NumberFormatException e) {
			throw new FechaException();
		}
		
		// Dia
		if(campos[0].length() == 1) {
			campos[0] = "0" + campos[0];
		}
		
		// Mes
		if(campos[1].length() == 1) {
			campos[1] = "0" + campos[1];
		}
		
		// Ano
		if(campos[2].length() == 2) {
			// Pasamos aa => aaaa. Por ejemplo: 10 a 2010
			Calendar c = Calendar.getInstance();
			String ano = c.get(Calendar.YEAR) + "";
			campos[2] = ano.charAt(1) + ano.charAt(2) + campos[2];
		}
		
		// Se presenta la fecha dada la vuelta
		return campos[2] + "/" + campos [1] + "/" + campos[0];
	}

	public boolean getNext() {
		index++;
		if(index >= dispServ.size()) {
			// Reiniciamos 
			index = -1;
			return false;
		}
		return true;
	}
	
	public Servicio getServicio() {
		return dispServ.get(index);
	}
	
	public boolean isEmpty() {
		return dispServ.isEmpty();
	}
}

