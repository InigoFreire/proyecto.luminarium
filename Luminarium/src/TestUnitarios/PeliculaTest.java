package TestUnitarios;

import model.Genero;
import model.Pelicula;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PeliculaTest {

    @Test
    void testConstructorAndGetters() {
        Genero genero = Genero.valueOf("Accion");
        Pelicula pelicula = new Pelicula("1", genero, "Titanic", 12, 180, "Una gran historia de amor");

        assertEquals("1", pelicula.getId());
        assertEquals(genero, pelicula.getGenero());
        assertEquals("Titanic", pelicula.getTitulo());
        assertEquals(12, pelicula.getPegi());
        assertEquals(180, pelicula.getDuracion());
        assertEquals("Una gran historia de amor", pelicula.getSinopsis());
    }

    @Test
    void testSetters() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId("2");
        pelicula.setGenero(Genero.valueOf("Comedia"));
        pelicula.setTitulo("Superbad");
        pelicula.setPegi(18);
        pelicula.setDuracion(120);
        pelicula.setSinopsis("Dos amigos viven una noche épica antes de ir a la universidad");

        assertEquals("2", pelicula.getId());
        assertEquals("Comedia", pelicula.getGenero());
        assertEquals("Superbad", pelicula.getTitulo());
        assertEquals(18, pelicula.getPegi());
        assertEquals(120, pelicula.getDuracion());
        assertEquals("Dos amigos viven una noche épica antes de ir a la universidad", pelicula.getSinopsis());
    }
}

