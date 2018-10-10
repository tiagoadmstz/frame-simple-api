/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.interfaces;

import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretEvent;

/**
 *
 * @author tiago.teixeira
 * @param <T>
 */
public class ListenerPatternAdapter<T> extends AbstractListenerPattern<T> {

    public ListenerPatternAdapter(T form) {
        super(form);
    }

    @Override
    protected void addModel() {
        
    }

    @Override
    public void setDados(Object object) {
        
    }

    @Override
    public void setDados() {
        
    }

    @Override
    public void getDados() {
        
    }

    @Override
    public void setEnableButtons(int codFunction) {
        
    }

    @Override
    protected void imprimir() {
        
    }

    @Override
    protected void deletar() {
        
    }

    @Override
    public void carregarListas() {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
    }

    @Override
    public void copiarObject(Object object) {
        
    }
    
}
