package model;

/**
 * Representa una película en el sistema.
 * Cada película tiene un identificador único, género, título, clasificación PEGI, duración en minutos y una sinopsis.
 */
public class Pelicula {
    private String id;
    private Genero genero;
    private String titulo;
    private int pegi;
    private int duracion;
    private String sinopsis;
    
    /**
     * Crea una nueva instancia de Pelicula sin inicializar sus atributos.
     */
    public Pelicula() {
        
    }
    
    /**
     * Crea una nueva instancia de Pelicula con todos sus atributos inicializados.
     * 
     * @param id       El identificador único de la película.
     * @param genero   El género de la película.
     * @param titulo   El título de la película.
     * @param pegi     La clasificación PEGI de la película.
     * @param duracion La duración en minutos de la película.
     * @param sinopsis La sinopsis de la película.
     */
    public Pelicula(String id, Genero genero, String titulo, int pegi, int duracion, String sinopsis) {
        this.id = id;
        this.genero = genero;
        this.titulo = titulo;
        this.pegi = pegi;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
    }
    
    // Getters & Setters
    
    /**
     * Obtiene el identificador único de la película.
     * 
     * @return El identificador único de la película.
     */
    public String getId() {
        return id;
    }
    
    /**
     * Establece el identificador único de la película.
     * 
     * @param id El identificador único de la película.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Obtiene el género de la película.
     * 
     * @return El género de la película.
     */
    public Genero getGenero() {
        return genero;
    }
    
    /**
     * Establece el género de la película.
     * 
     * @param genero El género de la película.
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    /**
     * Obtiene el título de la película.
     * 
     * @return El título de la película.
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Establece el título de la película.
     * 
     * @param titulo El título de la película.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Obtiene la clasificación PEGI de la película.
     * 
     * @return La clasificación PEGI de la película.
     */
    public int getPegi() {
        return pegi;
    }
    
    /**
     * Establece la clasificación PEGI de la película.
     * 
     * @param pegi La clasificación PEGI de la película.
     */
    public void setPegi(int pegi) {
        this.pegi = pegi;
    }
    
    /**
     * Obtiene la duración en minutos de la película.
     * 
     * @return La duración en minutos de la película.
     */
    public int getDuracion() {
        return duracion;
    }
    
    /**
     * Establece la duración en minutos de la película.
     * 
     * @param duracion La duración en minutos de la película.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    /**
     * Obtiene la sinopsis de la película.
     * 
     * @return La sinopsis de la película.
     */
    public String getSinopsis() {
        return sinopsis;
    }
    
    /**
     * Establece la sinopsis de la película.
     * 
     * @param sinopsis La sinopsis de la película.
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    
    /**
     * Retorna una representación en forma de cadena de la película.
     * 
     * @return Una cadena que representa la película.
     */
    @Override
    public String toString() {
        return "Pelicula [id=" + id + ", genero=" + genero + ", titulo=" + titulo + ", pegi=" + pegi + ", duracion="
                + duracion + ", sinopsis=" + sinopsis + "]";
    }   
}

