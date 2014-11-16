package datos;

import java.util.Vector;


public class Servicio {
	private String idServicio;
	private String nombre;
	private String descripcion;
	private String url;
	private String capacidad;
	private Vector<Subservicio> subservicios;
	private int index = -1;
	
	public Servicio(String id, String nombre, String descripcion, 
			String url, String capacidad, Vector<Subservicio> subServicios) {
		this.subservicios = subServicios;
		this.setIdServicio(id);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setUrl(url);
		this.setCapacidad(capacidad);	
	}
	
	public boolean getNextSubServicio() {
		if(subservicios == null) {
			return false;
		}
		else {
				index++;
			if(index >= subservicios.size()) {
				// Reiniciamos 
				index = -1;
				return false;
			}
			return true;
		}		
	}
	

	public Subservicio getSubservicio() {
		return subservicios.get(index);
	}

	public void addSubservicio(Subservicio subservicio) {
		this.subservicios.add(subservicio);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getIdServicio() {
		return idServicio;
	}
	
	

}
