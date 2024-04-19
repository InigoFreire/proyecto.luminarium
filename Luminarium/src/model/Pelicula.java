package model;

public class Pelicula {
	private String id;
	private Genero genero;
	private String titulo;
	private int pegi;
	private int duracion;
	private String sinopsis;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getPegi() {
		return pegi;
	}
	public void setPegi(int pegi) {
		this.pegi = pegi;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	
}
