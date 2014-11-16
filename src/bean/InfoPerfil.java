package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import servlet.ConexionBD;

public class InfoPerfil {
	
	private String login;
	private int IdUsuario;
	private int NumReservas;
	private String Nombre;
	private Reserva r;
	private Vector<String> NomServ = new Vector<String>();
	private int conflicto;
	
	public void setDataPublico(String login) {
		
			Connection con = ConexionBD.CrearConexion();
			try {
				
				this.login=login;
				
				Statement stm1 = con.createStatement();
				ResultSet rset1= stm1.executeQuery("SELECT IdUsuario, Nombre FROM " +
					"USUARIOS WHERE Login ='" + login +"';");
			
				rset1.next();
				this.IdUsuario = rset1.getInt(1);
				this.Nombre = rset1.getString(2);				

				rset1= stm1.executeQuery("SELECT COUNT(*) FROM " +
					"DISPONIBILIDAD_SERVICIOS WHERE Login ='" + login +"';");
				
				if (rset1.next()) {
					this.NumReservas = rset1.getInt(1);
				}
				
				GregorianCalendar ahora = new GregorianCalendar();
				
				String mes;
				String d;
				String h = "" + ahora.get(Calendar.HOUR_OF_DAY);
				String minuto = "" + ahora.get(Calendar.MINUTE);
											
				if ((ahora.get(Calendar.MONTH)+1) < 10) {
					mes = "0" + (ahora.get(Calendar.MONTH)+1);
				}
				else {
					mes = "" + (ahora.get(Calendar.MONTH)+1);
				}
				
				if ((ahora.get(Calendar.DAY_OF_MONTH)) < 10) {
					d = "0" + (ahora.get(Calendar.DAY_OF_MONTH));
				}
				else {
					d = "" + (ahora.get(Calendar.DAY_OF_MONTH));
				}
				
				if(h.length() < 2) {
				     h = "0" + h;
				}
				
				if(minuto.length() < 2) {
				     minuto = "0" + minuto;
				}
				
				String hoy = ahora.get(Calendar.YEAR) + "/" + mes + "/" + d;
				String now = h + minuto;
				
				//Primero miramos si hay reservas para hoy a partir de la hora del sistema
				rset1 = stm1.executeQuery("SELECT IdServicio, IdSubServicio, Dia, Hora, Estado, Login FROM " +
					"DISPONIBILIDAD_SERVICIOS WHERE Dia = '" + hoy + "' AND " +
					"Login ='" + login +"' AND Hora = (SELECT MIN(Hora) FROM " +
					"DISPONIBILIDAD_SERVICIOS WHERE Dia= '" + hoy + "' AND Login " +
					"= '" + login + "' AND Hora >= '" + now + "');");
											
				if (rset1.next()) {
					//Si hay reservas para hoy a partir de la hora del sistema
					int servicio = rset1.getInt(1);
					int subservicio = rset1.getInt(2);
					String dia = rset1.getString(3);
					String hora = rset1.getString(4);
					String estado = rset1.getString(5);
					String login_user = rset1.getString(6);
					this.r = new Reserva(servicio, subservicio, hora, estado, login_user, dia);
					if (rset1.next()) {
						this.conflicto = 1;
					}
					else{
						this.conflicto = 0;
					}
				} else {
					// No hay reservas para hoy, hay que mirar otros dias
					rset1 = stm1.executeQuery("SELECT IdServicio, IdSubServicio, Dia, " +
					"Hora, Estado, Login FROM DISPONIBILIDAD_SERVICIOS WHERE " +
					"Login = '" + login + "' AND Dia = (SELECT MIN(Dia) FROM " +
					"DISPONIBILIDAD_SERVICIOS WHERE Dia > '" + hoy + "' " +
					"AND Login = '" + login + "') AND Hora = (SELECT MIN(Hora) " +
					"FROM DISPONIBILIDAD_SERVICIOS WHERE Dia = (SELECT MIN(Dia) " +
					"FROM DISPONIBILIDAD_SERVICIOS WHERE Dia > '" + hoy + "' " +
					"AND Login = '" + login + "') AND Login = '" + login + "')");
					
					if (rset1.next()) {
						int servicio = rset1.getInt(1);
						int subservicio = rset1.getInt(2);
						String dia = rset1.getString(3);
						String hora = rset1.getString(4);
						String estado = rset1.getString(5);
						String login_user = rset1.getString(6);
						this.r = new Reserva(servicio, subservicio, hora, estado, login_user, dia);
						if (rset1.next()) {
							this.conflicto = 1;
						}
						else{
							this.conflicto = 0;
						}
					}
					
				}
				
				
				ConexionBD.CerrarConexion(con);

			}
			catch(SQLException e) {

				e.printStackTrace();
			}
	}
	
