package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import servlet.ConexionBD;

public class GestorReservas {
	
	private Hashtable<String,Vector<Reserva>> reservas = new Hashtable<String,Vector<Reserva>>();
	
	public void setDataGestor(String login, String ano, String mes) {
		
		Connection con = ConexionBD.CrearConexion();
		
		if (mes.length()==1) {
			mes = "0" + mes;
		}
		String fechainicio = ano + "/" + mes + "/" + "01";
		String fechafin = ano + "/" + mes + "/" + "31"; // Se pone 31 aunque no tenga 31 dias
														// vale para todos los casos
		
		try {
			Statement stm1 = con.createStatement();
			ResultSet rset= stm1.executeQuery("SELECT IdServicio, IdSubServicio, Dia, Hora, Estado, Login FROM " +
					"DISPONIBILIDAD_SERVICIOS WHERE Dia BETWEEN '" +
					fechainicio + "' AND '" + fechafin + "' AND IdServicio IN (SELECT " +
					"IdServicio FROM USUARIOS WHERE Login ='" + login +"');");
					
			while(rset.next()) {
				  int servicio = rset.getInt(1);
				  String dia = rset.getString(3);
				  int subservicio = rset.getInt(2);
				  String hora = rset.getString(4);
				  String estado = rset.getString(5);
				  String login_user = rset.getString(6);				  
				  
				  Reserva r = new Reserva(servicio, subservicio, hora, estado, login_user, dia);
				  Vector<Reserva> v;
				  if ((v = reservas.get(dia)) == null) {
					  v = new Vector<Reserva>();
					  reservas.put(dia, v);
				  }
				  
				  v.add(r);
				
				}
			
			ConexionBD.CerrarConexion(con);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void setDataPublico(String login, String ano, String mes) {
		
		Connection con = ConexionBD.CrearConexion();
		
		if (mes.length()==1) {
			mes = "0" + mes;
		}
		
		String fechainicio = ano + "/" + mes + "/" + "01";
		String fechafin = ano + "/" + mes + "/" + "31"; // Se pone 31 aunque no tenga 31 dias
														// vale para todos los casos
		
		try {
			Statement stm1 = con.createStatement();
			ResultSet rset= stm1.executeQuery("SELECT IdDisponibilidad, IdServicio, IdSubServicio, Dia, Hora, Estado, Login FROM " +
					"DISPONIBILIDAD_SERVICIOS WHERE Login ='" + login +"' AND Dia BETWEEN '" +
					fechainicio + "' AND '" + fechafin + "';");
					
			while(rset.next()) {
				String idDisponibilidad = rset.getString(1);
				int idServicio = rset.getInt(2);
				int idSubservicio = rset.getInt(3);
				String dia = rset.getString(4);
				String hora = rset.getString(5);
				String estado = rset.getString(6);
				String login_user = rset.getString(7);				  
			  
				Reserva r = new Reserva(idDisponibilidad, idServicio, idSubservicio, dia, hora, estado, login_user);
				Vector<Reserva> v;
				if ((v = reservas.get(dia)) == null) {
					v = new Vector<Reserva>();
					reservas.put(dia, v);
				}
				  
				v.add(r);
				
			}
			
			ConexionBD.CerrarConexion(con);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void setDataServicio(String idServicio, String ano, String mes) {
		Connection con = ConexionBD.CrearConexion();
		
		// Codigo para obtener la fecha de inicio y de final que tienen que abarcar 2 meses
		int a = Integer.parseInt(ano);
		int m = Integer.parseInt(mes);
		
		GregorianCalendar c = new GregorianCalendar();	
		c.set(GregorianCalendar.MONTH, m);
		c.set(GregorianCalendar.YEAR, a);
		if (mes.length()==1) {
			mes = "0" + mes;
		}
		
		String fechainicio = ano + "/" + mes + "/" + "01";
		
		c.add(GregorianCalendar.MONTH, 1);
		ano = c.get(GregorianCalendar.YEAR) + "";
		mes = (c.get(GregorianCalendar.YEAR)+ 1) + "";
		if (mes.length()==1) {
			mes = "0" + mes;
		}
		String fechafin = ano + "/" + mes + "/" + "31"; // Se pone 31 aunque no tenga 31 dias
														// vale para todos los casos
		
		try {
			Statement stm1 = con.createStatement();
			ResultSet rset= stm1.executeQuery("SELECT IdDisponibilidad, IdServicio, IdSubServicio, Dia, Hora, Estado, Login FROM " +
					"DISPONIBILIDAD_SERVICIOS WHERE idServicio = " + idServicio + " AND Dia BETWEEN '" +
					fechainicio + "' AND '" + fechafin + "';");
					
			while(rset.next()) {
				String idDisponibilidad = rset.getString(1);
				int servicio = rset.getInt(2);
				int idSubservicio = rset.getInt(3);
				String dia = rset.getString(4);
				String hora = rset.getString(5);
				String estado = rset.getString(6);
				String login_user = rset.getString(7);			  
			  
				Reserva r = new Reserva(idDisponibilidad, servicio, idSubservicio, dia, hora, estado, login_user);
				Vector<Reserva> v;
				if ((v = reservas.get(dia)) == null) {
					v = new Vector<Reserva>();
					reservas.put(dia, v);
				}
				  
				v.add(r);
				
			}
			
			ConexionBD.CerrarConexion(con);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void setDataSubServicio(String idSubServicio, String ano, String mes) {
		Connection con = ConexionBD.CrearConexion();
		
		// Codigo para obtener la fecha de inicio y de final que tienen que abarcar 2 meses
		int a = Integer.parseInt(ano);
		int m = Integer.parseInt(mes);
		
		GregorianCalendar c = new GregorianCalendar();	
		c.set(GregorianCalendar.MONTH, m);
		c.set(GregorianCalendar.YEAR, a);
		if (mes.length()==1) {
			mes = "0" + mes;
		}
		
		String fechainicio = ano + "/" + mes + "/" + "01";
		
		c.add(GregorianCalendar.MONTH, 1);
		ano = c.get(GregorianCalendar.YEAR) + "";
		mes = (c.get(GregorianCalendar.YEAR)+ 1) + "";
		if (mes.length()==1) {
			mes = "0" + mes;
		}
		String fechafin = ano + "/" + mes + "/" + "31"; // Se pone 31 aunque no tenga 31 dias
														// vale para todos los casos
		
		try {
			Statement stm1 = con.createStatement();
			ResultSet rset= stm1.executeQuery("SELECT IdDisponibilidad, IdServicio, IdSubServicio, Dia, Hora, Estado, Login FROM " +
					"DISPONIBILIDAD_SERVICIOS WHERE idSubServicio = " + idSubServicio + " AND Dia BETWEEN '" +
					fechainicio + "' AND '" + fechafin + "';");
					
			while(rset.next()) {
				String idDisponibilidad = rset.getString(1);
				int servicio = rset.getInt(2);
				String dia = rset.getString(4);
				int subservicio = rset.getInt(3);
				String hora = rset.getString(5);
				String estado = rset.getString(6);
				String login_user = rset.getString(7);				  
			  
				Reserva r = new Reserva(idDisponibilidad, servicio, subservicio, dia, hora, estado, login_user);
				Vector<Reserva> v;
				if ((v = reservas.get(dia)) == null) {
					v = new Vector<Reserva>();
					reservas.put(dia, v);
				}
				  
				v.add(r);
				
			}
			
			ConexionBD.CerrarConexion(con);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void reservar(String idDisponibilidad, String login) throws ReservaException, LoginException, ConexionBDException {
		Connection con = ConexionBD.CrearConexion();
		try {
			// Comprobamos que el idDisponibilidad es correcto
			try {
				Integer.parseInt(idDisponibilidad);
			} catch (NumberFormatException e) {
				throw new ReservaException();
			}
			Statement stm = con.createStatement();
			// El login no es nulo y existe en la base de datos
			if(login == null) {
				throw new LoginException();
			}
			ResultSet rset = stm.executeQuery("SELECT idUsuario FROM USUARIOS WHERE Login = '" + login + "';");
			if(!rset.next()) {
				throw new LoginException();
			}
			
			// Se comprueba que antes no haya sido reservado
			rset = stm.executeQuery("SELECT Estado FROM DISPONIBILIDAD_SERVICIOS WHERE IdDisponibilidad = " + idDisponibilidad + ";");
			if(rset.next()) {
				if(rset.getString(1).compareTo("Ocupado") != 0) {
					int m = stm.executeUpdate("UPDATE DISPONIBILIDAD_SERVICIOS SET Estado='Ocupado', Login = '" +
							login + "' WHERE IdDisponibilidad = " + idDisponibilidad + ";");
					if(m!=1) {
						throw new ReservaException();
					}
				} else {
					throw new ReservaException(); 
				}
						
			}			
			else {
				// No existe el idDisponibilidad solicitado
				throw new ReservaException();
			}
			ConexionBD.CerrarConexion(con);
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ConexionBDException();
		}
	}
	
	public Vector<Reserva> getReserva(String Dia) {
				
		return reservas.get(Dia);	
	}
}
