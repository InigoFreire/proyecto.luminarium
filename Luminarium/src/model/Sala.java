package model;

/**
 * Representa una sala en el sistema.
 * Cada sala tiene un identificador único y un aforo máximo que indica la capacidad de la sala.
 */
public class Sala {
    private String id;
    private int aforo;
    
    /**
     * Crea una nueva instancia de Sala sin inicializar sus atributos.
     */
    public Sala(){
        
    }
    
    /**
     * Obtiene el identificador único de la sala.
     * 
     * @return El identificador único de la sala.
     */
    public String getId() {
        return id;
    }
    
    /**
     * Establece el identificador único de la sala.
     * 
     * @param id El identificador único de la sala.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Obtiene el aforo máximo de la sala, que indica la capacidad de la sala.
     * 
     * @return El aforo máximo de la sala.
     */
    public int getAforo() {
        return aforo;
    }
    
    /**
     * Establece el aforo máximo de la sala, que indica la capacidad de la sala.
     * 
     * @param aforo El aforo máximo de la sala.
     */
    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    /**
     * Retorna una representación en forma de cadena de la sala.
     * 
     * @return Una cadena que representa la sala.
     */
    @Override
    public String toString() {
        return "Sala [id=" + id + ", aforo=" + aforo + "]";
    }
    
}
