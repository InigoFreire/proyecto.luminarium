package TestUnitarios;

import model.Sala;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta clase contiene pruebas unitarias para la clase Sala.
 */
public class SalaTest {

    /**
     * Prueba el constructor de la clase Sala y los métodos getters.
     */
    @Test
    void testConstructorAndGetters() {
        Sala sala = new Sala();
        sala.setId("A1");
        sala.setAforo(100);

        assertEquals("A1", sala.getId());
        assertEquals(100, sala.getAforo());
    }

    /**
     * Prueba los métodos setters de la clase Sala.
     */
    @Test
    void testSetters() {
        Sala sala = new Sala();
        sala.setId("B2");
        sala.setAforo(80);

        assertEquals("B2", sala.getId());
        assertEquals(80, sala.getAforo());
    }
}


