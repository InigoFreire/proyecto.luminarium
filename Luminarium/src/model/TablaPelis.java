package model;

import javax.swing.table.AbstractTableModel;

public class TablaPelis extends AbstractTableModel  {
	private String[][] data;
	private String[] columnas;
	
	public TablaPelis(String[][] peliculas, String [] columna) {
		this.data = peliculas;
		this.columnas= columna;
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
