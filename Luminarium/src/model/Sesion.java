package model;

import java.time.LocalDateTime;

public class Sesion {
    private String id;
    private double precio;
    private LocalDateTime fecha;
    private int idPelicula;
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

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
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
