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

	public Tabla(String[][] peliculas, String[] columna) {
		this.data = peliculas;
		this.columnas = columna;
	}

	@Override
	public int getRowCount() {

		return data.length;
	}

	@Override
	public int getColumnCount() {

		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		return data[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column]; // Set column names
	}
}
