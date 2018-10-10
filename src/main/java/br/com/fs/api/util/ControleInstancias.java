package br.com.fs.api.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author Tiago D. Teixeira
 */
public abstract class ControleInstancias {

    private static final WindowCloseListener LISTENER = new WindowCloseListener();
    private static final Map<String, Object> INSTANCIAS = new HashMap();

    public static void setControleInstancias(String nome, Object object) {
        INSTANCIAS.put(nome, object);
        if (object instanceof JFrame) {
            JFrame frame = (JFrame) object;
            frame.addWindowListener(LISTENER);
        }
    }

    public static Object getInstance(String nome) {
        try {
            if (!INSTANCIAS.containsKey(nome)) {
                return createInstance(nome);
            }
            return INSTANCIAS.get(nome);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isInstanced(String nome){
        return INSTANCIAS.containsKey(nome);
    }
    
    public static void removeInstance(String nome) {
        INSTANCIAS.remove(nome);
    }

    public static JFrame logout(Class<?> loginForm) {
        clear();
        JFrame login = (JFrame) getInstance(loginForm.getName());
        return login;
    }

    private static Object createInstance(String nome) throws Exception {
        Class<?> clazz = Class.forName(nome);
        for (Constructor construtor : clazz.getConstructors()) {
            if (construtor.getParameterCount() == 0) {
                Object ob = construtor.newInstance();
                setControleInstancias(nome, ob);
                return ob;
            } else {
                return null;
            }
        }
        return null;
    }

    private static void clear() {
        INSTANCIAS.values().forEach(ob -> {
            JFrame frame = (JFrame) ob;
            frame.dispose();
        });
        INSTANCIAS.clear();
    }

    private static class WindowCloseListener extends WindowAdapter {

        public WindowCloseListener() {
            super();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            if (e.getID() == WindowEvent.WINDOW_CLOSED) {
                removeInstance(e.getSource().getClass().getName());
            }
        }
    }

}
