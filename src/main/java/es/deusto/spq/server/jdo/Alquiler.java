package es.deusto.spq.server.jdo;

import java.util.Date;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import es.deusto.spq.pojo.LibroDTO;
import es.deusto.spq.pojo.Usuario;

@PersistenceCapable(detachable="true")
@Join
public class Alquiler {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	private Long id;
	@Persistent
	private Float libroprecio;
	private String libronombre,librodesc;
	private String usuario; 
	@Persistent(defaultFetchGroup = "true")
	private String fecha_compra;
	
	

	public Alquiler() {
		
	}
	
	public Alquiler(String libronombre, String usuario, String fecha_compra) {
		super();
		this.libronombre = libronombre;
		this.usuario = usuario;
		this.fecha_compra = fecha_compra;
	}
	
	public String getLibronombre() {
		return libronombre;
	}
	public void setLibronombre(String libronombre) {
		this.libronombre = libronombre;
	}
	public Float getLibroprecio() {
		return libroprecio;
	}

	public void setLibroprecio(Float libroprecio) {
		this.libroprecio = libroprecio;
	}

	public String getLibrodesc() {
		return librodesc;
	}

	public void setLibrodesc(String librodesc) {
		this.librodesc = librodesc;
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
	
}
