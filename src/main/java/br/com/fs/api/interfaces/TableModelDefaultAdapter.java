package br.com.fs.api.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tiago D. Teixeira
 * @param <T>
 */
public abstract class TableModelDefaultAdapter<T> extends AbstractTableModel {

    private static final long serialVersionUID = 143910086997416022L;
    private String[] columnNames;
    protected List<T> lista;
    protected int selectedRow = -1;

    public TableModelDefaultAdapter() {
        this.lista = new ArrayList();
    }

    public TableModelDefaultAdapter(List<T> lista) {
        this.lista = lista == null ? new ArrayList() : (List<T>) lista;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List<String> getColumnsName() {
        return Arrays.asList(columnNames);
    }

    public void setColmunName(String... columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public int getRowCount() {
        return lista != null ? lista.size() : 0;
    }

    public void setLista(List<T> lista) {
        if (lista != null) {
            this.lista = lista;
        }
        fireTableDataChanged();
    }

    public void addObject(T object) {
        if (object != null) {
            lista.add(object);
            fireTableDataChanged();
        }
    }

    public void removeObject(int rowIndex) {
        lista.remove(rowIndex);
        fireTableDataChanged();
    }

    public void removeObjects(int... rowIndex) {
        lista.removeAll(getObjects(rowIndex));
        fireTableDataChanged();
    }

    public void removeObject(T object) {
        lista.remove(object);
        fireTableDataChanged();
    }

    public T getObject(int rowIndex) {
        return rowIndex != -1 ? lista.get(rowIndex) : null;
    }

    public List<T> getObjects(int... rowIndex) {
        List<T> objects = new ArrayList();
        for (int i : rowIndex) {
            objects.add(lista.get(i));
        }
        return objects;
    }

    public List<T> getObjects(List<Integer> rowIndex) {
        List<T> objects = new ArrayList();
        rowIndex.forEach((i) -> {
            objects.add(lista.get(i));
        });
        return objects;
    }

    public void deletarObjects(int[] rowIndex) {
        List<T> temp = new ArrayList();
        for (int i : rowIndex) {
            temp.add(lista.get(i));
        }
        lista.removeAll(temp);
        fireTableDataChanged();
    }

    public void deletarObjects(List<T> objects) {
        objects.forEach(lista::remove);
        fireTableDataChanged();
    }

    public void deletarLista() {
        lista.clear();
        fireTableDataChanged();
    }

    public void addLista(List<T> lista) {
        if (lista != null) {
            this.lista.addAll(lista);
        }
        fireTableDataChanged();
    }

    public List<T> getLista() {
        return lista;
    }

    public List<T> clonar() {
        return new ArrayList(lista);
    }

    public void resetarLista() {

    }

    public void atualizarItem(T object) {

    }

    public void aplicarFiltro(int column, String filtro) {
        Comparator comp = Collator.getInstance();
        lista.sort(comp);
    }

    public void aplicarFiltroLetra(String column, String filtro) {

    }

    public void atualizarItem(T object, int rowIndex) {
        lista.set(rowIndex, object);
        fireTableDataChanged();
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int rowIndex) {
        this.selectedRow = rowIndex;
    }
    
}
