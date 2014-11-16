package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import servlet.ConexionBD;

public class TiposServicios {
	private String[][] tipoServicios; 
	private int index = -1;
	public TiposServicios() {

		Connection con = ConexionBD.CrearConexion();

		try {
			Statement stm1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rset= stm1.executeQuery("SELECT IdTipoServicio, Descripcion FROM TIPO_SERVICIOS ORDER BY Descripcion;");
			// Sacamos el numeros de filas
			rset.last();
			int numFilas = rset.getRow();
			// Hay que volver al principio, es decir, justo antes del primer registro
			rset.beforeFirst();
			tipoServicios = new String[numFilas][2];
			for(int i=0;rset.next();i++) { 
				tipoServicios[i][0] = rset.getString(1);
				tipoServicios[i][1] =  rset.getString(2);
			}

			rset.close();
			ConexionBD.CerrarConexion(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean getNext() {
		index++;
		if(index >= tipoServicios.length) {
			// Reiniciamos 
			index = -1;
			return false;
		}
		return true;
	}

	public String getTipoServicio() {
		return tipoServicios[index][1];
	}

	public String getIdTipoServicio() {
		return tipoServicios[index][0];
	}
}
