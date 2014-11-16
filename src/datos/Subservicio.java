package datos;

public class Subservicio {
	private String IdSubservicio;
	private String IdServicio;
	private String Descripcion;
	private String Capacidad;
	
	public Subservicio(String idSubservicio, String idServicio,
			String descripcion, String capacidad) {
		IdSubservicio = idSubservicio;
		IdServicio = idServicio;
		Descripcion = descripcion;
		Capacidad = capacidad;
	}

	public String getIdSubservicio() {
		return IdSubservicio;
	}

	public void setIdSubservicio(String idSubservicio) {
		IdSubservicio = idSubservicio;
	}

	public String getIdServicio() {
		return IdServicio;
	}

	public void setIdServicio(String idServicio) {
		IdServicio = idServicio;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getCapacidad() {
		return Capacidad;
	}

	public void setCapacidad(String capacidad) {
		Capacidad = capacidad;
	}
	
}
