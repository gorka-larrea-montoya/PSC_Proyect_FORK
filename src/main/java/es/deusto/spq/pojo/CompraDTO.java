package es.deusto.spq.pojo;

public class CompraDTO {
	private Float libroprecio;
	private String libronombre, librodesc;
	private String usuario;
	public CompraDTO() {
		
	}
	
	/**
	 * constructor con los siguientes parametros
	 * @param libro
	 * @param usuario
	 */
	public CompraDTO(LibroDTO libro, String usuario) {
		super();

		this.usuario = usuario;
		this.libronombre = libro.getNombre();
		this.librodesc = libro.getDescripccion();
		this.libroprecio = libro.getPrecio();
	}
	public CompraDTO(String libronombre, String librodesc, Float libroprecio, String usuario) {
		super();
		this.usuario = usuario;
		this.libronombre = libronombre;
		this.librodesc = librodesc;
		this.libroprecio = libroprecio;
		
	}
	
	public LibroDTO getLibroDTO() {
		LibroDTO libro = new LibroDTO(libronombre,librodesc,libroprecio,"Compra");
		
		return libro;
	}
	public void setLibro(LibroDTO libro) {
		this.libronombre = libro.getNombre();
		this.librodesc = libro.getDescripccion();
		this.libroprecio = libro.getPrecio();
	}
	public String getUsuario() {
		return usuario;
	}
	@Override
	public String toString() {
		return "Compra [libro=" + libronombre + ", usuario=" + usuario + "]";
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Float getLibroprecio() {
		return libroprecio;
	}

	public void setLibroprecio(Float libroprecio) {
		this.libroprecio = libroprecio;
	}

	public String getLibronombre() {
		return libronombre;
	}

	public void setLibronombre(String libronombre) {
		this.libronombre = libronombre;
	}

	public String getLibrodesc() {
		return librodesc;
	}

	public void setLibrodesc(String librodesc) {
		this.librodesc = librodesc;
	}


	
}
