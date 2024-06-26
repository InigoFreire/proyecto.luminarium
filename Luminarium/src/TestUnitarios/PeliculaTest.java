package TestUnitarios;

import model.Genero;
import model.Pelicula;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta clase contiene pruebas unitarias para la clase Pelicula.
 */
public class PeliculaTest {

    /**
     * Prueba el constructor de la clase Pelicula y los métodos getters.
     */
    @Test
    void testConstructorAndGetters() {
        Genero genero = Genero.valueOf("Accion".toUpperCase());
        Pelicula pelicula = new Pelicula("1", genero, "Titanic", 12, 180, "Una gran historia de amor");

        assertEquals("1", pelicula.getId());
        assertEquals("Accion".toUpperCase(), pelicula.getGenero().toString());
        assertEquals("Titanic", pelicula.getTitulo());
        assertEquals(12, pelicula.getPegi());
        assertEquals(180, pelicula.getDuracion());
        assertEquals("Una gran historia de amor", pelicula.getSinopsis());
    }

    /**
     * Prueba los métodos setters de la clase Pelicula.
     */
    @Test
    void testSetters() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId("2");
        pelicula.setGenero(Genero.valueOf("Comedia".toUpperCase()));
        pelicula.setTitulo("Superbad");
        pelicula.setPegi(18);
        pelicula.setDuracion(120);
        pelicula.setSinopsis("Dos amigos viven una noche épica antes de ir a la universidad");

        assertEquals("2", pelicula.getId());
        assertEquals("Comedia".toUpperCase(), pelicula.getGenero().toString());
        assertEquals("Superbad", pelicula.getTitulo());
        assertEquals(18, pelicula.getPegi());
        assertEquals(120, pelicula.getDuracion());
        assertEquals("Dos amigos viven una noche épica antes de ir a la universidad", pelicula.getSinopsis());
    }
}
