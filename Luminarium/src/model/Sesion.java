package model;

import java.time.LocalDateTime;

public class Sesion {
<<<<<<< HEAD
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
=======
    private String id;
    private double precio;
    private LocalDateTime fecha;
    private String idPelicula;
    private String idSala;

    public Sesion() {

    }

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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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

    @Override
    public String toString() {
        return "Sesion [id=" + id + ", precio=" + precio + ", fecha=" + fecha + ", idPelicula=" + idPelicula + ", idSala=" + idSala + "]";
    }
}
>>>>>>> aitziber
