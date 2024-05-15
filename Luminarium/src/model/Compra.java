package model;

import java.time.LocalDate;

/**
 * La clase Compra representa una transacción de compra de tickets para una película en una sala de cine en una fecha específica.
 */
public class Compra {
    private int idTicket;
    private String dniUsuario;
    private String idPelicula;
    private String idSala;
    private LocalDate fecha;
    private double precio;
    
    /**
     * Crea una nueva instancia de Compra con valores predeterminados.
     */
    public Compra() {
        this.idTicket = 0;
        this.dniUsuario = "";
        this.idPelicula = "";
        this.idSala = "";
        this.fecha = null;
        this.precio = 0;
    }

    // Getters & Setters

    /**
     * Obtiene el ID del ticket de la compra.
     * 
     * @return El ID del ticket.
     */
    public int getIdTicket() {
        return idTicket;
    }

    /**
     * Establece el ID del ticket de la compra.
     * 
     * @param idTicket El ID del ticket.
     */
    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    /**
     * Obtiene el DNI del usuario que realizó la compra.
     * 
     * @return El DNI del usuario.
     */
    public String getDniUsuario() {
        return dniUsuario;
    }

    /**
     * Establece el DNI del usuario que realizó la compra.
     * 
     * @param dniUsuario El DNI del usuario.
     */
    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    /**
     * Obtiene el ID de la película comprada.
     * 
     * @return El ID de la película.
     */
    public String getIdPelicula() {
        return idPelicula;
    }

    /**
     * Establece el ID de la película comprada.
     * 
     * @param idPelicula El ID de la película.
     */
    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    /**
     * Obtiene el ID de la sala donde se realizó la compra.
     * 
     * @return El ID de la sala.
     */
    public String getIdSala() {
        return idSala;
    }

    /**
     * Establece el ID de la sala donde se realizó la compra.
     * 
     * @param idSala El ID de la sala.
     */
    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    /**
     * Obtiene la fecha en que se realizó la compra.
     * 
     * @return La fecha de la compra.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en que se realizó la compra.
     * 
     * @param fecha La fecha de la compra.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el precio de la compra.
     * 
     * @return El precio de la compra.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la compra.
     * 
     * @param precio El precio de la compra.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }   
}
