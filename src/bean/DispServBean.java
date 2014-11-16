package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import datos.IdTipoServicioException;
import datos.TiposServicios;

import servlet.ConexionBD;

public class DispServBean {
	private String fechaInicio;
	private String fechaFin;
	private String idTipoServicio;
	
	public DispServBean() {
		GregorianCalendar gc = new GregorianCalendar();
		String hoy = gc.get(GregorianCalendar.DATE) + "/" +
			(gc.get(GregorianCalendar.MONTH) + 1) +"/" + gc.get(GregorianCalendar.YEAR);
		fechaInicio = hoy;
		fechaFin = hoy;
		
		// Damos un idTipoServicio valido por defecto.
		// Tomamos el primero de la lista
		TiposServicios ts = new TiposServicios();
		if(ts.getNext()) {
			idTipoServicio = ts.getIdTipoServicio();
		}
		else {
			// No deberia llegar nunca aqui
			idTipoServicio = null;
		}
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getIdTipoServicio() {
		return idTipoServicio;
	}
	public void setIdTipoServicio(String idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}
	public String getDescripcion() throws IdTipoServicioException {
		Connection con = ConexionBD.CrearConexion();
		try {
			Integer.parseInt(idTipoServicio);
		}catch (NumberFormatException e) {
			throw new IdTipoServicioException();
		}
		
		try {
			Statement stm = con.createStatement();
			ResultSet rset= stm.executeQuery("SELECT Descripcion FROM TIPO_SERVICIOS WHERE IdTipoServicio = " + idTipoServicio + " ;");
			if(rset.next()) {
				String descripcion = rset.getString(1);
				rset.close();
				stm.close();
				ConexionBD.CerrarConexion(con);
				return descripcion;
			} else {
				rset.close();
				stm.close();
				ConexionBD.CerrarConexion(con);
				throw new IdTipoServicioException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error base de datos";
		}		
	}
}
