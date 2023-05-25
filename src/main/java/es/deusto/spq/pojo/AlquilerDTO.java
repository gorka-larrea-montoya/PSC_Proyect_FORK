package es.deusto.spq.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class AlquilerDTO {
	
	private Float libroprecio;
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
	 * @param libronombre
	 * @param librodesc
	 * @param libroprecio
	 * @param usuario
	 * @param fecha
	 */
	public AlquilerDTO(String libronombre,String librodesc,Float libroprecio, String usuario, String fecha) {
		super();
		this.libronombre = libronombre;
		this.librodesc = librodesc;
		this.libroprecio = libroprecio;
		this.usuario = usuario;
		this.fecha_compra = fecha;
	}
	
	public AlquilerDTO(LibroDTO libro, String usuario,String fecha) {
		super();
		this.libronombre = libro.getNombre();
		this.librodesc = libro.getDescripccion();
		this.libroprecio = libro.getPrecio();
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

	public Float getLibroprecio() {
		return libroprecio;
	}

	public void setLibroprecio(Float libroprecio) {
		this.libroprecio = libroprecio;
	}
	
}
