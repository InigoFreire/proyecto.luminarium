package model;

import java.time.LocalDate;

public class Sesion {
	private String id;
	private double precio;
	private LocalDate fecha;
	
	public Sesion() {
		this.id="";
		this.precio=0;
		this.fecha=null;
	}

	// Getters & Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}