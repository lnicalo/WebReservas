package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConexionBD {
	static final String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" ;
	static final String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	public static Connection CrearConexion() {
		// Registrar el driver
		try {
			Class.forName(driver);

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: "); 
			System.err.println(e.getMessage());
		}
		// Realizar la conexión
		try {
			Context initctx = new InitialContext();
			Context env = (Context) initctx.lookup("java:comp/env");
			
			String dir = (String) env.lookup("BaseDatos");
			String d = url + dir;
			
			return DriverManager.getConnection(d, "SSS", "SSS");
		}
		catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void CerrarConexion(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
