package TestUnitarios;

import model.Sala;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaTest {

    @Test
    void testConstructorAndGetters() {
        Sala sala = new Sala();
        sala.setId("A1");
        sala.setAforo(100);

        assertEquals("A1", sala.getId());
        assertEquals(100, sala.getAforo());
    }

    @Test
    void testSetters() {
        Sala sala = new Sala();
        sala.setId("B2");
        sala.setAforo(80);

        assertEquals("B2", sala.getId());
        assertEquals(80, sala.getAforo());
    }
}