	public void setDataGestor(String login) {
		
		Connection con = ConexionBD.CrearConexion();
		try {
			
			this.login=login;
			
			Statement stm1 = con.createStatement();
			ResultSet rset1= stm1.executeQuery("SELECT IdUsuario, Nombre FROM " +
				"USUARIOS WHERE Login ='" + login +"';");
		
			rset1.next();
				this.IdUsuario = rset1.getInt(1);
				this.Nombre = rset1.getString(2);
				
			rset1= stm1.executeQuery("SELECT Nombre FROM " +
						"SERVICIOS WHERE IdServicio IN (SELECT " +
					"IdServicio FROM USUARIOS WHERE Login ='" + login +"');");
			
			while(rset1.next()) {
				this.NomServ.add(rset1.getString(1));
			}

			GregorianCalendar ahora = new GregorianCalendar();
			
			String mes;
			String d;
			String h = "" + ahora.get(Calendar.HOUR_OF_DAY);
			String minuto = "" + ahora.get(Calendar.MINUTE);
			
			if ((ahora.get(Calendar.MONTH)+1) < 10) {
				mes = "0" + (ahora.get(Calendar.MONTH)+1);
			}
			else {
				mes = "" + (ahora.get(Calendar.MONTH)+1);
			}
			
			if ((ahora.get(Calendar.DAY_OF_MONTH)) < 10) {
				d = "0" + (ahora.get(Calendar.DAY_OF_MONTH));
			}
			else {
				d = "" + (ahora.get(Calendar.DAY_OF_MONTH));
			}
			
			if(h.length() < 2) {
			     h = "0" + h;
			}
			
			if(minuto.length() < 2) {
			     minuto = "0" + minuto;
			}
			
			String hoy = ahora.get(Calendar.YEAR) + "/" + mes + "/" + d;
			String now = h + minuto;
			
			rset1= stm1.executeQuery("SELECT COUNT(*) FROM " +
			"DISPONIBILIDAD_SERVICIOS WHERE Dia >= '" + hoy + "'" +
			" AND IdServicio IN (SELECT IdServicio FROM USUARIOS WHERE " +
			"Login ='" + login + "');");
				
			if (rset1.next()) {
			this.NumReservas = rset1.getInt(1);
			}
			
			//Primero miramos si hay reservas para hoy a partir de la hora del sistema
			rset1 = stm1.executeQuery("SELECT IdServicio, IdSubServicio, Dia, Hora, Estado, Login FROM " +
			"DISPONIBILIDAD_SERVICIOS WHERE Dia = '" + hoy + "' AND Estado = 'Ocupado' " +
			"AND IdServicio IN (SELECT IdServicio FROM USUARIOS WHERE " +
			"Login = '" + login + "') AND Hora = (SELECT MIN(Hora) FROM " +
			"DISPONIBILIDAD_SERVICIOS WHERE Hora > '" + now + "' AND Dia = " +
			"'" + hoy + "' AND Estado = 'Ocupado' AND IdServicio IN " +
			"(SELECT IdServicio FROM USUARIOS WHERE Login = '" + login + "'));");
							
					
			if (rset1.next()) {
				//Si hay reservas para hoy a partir de la hora del sistema
				int servicio = rset1.getInt(1);
				int subservicio = rset1.getInt(2);
				String dia = rset1.getString(3);
				String hora = rset1.getString(4);
				String estado = rset1.getString(5);
				String login_user = rset1.getString(6);
				this.r = new Reserva(servicio, subservicio, hora, estado, login_user, dia);
				if (rset1.next()) {
					this.conflicto = 1;
				}
				else{
					this.conflicto = 0;
				}
			} else {
				// No hay reservas para hoy, hay que mirar otros dias
				rset1 = stm1.executeQuery("SELECT IdServicio, IdSubServicio, Dia, " +
				"Hora, Estado, Login FROM DISPONIBILIDAD_SERVICIOS WHERE " +
				"Estado = 'Ocupado' AND IdServicio IN (SELECT IdServicio FROM " +
				"USUARIOS WHERE Login = '" + login + "') AND Dia = " +
				"(SELECT MIN(Dia) FROM DISPONIBILIDAD_SERVICIOS WHERE " +
				"Dia > '" + hoy + "' AND Estado = 'Ocupado' AND " +
				"IdServicio IN (SELECT IdServicio FROM USUARIOS WHERE Login " +
				"= '" + login + "')) AND Hora = (SELECT MIN(Hora) " +
				"FROM DISPONIBILIDAD_SERVICIOS WHERE Dia = (SELECT MIN(Dia) " +
				"FROM DISPONIBILIDAD_SERVICIOS WHERE Dia > '" + hoy + "' " +
				"AND Estado = 'Ocupado' AND IdServicio IN (SELECT IdServicio " +
				"FROM USUARIOS WHERE Login = '" + login + "')) " +
				"AND Estado = 'Ocupado' AND IdServicio IN (SELECT IdServicio " +
				"FROM USUARIOS WHERE Login = '" + login + "'))");
				
				if (rset1.next()) {
					int servicio = rset1.getInt(1);
					int subservicio = rset1.getInt(2);
					String dia = rset1.getString(3);
					String hora = rset1.getString(4);
					String estado = rset1.getString(5);
					String login_user = rset1.getString(6);
					this.r = new Reserva(servicio, subservicio, hora, estado, login_user, dia);
					if (rset1.next()) {
						this.conflicto = 1;
					}
					else{
						this.conflicto = 0;
					}
				}
				
			}
			
			
			ConexionBD.CerrarConexion(con);

		}
		catch(SQLException e) {

			e.printStackTrace();
		}
}
	
	public String getlogin() {
		return this.login;
	}
	
	public int getIdUsuario() {
		return this.IdUsuario;
	}
	
	public int getNumReservas() {
		return this.NumReservas;
	}
	
	public Reserva getReserva() {
		return this.r;
	}
	
	public String getNombre() {
		return this.Nombre;
	}
	
	public Vector<String> getNomServ() {
		return this.NomServ;
	}
	
	public int getConflicto() {
		return this.conflicto;
	}
}
