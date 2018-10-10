/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.beans;

/**
 *
 * @author tiago.teixeira
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.JCheckBox;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public final class JCheckBoxTree extends JTree {

    private static final long serialVersionUID = -4194122328392241790L;
    private final JCheckBoxTree selfPointer = this;
    private HashMap<TreePath, CheckedNode> nodesCheckingState;
    private HashSet<TreePath> checkedPaths;
    //Definindo um novo tipo de evento para o mecanismo de checagem e preparando o mecanismo de manipulação de evento
    protected final EventListenerList listener = new EventListenerList();
    private final CheckBoxCellRenderer ckRenderer = new CheckBoxCellRenderer();
    private final TreeSelectionModelAdapter dtsm = new TreeSelectionModelAdapter();

    public JCheckBoxTree() {
        super();
        initComponents();
    }

    private void initComponents() {
        setToggleClickCount(0);
        setCellRenderer(ckRenderer);
        setSelectionModel(dtsm);
        attachListener();
    }

    public void setRoot(String nome) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(nome);
        TreeModel m = new DefaultTreeModel(root);
        this.setModel(m);
        this.updateUI();
    }

    public void addRamo(String grupo, List<String> itens) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.getModel().getRoot();
        DefaultMutableTreeNode r = new DefaultMutableTreeNode(grupo);
        root.add(r);
        itens.stream().forEach(item -> r.add(new DefaultMutableTreeNode(item)));
        atualizarTree(root);
    }

    public void addItem(String grupo, String item) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) getModel().getRoot();
        //DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(root.getIndex(new DefaultMutableTreeNode(grupo)));
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(0);
        node.add(new DefaultMutableTreeNode(item));
        atualizarTree(root);
    }

    private void atualizarTree(DefaultMutableTreeNode root) {
        this.setModel(new DefaultTreeModel(root));
        this.updateUI();
    }

    private void attachListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                TreePath tp = selfPointer.getPathForLocation(arg0.getX(), arg0.getY());
                if (tp == null) {
                    return;
                }
                boolean checkMode = !nodesCheckingState.get(tp).isSelected;
                checkSubTree(tp, checkMode);
                updatePredecessorsWithCheckMode(tp, checkMode);
                // Firing the check change event
                fireCheckChangeEvent(new CheckChangeEvent(new Object()));
                // Repainting tree after the data structures were updated
                selfPointer.repaint();
            }
        });
        /*cbt.addCheckChangeEventListener(ckEvent -> {
        System.out.println("event");
        TreePath[] paths = cbt.getCheckedPaths();
        for (TreePath tp : paths) {
        for (Object pathPart : tp.getPath()) {
        System.out.print(pathPart + ",");
        }
        }
        });*/
    }

    public void addCheckChangeEventListener(CheckChangeEventListener listener) {
        this.listener.add(CheckChangeEventListener.class, listener);
    }

    public void removeCheckChangeEventListener(CheckChangeEventListener listener) {
        this.listener.remove(CheckChangeEventListener.class, listener);
    }

    public void fireCheckChangeEvent(CheckChangeEvent evt) {
        Object[] listeners = listener.getListenerList();
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == CheckChangeEventListener.class) {
                ((CheckChangeEventListener) listeners[i + 1]).checkStateChanged(evt);
            }
        }
    }

    @Override
    public void setModel(TreeModel newModel) {
        super.setModel(newModel);
        resetCheckingState();
    }

    //Novo método que retorna somente os paths checados (ignora totalmente o mecanismo de seleção original)
    public TreePath[] getCheckedPaths() {;
        return checkedPaths.toArray(new TreePath[checkedPaths.size()]);
    }

    public List<String> getStringCheckedPaths() {
        TreePath[] paths = getCheckedPaths();
        List<String> lista = new ArrayList();
        for (TreePath tp : paths) {
            for (Object ob : tp.getPath()) {
                lista.add(ob.toString());
            }
        }
        return lista;
    }

    //Retorna verdadeiro no caso de um nó estiver selecionado, tem filhos mas não todos que estão selecionados
    public boolean isSelectedPartially(TreePath path) {
        CheckedNode cn = nodesCheckingState.get(path);
        return cn.isSelected && cn.hasChildren && !cn.allChildrenSelected;
    }

    private void resetCheckingState() {
        nodesCheckingState = new HashMap();
        checkedPaths = new HashSet();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) getModel().getRoot();
        if (node == null) {
            return;
        }
        addSubtreeToCheckingStateTracking(node);
    }

    //Criando estrutura de dados do model atual para o mecanismo de checagem
    private void addSubtreeToCheckingStateTracking(DefaultMutableTreeNode node) {
        TreeNode[] path = node.getPath();
        TreePath tp = new TreePath(path);
        CheckedNode cn = new CheckedNode(false, node.getChildCount() > 0, false);
        nodesCheckingState.put(tp, cn);
        for (int i = 0; i < node.getChildCount(); i++) {
            addSubtreeToCheckingStateTracking((DefaultMutableTreeNode) tp.pathByAddingChild(node.getChildAt(i)).getLastPathComponent());
        }
    }

    //Quando um nó está checado/deschecado, atualizando o status dos predecessores
    protected void updatePredecessorsWithCheckMode(TreePath tp, boolean check) {
        TreePath parentPath = tp.getParentPath();
        // If it is the root, stop the recursive calls and return
        if (parentPath == null) {
            return;
        }
        CheckedNode parentCheckedNode = nodesCheckingState.get(parentPath);
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
        parentCheckedNode.allChildrenSelected = true;
        parentCheckedNode.isSelected = false;
        for (int i = 0; i < parentNode.getChildCount(); i++) {
            TreePath childPath = parentPath.pathByAddingChild(parentNode.getChildAt(i));
            CheckedNode childCheckedNode = nodesCheckingState.get(childPath);
            // It is enough that even one subtree is not fully selected
            // to determine that the parent is not fully selected
            if (!childCheckedNode.allChildrenSelected) {
                parentCheckedNode.allChildrenSelected = false;
            }
            // If at least one child is selected, selecting also the parent
            if (childCheckedNode.isSelected) {
                parentCheckedNode.isSelected = true;
            }
        }
        if (parentCheckedNode.isSelected) {
            checkedPaths.add(parentPath);
        } else {
            checkedPaths.remove(parentPath);
        }
        // Go to upper predecessor
        updatePredecessorsWithCheckMode(parentPath, check);
    }

    //Chamada recursiva check/uncheck uma subtree
    protected void checkSubTree(TreePath tp, boolean check) {
        CheckedNode cn = nodesCheckingState.get(tp);
        cn.isSelected = check;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();
        for (int i = 0; i < node.getChildCount(); i++) {
            checkSubTree(tp.pathByAddingChild(node.getChildAt(i)), check);
        }
        cn.allChildrenSelected = check;
        if (check) {
            checkedPaths.add(tp);
        } else {
            checkedPaths.remove(tp);
        }
    }

    public interface CheckChangeEventListener extends EventListener {

        public void checkStateChanged(CheckChangeEvent event);
    }

    public final class CheckBoxCellRenderer extends JPanel implements TreeCellRenderer {

        private static final long serialVersionUID = -7341833835878991719L;
        private final JCheckBox checkBox;

        public CheckBoxCellRenderer() {
            super();
            this.setLayout(new BorderLayout());
            checkBox = new JCheckBox();
            add(checkBox, BorderLayout.CENTER);
            setOpaque(false);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object obj = node.getUserObject();
            TreePath tp = new TreePath(node.getPath());
            CheckedNode cn = nodesCheckingState.get(tp);
            if (cn == null) {
                return this;
            }
            checkBox.setSelected(cn.isSelected);
            checkBox.setText(obj.toString());
            checkBox.setOpaque(cn.isSelected && cn.hasChildren && !cn.allChildrenSelected);
            return this;
        }
    }

    public class CheckChangeEvent extends EventObject {

        private static final long serialVersionUID = -8100230309044193368L;

        public CheckChangeEvent(Object source) {
            super(source);
        }
    }

    public class CheckedNode {

        boolean isSelected;
        boolean hasChildren;
        boolean allChildrenSelected;

        public CheckedNode(boolean isSelected_, boolean hasChildren_, boolean allChildrenSelected_) {
            isSelected = isSelected_;
            hasChildren = hasChildren_;
            allChildrenSelected = allChildrenSelected_;
        }
    }

    public class TreeSelectionModelAdapter extends DefaultTreeSelectionModel {

        private static final long serialVersionUID = -8190634240451667286L;

        // Totally disabling the selection mechanism
        @Override
        public void setSelectionPath(TreePath path) {
        }

        @Override
        public void addSelectionPath(TreePath path) {
        }

        @Override
        public void removeSelectionPath(TreePath path) {
        }

        @Override
        public void setSelectionPaths(TreePath[] pPaths) {
        }
    }

}
