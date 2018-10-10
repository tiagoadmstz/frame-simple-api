package br.com.fs.api.renderers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.fs.api.util.REGEX;
import br.com.fs.api.util.RegexUtil;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tiago D. Teixeira
 */
public final class DefaultCBIHeaderRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 4580475964761407740L;

    private JTable table = null;
    private MouseEventReposter reporter = null;
    private JPanel editor;
    private final JButton button = new JButton();
    private final JPopupMenu popupMenu = new JPopupMenu();
    private final DefaultCBIHeaderRendererListener listener = new DefaultCBIHeaderRendererListener();
    private final ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("/br/com/fs/api/img/down.gif"));
    private final ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/br/com/fs/api/img/down_red.gif"));
    private final ImageIcon imageIcon3 = new ImageIcon(getClass().getResource("/br/com/fs/api/img/iconeCF.gif"));
    private final TableRowSorter sorter;
    private int column = -1;

    public DefaultCBIHeaderRenderer(TableRowSorter sorter) {
        this.sorter = sorter;
    }

    private void addFiltros(JLabel label) {
        button.setIcon(imageIcon1);
        button.setPreferredSize(new Dimension(16, 16));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(button, 8, 8);
            }
        });

        this.construiMenus(table.getModel().getColumnClass(column));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.WEST);
        panel.add(button, BorderLayout.EAST);
        panel.setOpaque(true);
        editor = panel;
    }

    public void getItensTexto(JMenu menu) {
        JMenuItem mi1 = new JMenuItem("É Igual a...");
        mi1.setActionCommand("igualA");
        mi1.addActionListener(listener);
        JMenuItem mi2 = new JMenuItem("É Diferente de...");
        mi2.setActionCommand("diferenteDe");
        mi2.addActionListener(listener);
        JMenuItem mi3 = new JMenuItem("Começa com...");
        mi3.setActionCommand("comecaCom");
        mi3.addActionListener(listener);
        JMenuItem mi4 = new JMenuItem("Termina com...");
        mi4.setActionCommand("terminaCom");
        mi4.addActionListener(listener);
        JMenuItem mi5 = new JMenuItem("Contém...");
        mi5.setActionCommand("comtem");
        mi5.addActionListener(listener);
        JMenuItem mi6 = new JMenuItem("Não Contém...");
        mi6.setActionCommand("naoContem");
        mi6.addActionListener(listener);

        menu.add(mi1);
        menu.add(mi2);
        menu.add(new JSeparator());
        menu.add(mi3);
        menu.add(mi4);
        menu.add(new JSeparator());
        menu.add(mi5);
        menu.add(mi6);
    }

    public void getItensNumero(JMenu menu) {
        JMenuItem mi1 = new JMenuItem("É Igual a...");
        mi1.setActionCommand("igualA");
        mi1.addActionListener(listener);
        JMenuItem mi2 = new JMenuItem("É Diferente de...");
        mi2.setActionCommand("diferenteDe");
        mi2.addActionListener(listener);
        JMenuItem mi3 = new JMenuItem("É Maior do que...");
        JMenuItem mi4 = new JMenuItem("É Maior ou Igual a...");
        JMenuItem mi5 = new JMenuItem("É Menor do que...");
        JMenuItem mi6 = new JMenuItem("É Menor ou Igual a...");
        JMenuItem mi7 = new JMenuItem("Está entre...");
        JMenuItem mi8 = new JMenuItem("10 Primeiros...");
        JMenuItem mi9 = new JMenuItem("Acima da média");
        JMenuItem mi10 = new JMenuItem("Abaixo da média");

        menu.add(mi1);
        menu.add(mi2);
        menu.add(new JSeparator());
        menu.add(mi3);
        menu.add(mi4);
        menu.add(mi5);
        menu.add(mi6);
        menu.add(mi7);
        menu.add(new JSeparator());
        menu.add(mi8);
        menu.add(mi9);
        menu.add(mi10);
    }

    public void construiMenus(Class columnClass) {

        JMenu menuPersonalizado = new JMenu();

        JMenuItem m1 = new JMenuItem("Ordenar de A a Z...");
        m1.setActionCommand("AZ");
        m1.setIcon(new ImageIcon(getClass().getResource("/br/com/fs/api/img/iconeAZ.gif")));
        m1.addActionListener(listener);
        JMenuItem m2 = new JMenuItem("Ordenar de Z a A...");
        m2.setActionCommand("ZA");
        m2.setIcon(new ImageIcon(getClass().getResource("/br/com/fs/api/img/iconeZA.gif")));
        m2.addActionListener(listener);
        JMenuItem m3 = new JMenuItem("Limpar filtro de ...");
        m3.setActionCommand("limpar");
        m3.setEnabled(false);
        m3.setIcon(imageIcon3);
        m3.addActionListener(listener);

        popupMenu.add(m1);
        popupMenu.add(m2);
        popupMenu.add(new JSeparator());
        popupMenu.add(m3);
        if (Objects.equals(columnClass, String.class) || Objects.equals(columnClass, Object.class)) {
            menuPersonalizado.setText("Filtros de Texto");
            this.getItensTexto(menuPersonalizado);
            popupMenu.add(menuPersonalizado);
        } else if (Objects.equals(columnClass, Number.class)) {
            menuPersonalizado.setText("Filtros de Número");
            this.getItensNumero(menuPersonalizado);
            popupMenu.add(menuPersonalizado);
        }
        table.getModel().getColumnClass(column);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table != null && this.table != table) {
            this.table = table;
            final JTableHeader header = table.getTableHeader();
            header.setEnabled(true);
            if (header != null) {
                this.column = column;
                this.addFiltros(comp instanceof JLabel ? (JLabel) comp : null);
                this.editor.setForeground(comp.getForeground());
                this.editor.setBackground(comp.getBackground());
                this.button.setBackground(comp.getBackground());
                this.editor.setFont(comp.getFont());
                reporter = new MouseEventReposter(header, column, this.editor);
                header.addMouseListener(reporter);
            }
        }

        if (reporter != null) {
            reporter.setColumn(column);
        }

        return this.editor;
    }

    static public class MouseEventReposter extends MouseAdapter {

        private Component dispatchComponent;
        private JTableHeader header;
        private int column = -1;
        private Component editor;

        public MouseEventReposter(JTableHeader header, int column, Component editor) {
            this.header = header;
            this.column = column;
            this.editor = editor;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        private void setDispatchComponent(MouseEvent e) {
            int col = header.getTable().columnAtPoint(e.getPoint());
            if (col != column || col == -1) {
                return;
            }

            Point p = e.getPoint();
            Point p2 = SwingUtilities.convertPoint(header, p, editor);
            dispatchComponent = SwingUtilities.getDeepestComponentAt(editor, p2.x, p2.y);
        }

        private boolean repostEvent(MouseEvent e) {
            if (dispatchComponent == null) {
                return false;
            }
            MouseEvent e2 = SwingUtilities.convertMouseEvent(header, e, dispatchComponent);
            dispatchComponent.dispatchEvent(e2);
            return true;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (header.getResizingColumn() == null) {
                Point p = e.getPoint();

                int col = header.getTable().columnAtPoint(p);
                if (col != column || col == -1) {
                    return;
                }

                int index = header.getColumnModel().getColumnIndexAtX(p.x);
                if (index == -1) {
                    return;
                }

                editor.setBounds(header.getHeaderRect(index));
                header.add(editor);
                editor.validate();
                setDispatchComponent(e);
                repostEvent(e);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            repostEvent(e);
            dispatchComponent = null;
            header.remove(editor);
        }
    }

    private class DefaultCBIHeaderRendererListener implements ActionListener {

        public DefaultCBIHeaderRendererListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem mi = (JMenuItem) popupMenu.getComponent(3);
            switch (e.getActionCommand()) {
                case "AZ":
                    System.out.println("Ordenando de A a Z");
                    sorter.setSortable(column, true);
                    if (sorter.getSortKeys().size() > 0) {
                        RowSorter.SortKey sortKey = (RowSorter.SortKey) sorter.getSortKeys().get(0);
                        if (sortKey.getSortOrder() == SortOrder.DESCENDING) {
                            sorter.toggleSortOrder(column);
                        }
                    } else {
                        sorter.toggleSortOrder(column);
                    }
                    sorter.setSortable(column, false);
                    break;
                case "ZA":
                    System.out.println("Ordenando de Z a A");
                    sorter.setSortable(column, true);
                    if (sorter.getSortKeys().size() > 0) {
                        RowSorter.SortKey sortKey = (RowSorter.SortKey) sorter.getSortKeys().get(0);
                        if (sortKey.getSortOrder() == SortOrder.ASCENDING) {
                            sorter.toggleSortOrder(column);
                        }
                    } else {
                        sorter.toggleSortOrder(column);
                    }
                    sorter.setSortable(column, false);
                    break;
                case "limpar":
                    sorter.setRowFilter(null);
                    System.out.println("Limpando filtros");
                    mi.setEnabled(false);
                    button.setIcon(imageIcon1);
                    break;
                case "igualA":
                    String igual = JOptionPane.showInputDialog(button, "É igual a ...", "Informe o texto a ser filtrado", JOptionPane.PLAIN_MESSAGE);
                    if (!"".equals(igual) && igual != null) {
                        sorter.setRowFilter(RowFilter.regexFilter(RegexUtil.getRegex(REGEX.IGUAL, igual), column));
                        mi.setEnabled(true);
                        button.setIcon(imageIcon2);
                    }
                    break;
                case "diferenteDe":
                    String diferente = JOptionPane.showInputDialog(button, "É Diferente de ...", "Informe o texto a ser filtrado", JOptionPane.PLAIN_MESSAGE);
                    if (!"".equals(diferente) && diferente != null) {
                        sorter.setRowFilter(RowFilter.regexFilter(RegexUtil.getRegex(REGEX.DIFERENTE, diferente), column));
                        mi.setEnabled(true);
                        button.setIcon(imageIcon2);
                    }
                    break;
                case "comecaCom":
                    String comeca = JOptionPane.showInputDialog(button, "Começa com ...", "Informe o texto a ser filtrado", JOptionPane.PLAIN_MESSAGE);
                    if (!"".equals(comeca) && comeca != null) {
                        sorter.setRowFilter(RowFilter.regexFilter(RegexUtil.getRegex(REGEX.COMECACOM, comeca), column));
                        mi.setEnabled(true);
                        button.setIcon(imageIcon2);
                    }
                    break;
                case "terminaCom":
                    String termina = JOptionPane.showInputDialog(button, "Termina com ...", "Informe o texto a ser filtrado", JOptionPane.PLAIN_MESSAGE);
                    if (!"".equals(termina) && termina != null) {
                        sorter.setRowFilter(RowFilter.regexFilter(RegexUtil.getRegex(REGEX.TERMINACOM, termina), column));
                        mi.setEnabled(true);
                        button.setIcon(imageIcon2);
                    }
                    break;
                case "comtem":
                    String contem = JOptionPane.showInputDialog(button, "Contém ...", "Informe o texto a ser filtrado", JOptionPane.PLAIN_MESSAGE);
                    if (!"".equals(contem) && contem != null) {
                        sorter.setRowFilter(RowFilter.regexFilter(RegexUtil.getRegex(REGEX.CONTEM, contem), column));
                        mi.setEnabled(true);
                        button.setIcon(imageIcon2);
                    }
                    break;
                case "naoContem":
                    String naoContem = JOptionPane.showInputDialog(button, "Não Contém ...", "Informe o texto a ser filtrado", JOptionPane.PLAIN_MESSAGE);
                    if (!"".equals(naoContem) && naoContem != null) {
                        sorter.setRowFilter(RowFilter.regexFilter(RegexUtil.getRegex(REGEX.NAOCONTEM, naoContem), column));
                        mi.setEnabled(true);
                        button.setIcon(imageIcon2);
                    }
                    break;
                default:
                    System.out.println("Filtros personalizados");
                    break;
            }
        }
    }
}
