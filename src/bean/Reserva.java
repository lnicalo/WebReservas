package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import servlet.ConexionBD;

public class Reserva {
	private String IdDisponibilidad;
	int IdServicio;
	int IdSubservicio;
	String Hora;
	String Estado;
	String Login;
	String Dia;
	private String NombreServ;
	private String DesSubServ;
	
	public Reserva(String idDisponibilidad, int idServicio, int idSubservicio,
			String dia, String hora, String estado, String login) {
		this.IdDisponibilidad = idDisponibilidad;
		this.IdServicio = idServicio;
		this.IdSubservicio = idSubservicio;
		this.Dia = dia;
		this.Hora = hora;
		this.Estado = estado;
		this.Login = login;
		
		setNombres();
	}
	
	public Reserva(int IdServicio, int IdSubservicio, String Hora, String Estado, String Login, String dia) {
		this.IdServicio=IdServicio;
		this.IdSubservicio=IdSubservicio;
		this.Hora=Hora;
		this.Estado=Estado;
		this.Login=Login;
		this.Dia=dia;
		
		setNombres();		
		
	}
	
	public String getIdDisponibilidad() {
		return IdDisponibilidad;
	}
	
	public int getIdServicio() {
		return this.IdServicio;
	}
	
	public int getIdSubservicio() {
		return this.IdSubservicio;
	}
	
	public String getHora() {
		return this.Hora;
	}
	
	public String getEstado() {
		return this.Estado;
	}
	
	public String getLogin() {
		return this.Login;
	}
	
	public String getDia() {
		return this.Dia;
	}
	
	public String getNombreServ() {
		return this.NombreServ;
	}
	
	public String getDesSubServ() {
		return this.DesSubServ;
	}
	
	public boolean isReservado() {
		if(Estado.compareTo("Ocupado") == 0) 
			return true;
		else
			return false;
	}
	
	private void setNombres() {
		
		try {
			Connection con = ConexionBD.CrearConexion();
			Statement stm1 = con.createStatement();
		
			ResultSet rset1= stm1.executeQuery("SELECT Nombre FROM " +
				"SERVICIOS WHERE IdServicio =" + this.IdServicio +";");
		
			if (rset1.next())
			this.NombreServ = rset1.getString(1);
		
			rset1= stm1.executeQuery("SELECT Descripción FROM " +
				"SUBSERVICIOS WHERE IdServicio =" + this.IdServicio + " AND " +
				"IdSubServicio = " + this.IdSubservicio + ";");
			
			if (rset1.next())
			this.DesSubServ = rset1.getString(1);
			
			ConexionBD.CerrarConexion(con);
		
		}
		catch(SQLException e) {

			e.printStackTrace();
		}
		
	}
	
}
