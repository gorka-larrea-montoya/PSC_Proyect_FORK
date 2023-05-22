package es.deusto.spq.server.jdo;

import java.awt.RenderingHints.Key;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.identity.*;

import es.deusto.spq.pojo.LibroDTO;


@PersistenceCapable
public class Compra {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	private Long id;
	@Persistent
	private Float libroprecio;
	private String libronombre, librodesc;
	private String usuario;

	/**
	 * constructor vacio
	 */
	public Compra() {
		
	}
	
	/**
	 * constructor con los siguientes parametros
	 * @param libro
	 * @param usuario
	 */
	public Compra(Libro libro, String usuario) {
		super();

		this.usuario = usuario;
		this.libronombre = libro.getNombre();
		this.librodesc = libro.getDescripccion();
		this.libroprecio = libro.getPrecio();
	}
	public Libro getLibro() {
		Libro libro = new Libro(libronombre,librodesc,libroprecio,"Compra");
		
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libronombre = libro.getNombre();
		this.librodesc = libro.getDescripccion();
		this.libroprecio = libro.getPrecio();
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
}
