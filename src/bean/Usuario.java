package bean;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private int cedula;
	private String nombre;
	
	public Usuario() {}
	
	public Usuario(int cedula, String nombre) {
		this.cedula = cedula;
		this.nombre = nombre;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return cedula + " - " + nombre;
	}
	
}
