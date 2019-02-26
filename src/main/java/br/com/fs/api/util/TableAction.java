/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import br.com.fs.api.interfaces.ManipulaBean;
import br.com.fs.api.interfaces.TableModelDefaultAdapter;
import br.com.fs.api.msg.MessageFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import javax.swing.JTable;

/**
 *
 * @author tiago.teixeira
 */
public final class TableAction implements Serializable {

    private static final long serialVersionUID = 8915022500685462075L;
    private final JTable table;
    private final TableModelDefaultAdapter model;

    public TableAction(JTable table, TableModelDefaultAdapter model) {
        this.table = table;
        this.model = model;
        initController();
    }

    private void initController() {
        attachListener();
    }

    private void attachListener() {
        try {
            table.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    switch (event.getKeyCode()) {
                        case KeyEvent.VK_DOWN:
                            addLine();
                            break;
                        case KeyEvent.VK_UP:
                            deleteBlancLine();
                            break;
                        case KeyEvent.VK_DELETE:
                            deleteLine();
                            break;
                        default:
                            break;
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private void addLine() {
        if (model.getRowCount() >= 1 && table.getSelectedRow() == model.getRowCount() - 1) {
            if (verificaLinhaAcima()) {
                ManipulaBean ob = (ManipulaBean) model.getObject(model.getRowCount() - 1);
                model.addObject(ob.getInstance());
                table.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
            }
        }
    }

    private void deleteLine() {
        if (MessageFactory.getQuestionMessage("Realmente deseja excluir o registro?", table)) {
            model.removeObject(table.getSelectedRow());
            this.addLine();
        }
    }

    private void deleteBlancLine() {
        if (model.getRowCount() > 1 && table.getSelectedRow() == model.getRowCount() - 1) {
            if (verificaLinhaAbaixo()) {
                model.removeObject(table.getSelectedRow());
            }
        }
    }

    private boolean verificaLinhaAcima() {
        if (model.getRowCount() > 1) {
            return !((ManipulaBean) model.getObject(model.getRowCount() - 2)).isNull();
        } else {
            return !((ManipulaBean) model.getObject(0)).isNull();
        }
    }

    private boolean verificaLinhaAbaixo() {
        if (model.getRowCount() > 1) {
            ManipulaBean mb = (ManipulaBean) model.getObject(model.getRowCount() - 1);
            if (!mb.isNull()) {
                return ((ManipulaBean) model.getObject(model.getRowCount() - 1)).isNull();
            }
        }
        return false;
    }

}
