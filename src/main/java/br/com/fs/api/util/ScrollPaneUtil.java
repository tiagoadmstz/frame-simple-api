/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;

/**
 *
 * @author tiago.teixeira
 */
public class ScrollPaneUtil {

    public static void scrollPanelConfigurator(JScrollPane scrollPane) {
        //aumenta a velocidade do scroll
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        Component[] comps = null;
        for (Component panel : scrollPane.getViewport().getComponents()) {
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                comps = p.getComponents();
                break;
            }
        }
        keyListenerScrollPanes listener = new keyListenerScrollPanes(scrollPane);
        for (Component comp : comps) {
            if (comp instanceof JScrollPane) {
                JScrollPane scp = (JScrollPane) comp;
                JTextArea ta = (JTextArea) scp.getViewport().getComponent(0);
                ta.setTabSize(0);
                ta.addKeyListener(listener);
                ta.addFocusListener(listener);
            } else if (comp instanceof JTextField) {
                comp.addFocusListener(listener);
            } else if (comp instanceof JComboBox) {
                comp.addFocusListener(listener);
            }
        }
    }

    private static class keyListenerScrollPanes implements KeyListener, FocusListener {

        private JScrollPane scroll = null;

        public keyListenerScrollPanes(JScrollPane scroll) {
            this.scroll = scroll;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_TAB) {
                manager.focusPreviousComponent();
            } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
                manager.focusNextComponent();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getComponent() instanceof JTextField && (e.getComponent().getY() - 30) > 0) {
                JTextField tx = (JTextField) e.getSource();
                scroll.getViewport().setViewPosition(new Point(tx.getLocation().x, (tx.getLocation().y - 30)));
            } else if (e.getComponent() instanceof JTextArea) {
                JTextArea ta = (JTextArea) e.getSource();
                JViewport vp = (JViewport) ta.getAccessibleContext().getAccessibleParent();
                JScrollPane scp = (JScrollPane) vp.getAccessibleContext().getAccessibleParent();
                scroll.getViewport().setViewPosition(new Point(scp.getLocation().x, (scp.getLocation().y - 30)));
            }
        }

        @Override
        public void focusLost(FocusEvent e) {

        }

    }

}
