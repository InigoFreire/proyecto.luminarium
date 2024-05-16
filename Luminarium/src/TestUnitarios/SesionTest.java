package TestUnitarios;

import model.Sesion;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta clase contiene pruebas unitarias para la clase Sesion.
 */
public class SesionTest {

    /**
     * Prueba el constructor de la clase Sesion y los métodos getters.
     */
    @Test
    void testConstructorAndGetters() {
        Sesion sesion = new Sesion();
        sesion.setId("1");
        sesion.setPrecio(10.5);
        sesion.setFecha(LocalDateTime.now());
        sesion.setIdPelicula("2");
        sesion.setIdSala("A1");
        sesion.setTicketRestante(50);

        assertEquals("1", sesion.getId());
        assertEquals(10.5, sesion.getPrecio());
        assertNotNull(sesion.getFecha());
        assertEquals("2", sesion.getIdPelicula());
        assertEquals("A1", sesion.getIdSala());
        assertEquals(50, sesion.getTicketRestante());
    }

    /**
     * Prueba los métodos setters de la clase Sesion.
     */
    @Test
    void testSetters() {
        Sesion sesion = new Sesion();
        sesion.setId("2");
        sesion.setPrecio(8.0);
        sesion.setFecha(LocalDateTime.now().plusDays(1));
        sesion.setIdPelicula("3");
        sesion.setIdSala("B2");
        sesion.setTicketRestante(100);

        assertEquals("2", sesion.getId());
        assertEquals(8.0, sesion.getPrecio());
        assertNotNull(sesion.getFecha());
        assertEquals("3", sesion.getIdPelicula());
        assertEquals("B2", sesion.getIdSala());
        assertEquals(100, sesion.getTicketRestante());
    }
}


