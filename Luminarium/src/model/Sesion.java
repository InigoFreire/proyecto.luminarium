package model;

import java.time.LocalDateTime;

/**
 * Representa una sesión en el sistema.
 * Cada sesión tiene un identificador único, un precio, una fecha y hora de proyección, 
 * el identificador de la película que se proyectará, el identificador de la sala en la que se proyectará
 * y el número de tickets restantes para la sesión.
 */
public class Sesion {
    private String id;
    private double precio;
    private LocalDateTime fecha;
    private String idPelicula;
    private String idSala;
    private int ticketRestante;
    
    /**
     * Crea una nueva instancia de Sesion sin inicializar sus atributos.
     */
    public Sesion() {
        
    }
    
    // Getters & Setters
    
    /**
     * Obtiene el identificador único de la sesión.
     * 
     * @return El identificador único de la sesión.
     */
    public String getId() {
        return id;
    }
    
    /**
     * Establece el identificador único de la sesión.
     * 
     * @param id El identificador único de la sesión.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Obtiene el precio de la sesión.
     * 
     * @return El precio de la sesión.
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * Establece el precio de la sesión.
     * 
     * @param precio El precio de la sesión.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Obtiene la fecha y hora de la sesión.
     * 
     * @return La fecha y hora de la sesión.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    /**
     * Establece la fecha y hora de la sesión.
     * 
     * @param fecha La fecha y hora de la sesión.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Obtiene el identificador de la película que se proyectará en la sesión.
     * 
     * @return El identificador de la película.
     */
    public String getIdPelicula() {
        return idPelicula;
    }
    
    /**
     * Establece el identificador de la película que se proyectará en la sesión.
     * 
     * @param idPelicula El identificador de la película.
     */
    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }
    
    /**
     * Obtiene el identificador de la sala en la que se proyectará la sesión.
     * 
     * @return El identificador de la sala.
     */
    public String getIdSala() {
        return idSala;
    }
    
    /**
     * Establece el identificador de la sala en la que se proyectará la sesión.
     * 
     * @param idSala El identificador de la sala.
     */
    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }
    
    /**
     * Obtiene el número de tickets restantes para la sesión.
     * 
     * @return El número de tickets restantes.
     */
    public int getTicketRestante() {
        return ticketRestante;
    }
    
    /**
     * Establece el número de tickets restantes para la sesión.
     * 
     * @param ticketRestante El número de tickets restantes.
     */
    public void setTicketRestante(int ticketRestante) {
        this.ticketRestante = ticketRestante;
    }
    
    /**
     * Retorna una representación en forma de cadena de la sesión.
     * 
     * @return Una cadena que representa la sesión.
     */
    @Override
    public String toString() {
        return "Sesion [id=" + id + ", precio=" + precio + ", fecha=" + fecha + ", idPelicula=" + idPelicula
                + ", idSala=" + idSala + ", ticketRestante=" + ticketRestante + "]";
    }   
}

