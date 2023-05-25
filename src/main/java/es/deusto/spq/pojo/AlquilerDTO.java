package es.deusto.spq.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class AlquilerDTO {
	
	private String libronombre,librodesc;
	private String usuario;
	private String fecha_compra;
	
	
	
	/**
	 * constructor vacio
	 */
	public AlquilerDTO() {
		
	}
	
	/**
	 * constructor con parametros
	 * @param libro
	 * @param usuario
	 * @param fecha
	 */
	public AlquilerDTO(String libronombre,String librodesc, String usuario, String fecha) {
		super();
		this.libronombre = libronombre;
		this.librodesc = librodesc;
		this.usuario = usuario;
		this.fecha_compra = fecha;
	}
	
	public String getLibroNombre() {
		return libronombre;
	}
	public void setLibroNombre(String libro) {
		this.libronombre = libro;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFecha_compra() {
		return fecha_compra;
	}
	public void setFecha_compra(String fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public String getLibrodesc() {
		return librodesc;
	}

	public void setLibrodesc(String librodesc) {
		this.librodesc = librodesc;
	}
	
}
