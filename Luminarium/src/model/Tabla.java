package model;

import javax.swing.table.AbstractTableModel;

/**
 * Clase que define un modelo de tabla personalizado para su uso en componentes JTable.
 * Esta clase extiende AbstractTableModel y proporciona implementaciones de los métodos
 * requeridos para definir un modelo de tabla.
 */
public class Tabla extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[][] data;
    private String[] columnas;

    /**
     * Crea una nueva instancia de Tabla con los datos y columnas proporcionados.
     * 
     * @param peliculas Los datos de la tabla.
     * @param columnas  Los nombres de las columnas de la tabla.
     */
    public Tabla(String[][] peliculas, String[] columnas) {
        this.data = peliculas;
        this.columnas = columnas;
    }

    /**
     * Devuelve el número de filas en el modelo de la tabla.
     * 
     * @return El número de filas en la tabla.
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     * Devuelve el número de columnas en el modelo de la tabla.
     * 
     * @return El número de columnas en la tabla.
     */
    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    /**
     * Devuelve el valor en la posición especificada de la tabla.
     * 
     * @param rowIndex    El índice de la fila.
     * @param columnIndex El índice de la columna.
     * @return El valor en la posición especificada de la tabla.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /**
     * Indica si la celda especificada es editable.
     * 
     * @param rowIndex    El índice de la fila.
     * @param columnIndex El índice de la columna.
     * @return false siempre, ya que las celdas no son editables.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * Devuelve el nombre de la columna especificada.
     * 
     * @param column El índice de la columna.
     * @return El nombre de la columna especificada.
     */
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
