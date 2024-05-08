package TestUnitarios;

import model.Tabla;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaTest {

    @Test
    void testGetRowCount() {
        String[][] data = {{"1", "Titanic"}, {"2", "Superbad"}};
        String[] columnas = {"ID", "Título"};
        Tabla tabla = new Tabla(data, columnas);

        assertEquals(2, tabla.getRowCount());
    }

    @Test
    void testGetColumnCount() {
        String[][] data = {{"1", "Titanic"}, {"2", "Superbad"}};
        String[] columnas = {"ID", "Título"};
        Tabla tabla = new Tabla(data, columnas);

        assertEquals(2, tabla.getColumnCount());
    }

    @Test
    void testGetValueAt() {
        String[][] data = {{"1", "Titanic"}, {"2", "Superbad"}};
        String[] columnas = {"ID", "Título"};
        Tabla tabla = new Tabla(data, columnas);

        assertEquals("1", tabla.getValueAt(0, 0));
        assertEquals("Titanic", tabla.getValueAt(0, 1));
        assertEquals("2", tabla.getValueAt(1, 0));
        assertEquals("Superbad", tabla.getValueAt(1, 1));
    }

    @Test
    void testIsCellEditable() {
        String[][] data = {{"1", "Titanic"}, {"2", "Superbad"}};
        String[] columnas = {"ID", "Título"};
        Tabla tabla = new Tabla(data, columnas);

        assertFalse(tabla.isCellEditable(0, 0));
        assertFalse(tabla.isCellEditable(1, 1));
    }

    @Test
    void testGetColumnName() {
        String[][] data = {{"1", "Titanic"}, {"2", "Superbad"}};
        String[] columnas = {"ID", "Título"};
        Tabla tabla = new Tabla(data, columnas);

        assertEquals("ID", tabla.getColumnName(0));
        assertEquals("Título", tabla.getColumnName(1));
    }
}

