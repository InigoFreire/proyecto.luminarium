package TestUnitarios;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import model.Compra;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta clase contiene pruebas unitarias para la clase Compra.
 */
public class CompraTest {

    /**
     * Prueba el constructor por defecto de la clase Compra y los métodos getters.
     */
    @Test
    void testConstructorAndGetters() {
        Compra compra = new Compra();
        compra.setIdTicket(1);
        compra.setDniUsuario("12345678A");
        compra.setIdPelicula("P001");
        compra.setIdSala("Sala1");
        compra.setFecha(LocalDate.of(2024, 5, 14));
        compra.setPrecio(10.5);

        assertEquals(1, compra.getIdTicket());
        assertEquals("12345678A", compra.getDniUsuario());
        assertEquals("P001", compra.getIdPelicula());
        assertEquals("Sala1", compra.getIdSala());
        assertEquals(LocalDate.of(2024, 5, 14), compra.getFecha());
        assertEquals(10.5, compra.getPrecio());
    }

    /**
     * Prueba los métodos setters de la clase Compra.
     */
    @Test
    void testSetters() {
        Compra compra = new Compra();
        compra.setIdTicket(2);
        compra.setDniUsuario("87654321B");
        compra.setIdPelicula("P002");
        compra.setIdSala("Sala2");
        compra.setFecha(LocalDate.of(2024, 5, 15));
        compra.setPrecio(12.75);

        assertEquals(2, compra.getIdTicket());
        assertEquals("87654321B", compra.getDniUsuario());
        assertEquals("P002", compra.getIdPelicula());
        assertEquals("Sala2", compra.getIdSala());
        assertEquals(LocalDate.of(2024, 5, 15), compra.getFecha());
        assertEquals(12.75, compra.getPrecio());
    }
}
