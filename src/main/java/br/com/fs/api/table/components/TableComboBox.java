/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.table.components;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Tiago
 */
public class TableComboBox extends DefaultTableCellRenderer {

    private static final long serialVersionUID = -4497632730091960950L;
    private final JComboBox combobox;

    public TableComboBox() {
        combobox = new JComboBox();
    }

    public JComboBox getCombobox() {
        return combobox;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        table.getColumnModel().getColumn(column).setCellEditor(new TableComboBoxEditor(combobox));
        if (value != null) {
            combobox.setSelectedItem(value);
        }
        return combobox;
    }

    public class TableComboBoxEditor extends DefaultCellEditor {

        private static final long serialVersionUID = 7049209914943293337L;

        public JComponent getEditorComponent() {
            return editorComponent;
        }

        public TableComboBoxEditor(JComboBox comboBox) {
            super(comboBox);
        }

        @Override
        public Object getCellEditorValue() {
            return combobox.getSelectedItem();
        }

    }

}
