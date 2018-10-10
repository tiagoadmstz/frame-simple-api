package br.com.fs.api.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Tiago D. Teixeira
 */
public class OnChangeListener {
   
    private static String text;
    private static Boolean change;
    
    public static void EventListener(FocusEvent focusEvent){
            
            switch (focusEvent.getID()){
                case FocusEvent.FOCUS_GAINED:
                        if(focusEvent.getComponent() instanceof JTextField){
                            JTextField temp = (JTextField) focusEvent.getComponent();
                            text = temp.getText();
                        } else if (focusEvent.getComponent() instanceof JComboBox){
                            JComboBox temp = (JComboBox) focusEvent.getComponent();
                            text = temp.getSelectedItem().toString();
                        }
                    break;
                case FocusEvent.FOCUS_LOST:
                        if(focusEvent.getComponent() instanceof JTextField){
                            JTextField temp = (JTextField) focusEvent.getComponent();
                            change = !text.equals(temp.getText());
                        } else if (focusEvent.getComponent() instanceof JComboBox){
                            JComboBox temp = (JComboBox) focusEvent.getComponent();
                            change = !text.equals(temp.getSelectedItem().toString());
                        }
                    break;
            }
    }
    
    public static Boolean getChangeEvent(){              
            return change;
    }
    
}