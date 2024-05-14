package model;

import java.time.LocalDate;

public class Compra {
	private int idTicket;
	private String dniUsuario;
	private String idPelicula;
	private String idSala;
	private LocalDate fecha;
	private double precio;
	
	public Compra() {
		this.idTicket=0;
		this.dniUsuario="";
		this.idPelicula="";
		this.idSala="";
		this.fecha=null;
		this.precio=0;
	}

	// Getters & Setters
	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public String getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(String idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getIdSala() {
		return idSala;
	}

	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}	
}