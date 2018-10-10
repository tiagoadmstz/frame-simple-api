/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.interfaces;

import br.com.fs.api.annotations.MapFrameField;
import br.com.fs.api.beans.JTextFieldCBI;
import br.com.fs.api.beans.MenuBarCbiDefault;
import br.com.fs.api.msg.MessageFactory;
import br.com.fs.api.util.CastFactory;
import br.com.fs.api.util.Datas;
import java.awt.Component;
import java.awt.Image;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * @author tiago.teixeira
 */
public abstract class ManipulaFrames extends JFrame {

    private static final long serialVersionUID = 5932987037949674860L;
    public static int NOVO = 0, CANCELAR = 1, SALVAR = 3, EDITAR = 4, FECHAR = 1, IMPRIMIR = 5, DELETAR = 1, ALTERAR = 2;
    private final Image image = new ImageIcon(getClass().getResource("/br/com/fs/api/img/icone.gif")).getImage();
    private int operacaoAtual = -1;

    public Optional<List<JPanel>> getListPaineis() {
        return Optional.empty();
    }

    public synchronized Optional<List<AbstractButton>> getListMenus() {
        try {
            Field field = this.getClass().getDeclaredField("menuBarCbiDefault");
            if (field != null) {
                field.setAccessible(true);
                return Optional.of(((MenuBarCbiDefault) field.get(this)).getListMenuItens());
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public Optional<List<AbstractButton>> getListButtons() {
        return Optional.empty();
    }

    public synchronized void novo() {
        getListPaineis().get().forEach(pl -> enableDisableComponentJFrame(NOVO, pl.getComponents()));
        operacaoEnableOrder(NOVO, getListMenus().orElse(null));
        operacaoEnableOrder(NOVO, getListButtons().orElse(null));
        operacaoAtual = NOVO;
    }

    public synchronized void cancelar() {
        getListPaineis().get().forEach(pl -> enableDisableComponentJFrame(CANCELAR, pl.getComponents()));
        operacaoEnableOrder(CANCELAR, getListMenus().orElse(null));
        operacaoEnableOrder(CANCELAR, getListButtons().orElse(null));
        operacaoAtual = CANCELAR;
    }

    public synchronized void editar() {
        getListPaineis().get().forEach(pl -> enableDisableComponentJFrame(EDITAR, pl.getComponents()));
        operacaoEnableOrder(EDITAR, getListMenus().orElse(null));
        operacaoEnableOrder(EDITAR, getListButtons().orElse(null));
        operacaoAtual = EDITAR;
    }

    public synchronized void setMenuEditarOpcoes() {
        operacaoEnableOrder(SALVAR, getListMenus().orElse(null));
        operacaoEnableOrder(SALVAR, getListButtons().orElse(null));
        operacaoAtual = IMPRIMIR;
    }

    public JMenuItem getItemFechar() {
        try {
            Field field = this.getClass().getDeclaredField("menuBarCbiDefault");
            if (field != null) {
                field.setAccessible(true);
                return ((MenuBarCbiDefault) field.get(this)).getItemFechar();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public synchronized void setTextFieldEditable(JCheckBox ck, List<JTextField> txt) {
        if (ck.isSelected()) {
            txt.forEach(campo -> campo.setEditable(true));
        } else {
            txt.forEach(campo -> {
                campo.setEditable(false);
                campo.setText("");
            });
        }
    }

    public synchronized void setTextFieldVisible(JCheckBox ck, List<JTextField> txt) {
        if (ck.isSelected()) {
            txt.forEach(campo -> campo.setVisible(true));
        } else {
            txt.forEach(campo -> {
                campo.setVisible(false);
                campo.setText("");
            });
        }
    }

    public synchronized boolean validarItensTextoPrescricao(Map<JCheckBox, List<JTextField>> camposVinculados) {
        boolean resultado = true;
        List<JCheckBox> lista = camposVinculados.keySet().stream().collect(Collectors.toList());
        for (JCheckBox ck : lista) {
            if (ck.isSelected()) {
                for (JTextField txt : camposVinculados.get(ck)) {
                    resultado = !txt.getText().equals("");
                    if (!resultado) {
                        return resultado;
                    }
                }
            }
        }
        return resultado;
    }

    public synchronized boolean validaCampos(ManipulaBean... object) {
        try {
            for (Object ob : object) {
                for (Method mo : ob.getClass().getDeclaredMethods()) {
                    if (mo.isAnnotationPresent(Column.class) || mo.isAnnotationPresent(JoinColumn.class)) {
                        Column column = mo.getAnnotation(Column.class);
                        JoinColumn joincolumn = mo.getAnnotation(JoinColumn.class);
                        if (column != null) {
                            if (!column.nullable()) {
                                return mo.invoke(ob) != null && !mo.invoke(ob).equals("");
                            } else {
                                return true;
                            }
                        } else {
                            if (!joincolumn.nullable()) {
                                return mo.invoke(ob) != null && !mo.invoke(ob).equals("");
                            } else {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Método de manipulação de formulários para ativar e desativar componentes
     * de interface
     *
     * @param codigoOperacao constante indicando a operacao
     * @param components lista de componentes a ser manipulados
     */
    public synchronized void enableDisableComponentJFrame(int codigoOperacao, Component[] components) {

        Map<Class, Integer> map = new HashMap();
        Boolean[][] opcoes = new Boolean[][]{
            {true, false, false, false, true}
        };

        map.put(JTextField.class, 0);
        map.put(JTextFieldCBI.class, 0);
        map.put(JComboBox.class, 0);
        map.put(JScrollPane.class, 0);
        map.put(JTextArea.class, 0);
        map.put(JTable.class, 0);
        map.put(JButton.class, 0);
        map.put(JCheckBox.class, 0);
        map.put(JRadioButton.class, 0);
        map.put(JSlider.class, 0);
        map.put(JPasswordField.class, 0);

        for (Component cp : components) {
            if (map.get(cp.getClass()) != null) {
                if (cp instanceof JScrollPane) {
                    JScrollPane sp = (JScrollPane) cp;
                    Component cp2 = sp.getViewport().getComponent(0);
                    cp2.setEnabled(opcoes[map.get(cp2.getClass())][codigoOperacao]);
                } else {
                    cp.setEnabled(opcoes[map.get(cp.getClass())][codigoOperacao]);
                }
            }
        }

        limparCampos(codigoOperacao, components);
    }

    /**
     * Este método faz a ativação ou inativação de botões ou itens de menu para
     * cada tipo de operacao do sistema.
     *
     * @param codigoOperacao É o código da operação do enumerador OPERACAO
     * @param componentes lista de botoes ou itens de menu
     */
    public synchronized void operacaoEnableOrder(int codigoOperacao, AbstractButton... componentes) {
        try {
            Map<String, Integer> mapEnable = new HashMap();
            mapEnable.put("Novo", 0);
            mapEnable.put("Cancelar", 1);
            mapEnable.put("Alterar", 2);
            mapEnable.put("Salvar", 2);
            mapEnable.put("Editar", 3);
            mapEnable.put("Fechar", 4);
            mapEnable.put("Imprimir", 5);
            mapEnable.put("Deletar", 6);
            mapEnable.put("Pesquisar", 7);
            //"Novo", "Cancelar", "Alterar", "Salvar", "Editar", "Fechar", "Imprimir", "Deletar"
            Boolean[][] opcoes = new Boolean[][]{
                {false, true, true, false, false, false, false, false}, //novo
                {true, false, false, false, true, false, false, true}, //cancelar e deletar
                {true, false, false, true, true, true, true, true}, //alterar
                {true, false, false, true, true, true, true, true}, //salvar
                {false, true, true, false, false, false, false, false} //editar
            };

            for (JComponent cp : componentes) {
                if (cp instanceof JMenuItem) {
                    JMenuItem item = (JMenuItem) cp;
                    if (mapEnable.containsKey(item.getText())) {
                        item.setEnabled(opcoes[codigoOperacao][mapEnable.get(item.getText())]);
                        if (Objects.equals("Alterar", item.getText()) && (codigoOperacao == 0 || codigoOperacao == 2)) {
                            item.setText("Salvar");
                            item.setActionCommand("salvar");
                        } else if (Objects.equals("Salvar", item.getText()) && (codigoOperacao == 3 || codigoOperacao == 4)) {
                            item.setText("Alterar");
                            item.setActionCommand("alterar");
                        }
                    }
                } else if (cp instanceof JButton) {
                    JButton bt = (JButton) cp;
                    if (mapEnable.containsKey(bt.getText())) {
                        bt.setEnabled(opcoes[codigoOperacao][mapEnable.get(bt.getText())]);
                        if (Objects.equals("Alterar", bt.getText()) && (codigoOperacao == 0 || codigoOperacao == 2)) {
                            bt.setText("Salvar");
                            bt.setActionCommand("salvar");
                        } else if (Objects.equals("Salvar", bt.getText()) && (codigoOperacao == 3 || codigoOperacao == 4)) {
                            bt.setText("Alterar");
                            bt.setActionCommand("alterar");
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Este método faz a ativação ou inativação de botões ou itens de menu para
     * cada tipo de operacao do sistema.
     *
     * @param codigoOperacao É o código da operação do enumerador OPERACAO
     * @param componentes lista de botoes ou itens de menu
     */
    public synchronized void operacaoEnableOrder(int codigoOperacao, List<AbstractButton> componentes) {
        if (componentes != null) {
            operacaoEnableOrder(codigoOperacao, componentes.toArray(new AbstractButton[componentes.size()]));
        }
    }

    public synchronized void limparCampos(int codigoOperacao, Component[] componentes) {
        if (codigoOperacao != EDITAR && codigoOperacao != SALVAR && codigoOperacao != ALTERAR) {
            for (Component component : componentes) {
                if (component instanceof JFormattedTextField) {
                    JFormattedTextField field = (JFormattedTextField) component;
                    field.setText("");
                } else if (component instanceof JTextField) {
                    JTextField field = (JTextField) component;
                    field.setText("");
                } else if (component instanceof JTextArea) {
                    JTextArea area = (JTextArea) component;
                    area.setText("");
                } else if (component instanceof JScrollPane) {
                    JScrollPane scroll = (JScrollPane) component;
                    for (Component comp : scroll.getViewport().getComponents()) {
                        if (comp instanceof JTextArea) {
                            JTextArea areaTemp = (JTextArea) comp;
                            areaTemp.setText("");
                        } else if (comp instanceof JTable) {
                            JTable tb = (JTable) comp;
                            TableModelDefaultAdapter model = (TableModelDefaultAdapter) tb.getModel();
                            model.deletarLista();
                        }
                    }
                } else if (component instanceof JComboBox) {
                    JComboBox combo = (JComboBox) component;
                    if (combo.getItemCount() > 0) {
                        combo.setSelectedIndex(0);
                    }
                } else if (component instanceof JCheckBox) {
                    JCheckBox ck = (JCheckBox) component;
                    ck.setSelected(false);
                } else if (component instanceof JSlider) {
                    JSlider sd = (JSlider) component;
                    sd.setValue(0);
                }
            }
        }
    }

    protected void setImageIcon() {
        setIconImage(image);
    }

    public void fechar() {
        if (MessageFactory.getQuestionMessage(MessageFactory.FECHAR, this)) {
            this.dispose();
            operacaoAtual = FECHAR;
        }
    }

    public void fecharSistema() {
        if (MessageFactory.getQuestionMessage(MessageFactory.FECHAR_SISTEMA, this)) {
            System.exit(0);
        }
    }

    /**
     * Este método faz um get de dados dentro de um JFrame e seta os mesmos
     * dentro de um objeto
     *
     *
     * @param object
     */
    public synchronized void getObject(ManipulaBean... object) {
        Arrays.asList(object).stream().forEachOrdered(ob -> setDados(ob));
    }

    /**
     * Este método faz o set de dados de um objecto dentro de um JFrame
     *
     * @param object
     */
    public synchronized void setObject(ManipulaBean... object) {
        Arrays.asList(object).stream().forEachOrdered(ob -> getDados(ob));
    }

    /**
     * Este método faz um get de dados dentro de um JFrame e seta os mesmos
     * dentro de um objeto
     *
     * @param object
     */
    protected synchronized void setDados(ManipulaBean object) {
        try {
            for (Method mf : getClass().getDeclaredMethods()) {
                if (mf.isAnnotationPresent(MapFrameField.class)
                        && (mf.getAnnotation(MapFrameField.class).targetEntity() == object.getClass()
                        || mf.getAnnotation(MapFrameField.class).targetEntity() == Object.class)) {
                    MapFrameField map = mf.getAnnotation(MapFrameField.class);
                    Method mo = getObjectGetSetMethod(object, map, "set");
                    //System.out.println(mo.getName());
                    try {
                        mo.invoke(object, getFrameObjectGetSetMethod(mf, map, getStrMethod(mf.getReturnType(), map, "get"), null));
                    } catch (Exception e) {
                    }
                    //System.out.println("PASSOU");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método faz o set de dados de um objecto dentro de um JFrame
     *
     * @param object
     */
    protected synchronized void getDados(ManipulaBean object) {
        try {
            Arrays.asList(getClass().getDeclaredMethods())
                    .stream()
                    .sorted((m1, m2) -> m1.getName().compareTo(m2.getName()))
                    .forEach(mf -> {
                        try {
                            if (mf.isAnnotationPresent(MapFrameField.class)
                                    && (mf.getAnnotation(MapFrameField.class).targetEntity() == object.getClass()
                                    || mf.getAnnotation(MapFrameField.class).targetEntity() == Object.class)) {
                                MapFrameField map = mf.getAnnotation(MapFrameField.class);
                                Method mo = getObjectGetSetMethod(object, map, "get");
                                //System.out.println(mo.getName() + " = " + mo.invoke(object));
                                getFrameObjectGetSetMethod(mf, map, getStrMethod(mf.getReturnType(), map, "set"), mo.invoke(object));
                                //System.out.println("PASSOU");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método corrige o nome do atributo para o nome de seu respectivo
     * getter ou setter
     *
     * @param object
     * @param map
     * @param prefix
     * @return
     */
    private synchronized Method getObjectGetSetMethod(ManipulaBean object, MapFrameField map, String prefix) {
        try {
            if (prefix.equals("get") || prefix.equals("is")) {
                prefix = map.typeParameter() != boolean.class && map.typeParameter() != Boolean.class ? "get" : "is";
                return object.getClass().getDeclaredMethod(prefix.concat(map.referencedField().replaceFirst("\\w", map.referencedField().substring(0, 1).toUpperCase())));
            } else if (prefix.equals("set")) {
                return object.getClass().getDeclaredMethod("set".concat(map.referencedField().replaceFirst("\\w", map.referencedField().substring(0, 1).toUpperCase())), map.typeParameter());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Este método executa um método get ou set de um campo de JFrame
     *
     * @param declaredMethod
     * @param map
     * @param strMethod
     * @param value
     * @return
     */
    private synchronized Object getFrameObjectGetSetMethod(Method declaredMethod, MapFrameField map, String strMethod, Object value) {
        try {
            //System.out.println(strMethod);
            if (strMethod.contains("get") || strMethod.contains("is")) {
                return CastFactory.cast(declaredMethod.invoke(this).getClass().getMethod(strMethod).invoke(declaredMethod.invoke(this)), map.typeParameter());
            } else if (strMethod.contains("set")) {
                return declaredMethod.invoke(this).getClass().getMethod(strMethod, getTypeSet(declaredMethod.getReturnType())).invoke(declaredMethod.invoke(this), castTypeSet(formatAdapter(value), getTypeSet(declaredMethod.getReturnType())));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private Object formatAdapter(Object value) {
        try {
            if (value.getClass() == LocalDate.class) {
                value = Datas.getDateString((LocalDate) value);
            }
            return value;
        } catch (Exception e) {
            return value;
        }
    }

    private Class<?> getTypeSet(Class<?> returnTypeField) {
        if (returnTypeField == JTextField.class || returnTypeField == JTextArea.class || returnTypeField == JTextFieldCBI.class) {
            return String.class;
        } else if (returnTypeField == JComboBox.class) {
            return Object.class;
        } else if (returnTypeField == JCheckBox.class) {
            return boolean.class;
        }
        return null;
    }

    private Object castTypeSet(Object value, Class<?> typeSet) {
        if (typeSet == String.class) {
            if (value != null) {
                return String.valueOf(value);
            }
        } else if (typeSet == Long.class) {
            if (value != null) {
                return Long.valueOf(value.toString());
            }
        }
        return value;
    }

    /**
     * Este método retorna uma String com o nome de um método para fazer get ou
     * set de dados em campos de JFrame
     *
     * @param returnType
     * @param map
     * @param strMethodPrefix
     * @return
     */
    private synchronized String getStrMethod(Class<?> returnType, MapFrameField map, String strMethodPrefix) {
        boolean type = strMethodPrefix.equals("get") || strMethodPrefix.equals("is");
        if (returnType == JTextField.class || returnType == JTextFieldCBI.class || returnType == JTextArea.class) {
            return type ? "getText" : "setText";
        } else if (returnType == JComboBox.class) {
            if (map.typeParameter() == String.class) {
                return type ? "getSelectedItem" : "setSelectedItem";
            } else if (map.typeParameter() == Integer.class) {
                return type ? "getSelectedIndex" : "setSelectedIndex";
            }
        } else if (returnType == JCheckBox.class) {
            return type ? "isSelected" : "setSelected";
        }
        return null;
    }

    public Map<String, String> getFieldsValues() {
        try {
            Map<String, String> maps = new HashMap();
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String valor = "";
                if (field.getType().getSuperclass() == JTextComponent.class) {
                    valor = String.valueOf(field.get(this).getClass().getMethod("getText").invoke(field.get(this)));
                } else if (field.getType() == JComboBox.class) {
                    valor = String.valueOf(field.get(this).getClass().getMethod("getSelectedItem").invoke(field.get(this)));
                }
                if (!valor.equals("")) {
                    maps.put(field.getName().toLowerCase(), valor);
                }
            }
            return maps;
        } catch (Exception e) {
            return null;
        }
    }

    public int getOperacaoAtual() {
        return operacaoAtual;
    }

    public void setOperacaoAtual(int operacao) {
        this.operacaoAtual = operacao;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        requestFocusInWindow();
        if (getExtendedState() == ICONIFIED) {
            setExtendedState(NORMAL);
        }
    }

}
