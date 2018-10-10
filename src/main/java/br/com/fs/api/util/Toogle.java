/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import java.awt.Component;
import java.util.Arrays;

/**
 *
 * @author tiago.teixeira
 */
public abstract class Toogle {

    public static void toogleEnable(Component component) {
        component.setEnabled(!component.isEnabled());
    }

    public static void toggleVisible(Component... comps) {
        Arrays.asList(comps).stream().forEach(c -> {
            c.setVisible(!c.isVisible());
        });
    }

    public static String toogleBoolean(String bool) {
        return bool.equals("true") ? "false" : "true";
    }

}
