package TestUnitarios;

import model.Usuario;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta clase contiene pruebas unitarias para la clase Usuario.
 */
public class UsuarioTest {

    /**
     * Prueba el constructor y los getters de la clase Usuario.
     */
    @Test
    void testConstructorAndGetters() {
        Usuario usuario = new Usuario("12345678A", "Password", "Juan", "Pérez", "juan@example.com");

        assertEquals("12345678A", usuario.getDni());
        assertEquals("Password", usuario.getPassword());
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Pérez", usuario.getApellido());
        assertEquals("juan@example.com", usuario.getEmail());
        assertEquals("", usuario.getMetodoPago());
        assertNull(usuario.getFechaCaducidadTarjeta());
        assertFalse(usuario.isAdminCheck());
    }

    /**
     * Prueba los setters de la clase Usuario.
     */
    @Test
    void testSetters() {
        Usuario usuario = new Usuario();
        usuario.setDni("98765432B");
        usuario.setPassword("123456");
        usuario.setNombre("María");
        usuario.setApellido("García");
        usuario.setEmail("maria@example.com");
        usuario.setMetodoPago("Visa");
        usuario.setFechaCaducidadTarjeta(YearMonth.of(2025, 12));
        usuario.setAdminCheck(true);

        assertEquals("98765432B", usuario.getDni());
        assertEquals("123456", usuario.getPassword());
        assertEquals("María", usuario.getNombre());
        assertEquals("García", usuario.getApellido());
        assertEquals("maria@example.com", usuario.getEmail());
        assertEquals("Visa", usuario.getMetodoPago());
        assertEquals(YearMonth.of(2025, 12), usuario.getFechaCaducidadTarjeta());
        assertTrue(usuario.isAdminCheck());
    }
}
