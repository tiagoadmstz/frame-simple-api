/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.frames;

import br.com.fs.api.dal.EntityManagerHelper;
import br.com.fs.api.interfaces.ListenerPatternAdapter;
import br.com.fs.api.interfaces.ManipulaFrames;
import br.com.fs.api.interfaces.entities.Frame_Sistema;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author tiago.teixeira
 */
public final class Form_Main extends ManipulaFrames {

    private static final long serialVersionUID = -419029088399611882L;
    private final Listener_Main listener;

    /**
     * Creates new form Main_Form
     */
    public Form_Main() {
        setImageIcon();
        initComponents();
        stateForm();
        startStatusBar();
        listener = new Listener_Main(this);
        //cadastrarTelas();
    }

    public JPopupMenu getPmAgenda() {
        return pmAgenda;
    }

    private void stateForm() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public JMenuItem getItemFechar() {
        return itemFechar;
    }

    private void startStatusBar() {
        statusBar.startStatusBar(this);
    }

    @Override
    public Optional<List<AbstractButton>> getListButtons() {
        return Optional.of(Arrays.asList(btAtestado, btGuiaSADT, btAtendimentosPrevios, btExamesSolicitados, btGuiaSADT, btReceituario,
                itemSelecionarBenef, itemAguardando, itemAtendido, itemConsultaStatus, itemDesmarcado, itemFaltou, itemMarcado, itemReagendou));
    }

    @Override
    public Optional<List<AbstractButton>> getListMenus() {
        List<AbstractButton> lista = new ArrayList();
        forMenu(menuBarCbiDefault.getComponents(), lista);
        return Optional.ofNullable(lista);
    }

    public List<AbstractButton> getMenuList() {
        List<AbstractButton> lista = new ArrayList();
        forMenu(menuBarCbiDefault.getComponents(), lista);
        return lista;
    }

    public boolean internalFrameIsOpen(JInternalFrame iframe) {
        return listener.internalFrameIsOpen(iframe);
    }

    private void forMenu(Component[] components, List<AbstractButton> lista) {
        for (Component comp : components) {
            if (comp instanceof JMenu) {
                JMenu menu = (JMenu) comp;
                for (Component a : menu.getMenuComponents()) {
                    if (a instanceof JMenu) {
                        JMenu m = (JMenu) a;
                        forMenu(m.getMenuComponents(), lista);
                    } else if (a instanceof JMenuItem) {
                        JMenuItem i = (JMenuItem) a;
                        lista.add(i);
                    }
                }
            } else if (comp instanceof JMenuItem) {
                JMenuItem i = (JMenuItem) comp;
                lista.add(i);
            }
        }
    }

    public void enableDisableMenuLateral(boolean enable) {
        btAtendimentosPrevios.setEnabled(enable);
        btAtestado.setEnabled(enable);
        btExamesSolicitados.setEnabled(enable);
        btGuiaSADT.setEnabled(enable);
        btReceituario.setEnabled(enable);
    }

    public JMenuItem getItemGerenciadorRelatorios() {
        return itemGerenciadorRelatorios;
    }

    private void cadastrarTelas(EntityManagerHelper emh) {
        getListMenus().ifPresent(lista -> {
            lista.stream()
                    .filter(menu -> !menu.getText().equals("Logout") && !menu.getText().equals("Fechar"))
                    .forEach(menu -> emh.getOperation(EntityManagerHelper.ATUALIZAR, new Frame_Sistema(null, null, menu.getText()), EntityManagerHelper.DERBYDB_PU));
        });
    }

    public JComboBox<String> getCbLocaisAtendimento() {
        return cbLocaisAtendimento;
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public JInternalFrame getIf_exames_solicitados() {
        return if_exames_solicitados;
    }

    public JInternalFrame getIf_lista_espera() {
        return if_lista_espera;
    }

    public JInternalFrame getIf_atendimentos() {
        return if_atendimentos;
    }

    public JTable getTbExamesSolicitados() {
        return tbExamesSolicitados;
    }

    public JTable getTbListaEspera() {
        return tbListaEspera;
    }

    public JLabel getLbNomeBeneficiario() {
        return lbNomeBeneficiario;
    }

    public JLabel getLbStatus() {
        return lbStatus;
    }

    public JTable getTbExames() {
        return tbExames;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        if_lista_espera = new javax.swing.JInternalFrame();
        painelControle = new javax.swing.JPanel();
        lbLocaisAtendimento = new javax.swing.JLabel();
        cbLocaisAtendimento = new javax.swing.JComboBox<>();
        painelMain_ListaEspera = new javax.swing.JPanel();
        scListaEspera = new javax.swing.JScrollPane();
        tbListaEspera = new javax.swing.JTable();
        if_exames_solicitados = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        scExamesSolicitados = new javax.swing.JScrollPane();
        tbExamesSolicitados = new javax.swing.JTable();
        scExames = new javax.swing.JScrollPane();
        tbExames = new javax.swing.JTable();
        pmAgenda = new javax.swing.JPopupMenu();
        itemSelecionarBenef = new javax.swing.JMenuItem();
        menuStatus = new javax.swing.JMenu();
        itemAguardando = new javax.swing.JMenuItem();
        itemAtendido = new javax.swing.JMenuItem();
        itemConsultaStatus = new javax.swing.JMenuItem();
        itemDesmarcado = new javax.swing.JMenuItem();
        itemMarcado = new javax.swing.JMenuItem();
        itemFaltou = new javax.swing.JMenuItem();
        itemReagendou = new javax.swing.JMenuItem();
        if_atendimentos = new javax.swing.JInternalFrame();
        panelMain = new javax.swing.JPanel();
        painelAtalhosResumo = new javax.swing.JPanel();
        lbNomeBeneficiario = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btAtestado = new javax.swing.JButton();
        btGuiaSADT = new javax.swing.JButton();
        btAtendimentosPrevios = new javax.swing.JButton();
        btExamesSolicitados = new javax.swing.JButton();
        btReceituario = new javax.swing.JButton();
        desktopPane = new javax.swing.JDesktopPane();
        statusBar = new br.com.fs.api.beans.JStatusBarCBI();
        menuBarCbiDefault = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        itemAgendamento = new javax.swing.JMenuItem();
        itemListaEspera = new javax.swing.JMenuItem();
        menuCadastroGeral = new javax.swing.JMenu();
        itemProfissional = new javax.swing.JMenuItem();
        itemBeneficiario = new javax.swing.JMenuItem();
        itemUsuarioProntuario = new javax.swing.JMenuItem();
        separador1 = new javax.swing.JPopupMenu.Separator();
        itemLogout = new javax.swing.JMenuItem();
        itemFechar = new javax.swing.JMenuItem();
        menuProtuario = new javax.swing.JMenu();
        itemFichaClinica = new javax.swing.JMenuItem();
        itemEvolucaoClinica = new javax.swing.JMenuItem();
        menuSae = new javax.swing.JMenu();
        menuCadastroSae = new javax.swing.JMenu();
        itemMeioLocomocao = new javax.swing.JMenuItem();
        itemPreAtendimento = new javax.swing.JMenuItem();
        itemConsulta = new javax.swing.JMenuItem();
        itemAnotacoes = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        itemGerenciadorRelatorios = new javax.swing.JMenuItem();
        menuPesquisa = new javax.swing.JMenu();
        itemPesqExamesLab = new javax.swing.JMenuItem();
        menuIntranet = new javax.swing.JMenu();
        itemChat = new javax.swing.JMenuItem();

        if_lista_espera.setClosable(true);
        if_lista_espera.setIconifiable(true);
        if_lista_espera.setMaximizable(true);
        if_lista_espera.setResizable(true);
        if_lista_espera.setTitle("Lista de Espera");
        if_lista_espera.setToolTipText("");
        if_lista_espera.setNormalBounds(new java.awt.Rectangle(400, 400, 400, 400));
        if_lista_espera.setPreferredSize(new java.awt.Dimension(474, 401));
        try {
            if_lista_espera.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        if_lista_espera.setVisible(true);

        lbLocaisAtendimento.setText("Local de Atendimento");

        javax.swing.GroupLayout painelControleLayout = new javax.swing.GroupLayout(painelControle);
        painelControle.setLayout(painelControleLayout);
        painelControleLayout.setHorizontalGroup(
            painelControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelControleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLocaisAtendimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbLocaisAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelControleLayout.setVerticalGroup(
            painelControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelControleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelControleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLocaisAtendimento)
                    .addComponent(cbLocaisAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbListaEspera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Matrícula", "Nome do Beneficiário", "Hora de Chegada", "Tempo de Espera"
            }
        ));
        scListaEspera.setViewportView(tbListaEspera);

        javax.swing.GroupLayout painelMain_ListaEsperaLayout = new javax.swing.GroupLayout(painelMain_ListaEspera);
        painelMain_ListaEspera.setLayout(painelMain_ListaEsperaLayout);
        painelMain_ListaEsperaLayout.setHorizontalGroup(
            painelMain_ListaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scListaEspera, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        painelMain_ListaEsperaLayout.setVerticalGroup(
            painelMain_ListaEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scListaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout if_lista_esperaLayout = new javax.swing.GroupLayout(if_lista_espera.getContentPane());
        if_lista_espera.getContentPane().setLayout(if_lista_esperaLayout);
        if_lista_esperaLayout.setHorizontalGroup(
            if_lista_esperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelMain_ListaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelControle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        if_lista_esperaLayout.setVerticalGroup(
            if_lista_esperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, if_lista_esperaLayout.createSequentialGroup()
                .addComponent(painelControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelMain_ListaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        if_exames_solicitados.setClosable(true);
        if_exames_solicitados.setIconifiable(true);
        if_exames_solicitados.setMaximizable(true);
        if_exames_solicitados.setResizable(true);
        if_exames_solicitados.setTitle("Exames Solicitados");
        if_exames_solicitados.setLayer(2);
        if_exames_solicitados.setName(""); // NOI18N
        if_exames_solicitados.setNormalBounds(new java.awt.Rectangle(100, 100, 100, 100));
        if_exames_solicitados.setVisible(true);

        jLabel1.setText("Status:");

        lbStatus.setBackground(new java.awt.Color(0, 0, 0));
        lbStatus.setForeground(new java.awt.Color(255, 0, 0));
        lbStatus.setText("Parado");

        tbExamesSolicitados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scExamesSolicitados.setViewportView(tbExamesSolicitados);

        tbExames.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scExames.setViewportView(tbExames);

        javax.swing.GroupLayout if_exames_solicitadosLayout = new javax.swing.GroupLayout(if_exames_solicitados.getContentPane());
        if_exames_solicitados.getContentPane().setLayout(if_exames_solicitadosLayout);
        if_exames_solicitadosLayout.setHorizontalGroup(
            if_exames_solicitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(if_exames_solicitadosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(if_exames_solicitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(if_exames_solicitadosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scExames)
                    .addComponent(scExamesSolicitados)))
        );
        if_exames_solicitadosLayout.setVerticalGroup(
            if_exames_solicitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(if_exames_solicitadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(if_exames_solicitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scExamesSolicitados, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scExames, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
        );

        if_exames_solicitados.getAccessibleContext().setAccessibleDescription("");

        itemSelecionarBenef.setText("Selecionar Beneficiário");
        itemSelecionarBenef.setActionCommand("selecionarBeneficiario");
        pmAgenda.add(itemSelecionarBenef);

        menuStatus.setText("Mudar Status");

        itemAguardando.setText("Aguardando");
        itemAguardando.setActionCommand("status");
        menuStatus.add(itemAguardando);

        itemAtendido.setText("Atendido");
        itemAtendido.setActionCommand("status");
        menuStatus.add(itemAtendido);

        itemConsultaStatus.setText("Em consulta");
        itemConsultaStatus.setActionCommand("status");
        menuStatus.add(itemConsultaStatus);

        itemDesmarcado.setText("Desmarcado");
        itemDesmarcado.setActionCommand("status");
        menuStatus.add(itemDesmarcado);

        itemMarcado.setText("Marcado");
        itemMarcado.setActionCommand("status");
        menuStatus.add(itemMarcado);

        itemFaltou.setText("Não compareceu");
        itemFaltou.setActionCommand("status");
        menuStatus.add(itemFaltou);

        itemReagendou.setText("Reagendou");
        itemReagendou.setActionCommand("status");
        menuStatus.add(itemReagendou);

        pmAgenda.add(menuStatus);

        if_atendimentos.setClosable(true);
        if_atendimentos.setIconifiable(true);
        if_atendimentos.setMaximizable(true);
        if_atendimentos.setResizable(true);
        if_atendimentos.setTitle("Atendimentos Prévios/Anteriores");
        if_atendimentos.setToolTipText("");
        if_atendimentos.setNormalBounds(new java.awt.Rectangle(400, 400, 400, 400));
        if_atendimentos.setPreferredSize(new java.awt.Dimension(474, 401));
        try {
            if_atendimentos.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        if_atendimentos.setVisible(true);

        javax.swing.GroupLayout if_atendimentosLayout = new javax.swing.GroupLayout(if_atendimentos.getContentPane());
        if_atendimentos.getContentPane().setLayout(if_atendimentosLayout);
        if_atendimentosLayout.setHorizontalGroup(
            if_atendimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        if_atendimentosLayout.setVerticalGroup(
            if_atendimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Registro Eletrônico em Saúde");
        setName("main_form"); // NOI18N

        lbNomeBeneficiario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNomeBeneficiario.setText("Nenhum Beneficiário Selecionado");
        lbNomeBeneficiario.setToolTipText("Este beneficiário será utilizado como base para todas as atividades do sistema");
        lbNomeBeneficiario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbNomeBeneficiario.setMaximumSize(new java.awt.Dimension(250, 14));
        lbNomeBeneficiario.setMinimumSize(new java.awt.Dimension(250, 14));
        lbNomeBeneficiario.setPreferredSize(new java.awt.Dimension(250, 14));

        btAtestado.setText("Atestado");
        btAtestado.setToolTipText("Emitir Atestado");
        btAtestado.setActionCommand("emitirAtestado");
        btAtestado.setEnabled(false);
        btAtestado.setFocusable(false);
        btAtestado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAtestado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btGuiaSADT.setText("Guia SADT");
        btGuiaSADT.setActionCommand("emitirGuiaSadt");
        btGuiaSADT.setEnabled(false);
        btGuiaSADT.setFocusable(false);
        btGuiaSADT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btGuiaSADT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btAtendimentosPrevios.setText("Atendimentos Prévios/Anteriores");
        btAtendimentosPrevios.setToolTipText("");
        btAtendimentosPrevios.setActionCommand("atendimentos_previos");
        btAtendimentosPrevios.setEnabled(false);
        btAtendimentosPrevios.setFocusable(false);
        btAtendimentosPrevios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAtendimentosPrevios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btExamesSolicitados.setText("Exames");
        btExamesSolicitados.setActionCommand("exames_solicitados");
        btExamesSolicitados.setEnabled(false);
        btExamesSolicitados.setFocusable(false);
        btExamesSolicitados.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExamesSolicitados.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btReceituario.setText("Receituário");
        btReceituario.setActionCommand("receituario");
        btReceituario.setEnabled(false);
        btReceituario.setFocusable(false);
        btReceituario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btReceituario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout painelAtalhosResumoLayout = new javax.swing.GroupLayout(painelAtalhosResumo);
        painelAtalhosResumo.setLayout(painelAtalhosResumoLayout);
        painelAtalhosResumoLayout.setHorizontalGroup(
            painelAtalhosResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNomeBeneficiario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(painelAtalhosResumoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelAtalhosResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btExamesSolicitados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btGuiaSADT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAtestado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelAtalhosResumoLayout.createSequentialGroup()
                        .addGroup(painelAtalhosResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAtendimentosPrevios)
                            .addComponent(btReceituario, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelAtalhosResumoLayout.setVerticalGroup(
            painelAtalhosResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAtalhosResumoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNomeBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAtestado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btGuiaSADT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAtendimentosPrevios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExamesSolicitados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btReceituario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        desktopPane.setMinimumSize(new java.awt.Dimension(685, 505));
        desktopPane.setOpaque(false);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addComponent(painelAtalhosResumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelAtalhosResumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuArquivo.setText("Arquivo");

        itemAgendamento.setText("Agendamento");
        itemAgendamento.setActionCommand("agendamento");
        itemAgendamento.setEnabled(false);
        menuArquivo.add(itemAgendamento);

        itemListaEspera.setText("Lista de Espera");
        itemListaEspera.setActionCommand("lista_espera");
        menuArquivo.add(itemListaEspera);

        menuCadastroGeral.setText("Cadastros");

        itemProfissional.setText("Profissional");
        itemProfissional.setActionCommand("cadProfissional");
        itemProfissional.setEnabled(false);
        menuCadastroGeral.add(itemProfissional);

        itemBeneficiario.setText("Beneficiário");
        itemBeneficiario.setActionCommand("cadBeneficiario");
        itemBeneficiario.setEnabled(false);
        menuCadastroGeral.add(itemBeneficiario);

        itemUsuarioProntuario.setText("Usuário Prontuário");
        itemUsuarioProntuario.setActionCommand("cadUsuarioProntuario");
        itemUsuarioProntuario.setEnabled(false);
        menuCadastroGeral.add(itemUsuarioProntuario);

        menuArquivo.add(menuCadastroGeral);
        menuArquivo.add(separador1);

        itemLogout.setText("Logout");
        itemLogout.setActionCommand("logout");
        menuArquivo.add(itemLogout);

        itemFechar.setText("Fechar");
        itemFechar.setActionCommand("fechar");
        menuArquivo.add(itemFechar);

        menuBarCbiDefault.add(menuArquivo);

        menuProtuario.setText("Prontuário");

        itemFichaClinica.setText("Observação Clínica (C.N.)");
        itemFichaClinica.setToolTipText("");
        itemFichaClinica.setActionCommand("fichaClinica");
        itemFichaClinica.setEnabled(false);
        menuProtuario.add(itemFichaClinica);

        itemEvolucaoClinica.setText("Evolução Clínica");
        itemEvolucaoClinica.setActionCommand("evolucaoClinica");
        itemEvolucaoClinica.setEnabled(false);
        menuProtuario.add(itemEvolucaoClinica);

        menuBarCbiDefault.add(menuProtuario);

        menuSae.setText("SAE");

        menuCadastroSae.setText("Cadastros");

        itemMeioLocomocao.setText("Meio de Locomoção");
        itemMeioLocomocao.setActionCommand("cadMeioLocomocao");
        itemMeioLocomocao.setEnabled(false);
        menuCadastroSae.add(itemMeioLocomocao);

        menuSae.add(menuCadastroSae);

        itemPreAtendimento.setText("Pré-Atendimento");
        itemPreAtendimento.setActionCommand("preAtendimentoSae");
        itemPreAtendimento.setEnabled(false);
        menuSae.add(itemPreAtendimento);

        itemConsulta.setText("Consulta");
        itemConsulta.setActionCommand("consultaSae");
        itemConsulta.setEnabled(false);
        menuSae.add(itemConsulta);

        itemAnotacoes.setText("Anotações");
        itemAnotacoes.setActionCommand("anotacoesSae");
        itemAnotacoes.setEnabled(false);
        menuSae.add(itemAnotacoes);

        menuBarCbiDefault.add(menuSae);

        menuRelatorios.setText("Relatórios");

        itemGerenciadorRelatorios.setText("Gerenciador de Relatórios");
        itemGerenciadorRelatorios.setActionCommand("reportManager");
        itemGerenciadorRelatorios.setEnabled(false);
        menuRelatorios.add(itemGerenciadorRelatorios);

        menuBarCbiDefault.add(menuRelatorios);

        menuPesquisa.setText("Pesquisa");

        itemPesqExamesLab.setText("Exames Laboratoriais");
        itemPesqExamesLab.setActionCommand("examesLab");
        itemPesqExamesLab.setEnabled(false);
        menuPesquisa.add(itemPesqExamesLab);

        menuBarCbiDefault.add(menuPesquisa);

        menuIntranet.setText("Intranet");

        itemChat.setText("Chat");
        itemChat.setActionCommand("chat");
        menuIntranet.add(itemChat);

        menuBarCbiDefault.add(menuIntranet);

        setJMenuBar(menuBarCbiDefault);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
                    .addComponent(panelMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtendimentosPrevios;
    private javax.swing.JButton btAtestado;
    private javax.swing.JButton btExamesSolicitados;
    private javax.swing.JButton btGuiaSADT;
    private javax.swing.JButton btReceituario;
    private javax.swing.JComboBox<String> cbLocaisAtendimento;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JInternalFrame if_atendimentos;
    private javax.swing.JInternalFrame if_exames_solicitados;
    private javax.swing.JInternalFrame if_lista_espera;
    private javax.swing.JMenuItem itemAgendamento;
    private javax.swing.JMenuItem itemAguardando;
    private javax.swing.JMenuItem itemAnotacoes;
    private javax.swing.JMenuItem itemAtendido;
    private javax.swing.JMenuItem itemBeneficiario;
    private javax.swing.JMenuItem itemChat;
    private javax.swing.JMenuItem itemConsulta;
    private javax.swing.JMenuItem itemConsultaStatus;
    private javax.swing.JMenuItem itemDesmarcado;
    private javax.swing.JMenuItem itemEvolucaoClinica;
    private javax.swing.JMenuItem itemFaltou;
    private javax.swing.JMenuItem itemFechar;
    private javax.swing.JMenuItem itemFichaClinica;
    private javax.swing.JMenuItem itemGerenciadorRelatorios;
    private javax.swing.JMenuItem itemListaEspera;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JMenuItem itemMarcado;
    private javax.swing.JMenuItem itemMeioLocomocao;
    private javax.swing.JMenuItem itemPesqExamesLab;
    private javax.swing.JMenuItem itemPreAtendimento;
    private javax.swing.JMenuItem itemProfissional;
    private javax.swing.JMenuItem itemReagendou;
    private javax.swing.JMenuItem itemSelecionarBenef;
    private javax.swing.JMenuItem itemUsuarioProntuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JLabel lbLocaisAtendimento;
    private javax.swing.JLabel lbNomeBeneficiario;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuBar menuBarCbiDefault;
    private javax.swing.JMenu menuCadastroGeral;
    private javax.swing.JMenu menuCadastroSae;
    private javax.swing.JMenu menuIntranet;
    private javax.swing.JMenu menuPesquisa;
    private javax.swing.JMenu menuProtuario;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenu menuSae;
    private javax.swing.JMenu menuStatus;
    private javax.swing.JPanel painelAtalhosResumo;
    private javax.swing.JPanel painelControle;
    private javax.swing.JPanel painelMain_ListaEspera;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPopupMenu pmAgenda;
    private javax.swing.JScrollPane scExames;
    private javax.swing.JScrollPane scExamesSolicitados;
    private javax.swing.JScrollPane scListaEspera;
    private javax.swing.JPopupMenu.Separator separador1;
    private br.com.fs.api.beans.JStatusBarCBI statusBar;
    private javax.swing.JTable tbExames;
    private javax.swing.JTable tbExamesSolicitados;
    private javax.swing.JTable tbListaEspera;
    // End of variables declaration//GEN-END:variables

    public class Listener_Main extends ListenerPatternAdapter<Form_Main> {

        private final EntityManagerHelper emh = new EntityManagerHelper();
        private final Model_Plano_Beneficiario_Resumo beneficiario = new Model_Plano_Beneficiario_Resumo();
        private final Model_Prestador_Profissional prestador_profissional = new Model_Prestador_Profissional();
        private final TableModel_Exames_Pedidos model_exames_pedidos = new TableModel_Exames_Pedidos();
        private TableModel_Exames_Laboratorios model;
        private List<Pedido_Lab> pedidos = new ArrayList();
        private DrPaccaUtil drPaccaUtil = new DrPaccaUtil();
        private DrPaccaUtil_Cloud drPaccaCloud = new DrPaccaUtil_Cloud();
        private BiodiagnoseUtil biodiagnoseUtil = new BiodiagnoseUtil();
        private Thread t_exames;

        public Listener_Main(Form_Main form) {
            super(form);
            initComponents();
        }

        @Override
        protected void initComponents() {
            attachListener();
            fecharESC(form.getItemFechar());
            validarPermissoes();
        }

        @Override
        public void attachListener() {
            form.getMenuList().stream().forEach(item -> item.addActionListener(this));
            form.getListButtons().ifPresent(lista -> lista.forEach(bt -> bt.addActionListener(this)));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            switch (e.getActionCommand()) {
                case "agendamento":
                    Form_Agendamento agenda = (Form_Agendamento) ControleInstancias.getInstance(Form_Agendamento.class.getName());
                    agenda.setVisible(true);
                    break;
                case "logout":
                    if (MessageFactory.getQuestionMessage(MessageFactory.LOGOUT, form)) {
                        Form_Login login = (Form_Login) ControleInstancias.logout(Form_Login.class);
                        login.setVisible(true);
                    }
                    break;
                case "fechar":
                    form.fecharSistema();
                    break;
                case "cadProfissional":
                    Form_Cad_Profissional cad_profissional = (Form_Cad_Profissional) ControleInstancias.getInstance(Form_Cad_Profissional.class.getName());
                    cad_profissional.setVisible(true);
                    break;
                case "cadBeneficiario":
                    break;
                case "cadUsuarioProntuario":
                    Form_Manage_Usuario cad_usuario = (Form_Manage_Usuario) ControleInstancias.getInstance(Form_Manage_Usuario.class.getName());
                    cad_usuario.setVisible(true);
                    break;
                case "fichaClinica":
                    initProntuario();
                    break;
                case "evolucaoClinica":
                    Form_Evolucao evolucaoClinica = (Form_Evolucao) ControleInstancias.getInstance(Form_Evolucao.class.getName());
                    evolucaoClinica.setVisible(true);
                    break;
                case "cadMeioLocomocao":
                    Form_Cad_Meio_Locomocao cadMeioLocomocao = (Form_Cad_Meio_Locomocao) ControleInstancias.getInstance(Form_Cad_Meio_Locomocao.class.getName());
                    cadMeioLocomocao.setVisible(true);
                    break;
                case "preAtendimentoSae":
                    Form_Pre_Atendimento preAtendimento = (Form_Pre_Atendimento) ControleInstancias.getInstance(Form_Pre_Atendimento.class.getName());
                    preAtendimento.setVisible(true);
                    break;
                case "consultaSae":
                    Form_Consulta consulta = (Form_Consulta) ControleInstancias.getInstance(Form_Consulta.class.getName());
                    consulta.setVisible(true);
                    break;
                case "anotacoesSae":
                    Form_Anotacao anotacao = (Form_Anotacao) ControleInstancias.getInstance(Form_Anotacao.class.getName());
                    anotacao.setVisible(true);
                    break;
                case "reportManager":
                    openReportManager();
                    break;
                case "examesLab":
                    Form_Pesquisa_Exames exames = (Form_Pesquisa_Exames) ControleInstancias.getInstance(Form_Pesquisa_Exames.class.getName());
                    exames.setVisible(true);
                    break;
                case "emitirAtestado":
                    Form_Atestado atestado = (Form_Atestado) ControleInstancias.getInstance(Form_Atestado.class.getName());
                    atestado.setBeneficiario(beneficiario);
                    atestado.setVisible(true);
                    break;
                case "lista_espera":
                    getListaEspera();
                    break;
                case "exames_solicitados":
                    getExamesSolicitados();
                    break;
                case "atendimentos_previos":
                    initAtendimentosPrevios();
                    break;
                case "emitirGuiaSadt":
                    Form_Guia_SP_SADT guia_sadt = (Form_Guia_SP_SADT) ControleInstancias.getInstance(Form_Guia_SP_SADT.class.getName());
                    guia_sadt.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    initGuiaSpSadt();
                    guia_sadt.setVisible(true);
                    break;
                case "chat":
                    Chat_Client chat = (Chat_Client) ControleInstancias.getInstance(Chat_Client.class.getName());
                    chat.setIconImage(form.getIconImage());
                    chat.setLogoImg(new ImageIcon(getClass().getResource("/br/com/sres/img/logox.png")).getImage());
                    chat.setVisible(true);
                    break;
                case "selecionarBeneficiario":
                    selecionarBeneficiario();
                    break;
                case "status":
                    mudarStatus(((JMenuItem) e.getSource()).getText());
                    break;
                default:
                    MessageFactory.getAppMessage(MessageFactory.EM_DESENVOLVIMENTO, form);
                    break;
            }
            form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        private void openReportManager() {
            Form_ReportManager rm = (Form_ReportManager) ControleInstancias.getInstance(Form_ReportManager.class.getName());
            rm.setVisible(true);
        }

        private void initProntuario() {
            if (!form.getLbNomeBeneficiario().getText().equals("")) {
                Form_Prontuario prontuario = (Form_Prontuario) ControleInstancias.getInstance(Form_Prontuario.class.getName());
                prontuario.initializeForm(beneficiario);
                prontuario.setVisible(true);
            }
        }

        private void initGuiaSpSadt() {
            if (ControleInstancias.isInstanced(Form_Guia_SP_SADT.class.getName())) {
                if (beneficiario.getBeneficiario() != null) {
                    Form_Guia_SP_SADT guia = (Form_Guia_SP_SADT) ControleInstancias.getInstance(Form_Guia_SP_SADT.class.getName());
                    guia.initializeForm(beneficiario);
                }
            }
        }

        private void selecionarBeneficiario() {
            try {
                TableModel_Lista_Espera model = (TableModel_Lista_Espera) form.getTbListaEspera().getModel();
                setBeneficiario(model.getObject(form.getTbListaEspera().getRowSorter().convertRowIndexToModel(form.getTbListaEspera().getSelectedRow())).getPlano_beneficiario());
            } catch (Exception e) {
            }
        }

        private void mudarStatus(String actionComand) {
            try {
                TableModel_Lista_Espera model = (TableModel_Lista_Espera) form.getTbListaEspera().getModel();
                Model_Agenda_Movimentacao mov = model.getObject(form.getTbListaEspera().getSelectedRow());
                switch (actionComand) {
                    case "Aguardando":
                        mov.setStatus_difer_atendimento(STATUS_AGENDA_ATENDIMENTO.A);
                        mov.setData_chegada(LocalDateTime.now());
                        break;
                    case "Atendido":
                        mov.setStatus_difer_atendimento(STATUS_AGENDA_ATENDIMENTO.T);
                        mov.setData_atendimento(LocalDateTime.now());
                        break;
                    case "Em consulta":
                        mov.setStatus_difer_atendimento(STATUS_AGENDA_ATENDIMENTO.C);
                        break;
                    case "Desmarcado":
                        mov.setStatus_difer_atendimento(STATUS_AGENDA_ATENDIMENTO.D);
                        break;
                    case "Não compareceu":
                        mov.setStatus_difer_atendimento(STATUS_AGENDA_ATENDIMENTO.F);
                        break;
                    case "Marcado":
                        mov.setStatus_difer_atendimento(STATUS_AGENDA_ATENDIMENTO.M);
                        break;
                    case "Reagendou":
                        mov.setStatus_difer_atendimento(STATUS_AGENDA_ATENDIMENTO.R);
                        break;
                }
                model.fireTableDataChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void getListaEspera() {
            Usuario u = ControleUsuario.getUsuarioAtual();
            if (u.getPrestador_profissional() != null) {
                for (Model_Prestador_Profissional pp : u.getPrestador_profissional()) {
                    form.getCbLocaisAtendimento().addItem(pp.getPrestador().getNome());
                }
                List<Model_Agenda_Movimentacao> agendamento = getListEspera(u.getPrestador_profissional().get(0).getPrestador(), u.getPrestador_profissional().get(0).getProfissional());
                TableModel_Lista_Espera lista_espera = new TableModel_Lista_Espera(AgendaUtil.verificarMovimentacao(agendamento));
                form.getTbListaEspera().setModel(lista_espera);
                setColumnDesign(form.getTbListaEspera(), new DefaultCBIRenderer());
                TableRowSorter sorter = new TableRowSorter(lista_espera);
                setColumnFilter(form.getTbListaEspera(), sorter);
                setColumnSize(form.getTbListaEspera(), 80, 80, 80, 100, 350, 150, 100, 100);
                form.getTbListaEspera().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            form.getPmAgenda().show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                });
                form.getCbLocaisAtendimento().addItemListener(event -> {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        for (Model_Prestador_Profissional mpp : u.getPrestador_profissional()) {
                            if (mpp.getPrestador().getNome().equals(form.getCbLocaisAtendimento().getSelectedItem().toString())) {
                                ((TableModelDefaultAdapter) form.getTbListaEspera().getModel()).setLista(getListEspera(mpp.getPrestador(), mpp.getProfissional()));
                                prestador_profissional.copiar(mpp);
                                break;
                            }
                        }
                    }
                });
            } else {
                MessageFactory.getExceptionMessage("Nenhum profissional vinculado ao usuário!", form);
            }
            this.openInternalFrame(form.getIf_lista_espera());
        }

        private List<Model_Agenda_Movimentacao> getListEspera(Model_Prestador_Servico_Resumo local_atendimento, Medico profissional) {
            try {
                List<Model_Agenda_Movimentacao> agendamento = (List<Model_Agenda_Movimentacao>) emh.getObjectListNamedQuery(Model_Agenda_Movimentacao.class, "model_agenda_movimentacao.findByProfissional", new String[]{"paramDayStart", "paramDayEnd", "paramPrestador", "paramProfissional", "paramBloqueado"}, new Object[]{LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)).plusDays(1l), local_atendimento, profissional, BOOLEANO.N}, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList());
                return agendamento;
            } catch (Exception e) {
                return null;
            }
        }

        private void initAtendimentosPrevios() {
            try {
                this.openInternalFrame(form.getIf_atendimentos());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void getExamesSolicitados() {
            if (beneficiario.getBeneficiario() != null) {
                this.setExamesSolicitados();
                this.openInternalFrame(form.getIf_exames_solicitados());
            } else {
                MessageFactory.getAppMessage(MessageFactory.BENEFICIARIO_NAO_SELECIONADO, form);
            }
        }

        private void setExamesSolicitados() {
            model_exames_pedidos.deletarLista();
            model_exames_pedidos.setLista((List<Guia_Procedimento>) emh.getObjectListNamedQuery(Guia_Procedimento.class, "guia_procedimento.findByBeneficiario", new String[]{"paramTipoGuia", "paramMatricula", "paramData", "paramCancelado"}, new Object[]{Arrays.asList(4), beneficiario.getBeneficiario().getMatricula(), LocalDateTime.now().minusDays(60), BOOLEANO.N}, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList()));
            form.getTbExamesSolicitados().setModel(model_exames_pedidos);
            setColumnDesign(form.getTbExamesSolicitados(), new DefaultCBIRenderer());
            model = new TableModel_Exames_Laboratorios();
            form.getTbExames().setModel(model);
            form.getTbExames().addMouseListener(new MouseAdapter() {
                @Override
                public synchronized void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        try {
                            //JOptionPane.showMessageDialog(form, "Pesquisando Exame - Aguarde");
                            new Thread(pegarArquivoExame()).start();
                        } catch (Exception ex) {
                        }
                    }
                }
            });
            setColumnDesign(form.getTbExames(), new DefaultCBIRenderer());
            if (t_exames != null) {
                t_exames.interrupt();
            }
            t_exames = new Thread(pesquisarExames(), "exames");
            t_exames.start();
        }

        private void openInternalFrame(JInternalFrame frame) {
            try {
                if (!internalFrameIsOpen(frame)) {
                    form.getDesktopPane().add(frame);
                    form.getDesktopPane().getDesktopManager().openFrame(frame);
                    frame.setFrameIcon(new ImageIcon(form.getIconImage()));
                    form.getDesktopPane().getDesktopManager().maximizeFrame(frame);
                    frame.setMaximum(true);
                    frame.setVisible(true);
                }
                if (frame.isIcon()) {
                    form.getDesktopPane().getDesktopManager().maximizeFrame(frame);
                }
            } catch (Exception e) {
            }
        }

        public boolean internalFrameIsOpen(JInternalFrame iframe) {
            for (JInternalFrame frame : form.getDesktopPane().getAllFrames()) {
                if (frame.getTitle().equals(iframe.getTitle())) {
                    return true;
                }
            }
            return false;
        }

        public Model_Plano_Beneficiario_Resumo getBeneficiario() {
            return beneficiario;
        }

        public void setBeneficiario(Model_Plano_Beneficiario_Resumo beneficiario) {
            try {
                this.beneficiario.copiar(beneficiario);
                form.getLbNomeBeneficiario().setText(beneficiario.getBeneficiario().getNome());
                if (this.internalFrameIsOpen(form.getIf_exames_solicitados())) {
                    this.setExamesSolicitados();
                }
                if (ControleInstancias.isInstanced(Form_Prontuario.class.getName())) {
                    ((Form_Prontuario) ControleInstancias.getInstance(Form_Prontuario.class.getName())).initializeForm(beneficiario);
                }
                form.enableDisableMenuLateral(true);
            } catch (Exception e) {
            }
        }

        public Model_Prestador_Profissional getProfissional() {
            return prestador_profissional;
        }

        public void setProfissional(Model_Prestador_Profissional profissional) {
            try {
                this.prestador_profissional.copiar(profissional);
            } catch (Exception e) {
            }
        }

        private void pesquisaResultado(Exame_Lab exame) {
            try {
                ExamesLaboratoriasUtil.openExameFile(exame.getResultado(), "pdf");
            } catch (Exception e) {
            }
        }

        private synchronized Runnable pegarArquivoExame() {
            return () -> {
                form.getIf_exames_solicitados().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Exame_Lab exame = model.getObject(form.getTbExames().getSelectedRow());
                if (exame.getResultado() == null) {
                    if (!exame.getLaboratorio().equals("BIODIAGNOSE")) {
                        if (exame.getDataEntrada().isAfter(LocalDate.of(2018, 7, 4))) {
                            drPaccaCloud.pegarResultadoExameDrPacca(exame, beneficiario.getBeneficiario(), DrPaccaUtil.PESQUISA_NOME);
                        } else {
                            drPaccaUtil.pegarResultadoExameDrPacca(exame, beneficiario.getBeneficiario(), DrPaccaUtil.PESQUISA_MATRICULA);
                            if (exame.getResultado().equals("") || exame.getResultado() == null) {
                                drPaccaUtil.pegarResultadoExameDrPacca(exame, beneficiario.getBeneficiario(), DrPaccaUtil.PESQUISA_NOME);
                            }
                        }
                        emh.getOperation(EntityManagerHelper.ATUALIZAR, exame, EntityManagerHelper.DERBYDB_PU);
                    } else {
                        biodiagnoseUtil.imprimirResultado(exame, beneficiario.getBeneficiario());
                        for (Pedido_Lab pdd : pedidos) {
                            if (pdd.getAmostra().equals(exame.getAmostra())) {
                                for (Exame_Lab ex : pdd.getExames()) {
                                    ex.setResultado(exame.getResultado());
                                }
                                emh.getOperation(EntityManagerHelper.ATUALIZAR, pdd, EntityManagerHelper.DERBYDB_PU);
                            }
                        }
                    }
                } else {
                    pesquisaResultado(exame);
                }
                form.getIf_exames_solicitados().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            };
        }

        private synchronized Runnable pesquisarExames() {
            return () -> {
                String nome = form.getLbNomeBeneficiario().getText();

                model.deletarLista();
                if (beneficiario.getBeneficiario().getMatricula() != null) {
                    //pesquisa os pedidos na base de dados
                    form.getLbStatus().setForeground(Color.blue);
                    form.getLbStatus().setText("Pesquisando no banco de dados");
                    pedidos = (List<Pedido_Lab>) emh.getObjectListNamedQuery(Pedido_Lab.class,
                            "lab.pedidos.findByMatriculaDatas", new String[]{"paramMatricula", "paramDataInicial", "paramDataFinal"},
                            new Object[]{beneficiario.getBeneficiario().getMatricula(), LocalDate.now().minusDays(60), LocalDate.now()},
                            EntityManagerHelper.DERBYDB_PU).orElse(new ArrayList());
                    //adiciona pedidos encontramos a tabela
                    pedidos.forEach(pdd -> model.addLista(pdd.getExames()));
                    //pega a última data e usa de data inicial para buscar nos laboratórios
                    for (int i = 0; i < 2; i++) {
                        LocalDate[] datas = getDataPesquisaLab(pedidos, i);
                        if (datas != null) {
                            System.out.println("Pesquisando entre " + datas[0].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " e " + datas[1].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            //pesquisa exames no laboratório Dr. Pacca
                            Thread pacca = new Thread(pesquisarExamesDrPacca(datas), "pacca");
                            pacca.start();
                            //pesquisa exames no novo portal do laboratório Dr.Pacca
                            Thread pacca_cloud = new Thread(pesquisarExamesDrPacca_Colud(datas), "pacca_cloud");
                            pacca_cloud.start();
                            //pesquisa exames no laboratório Bio Diagnose
                            Thread bio = new Thread(pesquisarExamesBiodiagnose(pedidos, datas), "bio");
                            bio.start();
                            new Thread(setStatus(pacca, bio)).start();
                            if (datas[0].equals(LocalDate.now().minusDays(60)) && datas[1].equals(LocalDate.now())) {
                                break;
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "Não encontramos este beneficiário!", "Beneficiário inexistente", JOptionPane.WARNING_MESSAGE);
                }
            };
        }

        private synchronized Runnable pesquisarExamesDrPacca(LocalDate[] datas) {
            return () -> {
                List<Pedido_Lab> pedidos_pacca = drPaccaUtil.pesquisarExames(DrPaccaUtil.PESQUISA_MATRICULA, beneficiario.getBeneficiario(), datas[0].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), datas[1].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                if (pedidos_pacca.isEmpty()) {
                    drPaccaUtil.pesquisarExames(DrPaccaUtil.PESQUISA_NOME, beneficiario.getBeneficiario(), datas[0].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), datas[1].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }

                pedidos_pacca.stream()
                        .filter(pedido -> pedidos.stream().noneMatch(pdd -> pdd.getAmostra().equals(pedido.getAmostra())))
                        .map((pedido) -> {
                            pedido.setBeneficiario(beneficiario.getBeneficiario());
                            emh.getOperation(EntityManagerHelper.ATUALIZAR, pedido, EntityManagerHelper.DERBYDB_PU);
                            return pedido;
                        }).forEachOrdered((pedido) -> {
                    model.addLista(pedido.getExames());
                });
            };
        }

        private synchronized Runnable pesquisarExamesDrPacca_Colud(LocalDate[] datas) {
            return () -> {
                List<Pedido_Lab> pedidos_pacca = drPaccaCloud.pesquisarExames(DrPaccaUtil_Cloud.PESQUISA_NOME, beneficiario.getBeneficiario(), datas[0].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), datas[1].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                pedidos_pacca.stream()
                        .filter(pedido -> pedidos.stream().noneMatch(pdd -> pdd.getAmostra().equals(pedido.getAmostra())))
                        .map((pedido) -> {
                            pedido.setBeneficiario(beneficiario.getBeneficiario());
                            emh.getOperation(EntityManagerHelper.ATUALIZAR, pedido, EntityManagerHelper.DERBYDB_PU);
                            return pedido;
                        }).forEachOrdered((pedido) -> {
                    model.addLista(pedido.getExames());
                });
            };
        }

        private synchronized Runnable pesquisarExamesBiodiagnose(List<Pedido_Lab> lista, LocalDate[] datas) {
            return () -> {
                List<Pedido_Lab> pedidos_bio = biodiagnoseUtil.pesquisa_Exames(beneficiario.getBeneficiario(), datas[0].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), datas[1].format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                pedidos_bio.stream()
                        .filter(pedido -> pedidos.stream().noneMatch(pdd -> pdd.getAmostra().equals(pedido.getAmostra())))
                        .map((pedido) -> {
                            pedido.setBeneficiario(beneficiario.getBeneficiario());
                            emh.getOperation(EntityManagerHelper.ATUALIZAR, pedido, EntityManagerHelper.DERBYDB_PU);
                            return pedido;
                        }).forEachOrdered((pedido) -> {
                    model.addLista(pedido.getExames());
                });
            };
        }

        private synchronized Runnable setStatus(Thread pacca, Thread bio) {
            return () -> {
                while (pacca.isAlive() || bio.isAlive()) {
                    if (!form.getLbStatus().getText().equals("Pesquisando nos Laboratórios")) {
                        form.getLbStatus().setForeground(new Color(0, 153, 0));
                        form.getLbStatus().setText("Pesquisando nos Laboratórios");
                    }
                }
                form.getLbStatus().setForeground(Color.red);
                form.getLbStatus().setText("Parado");
            };
        }

        private synchronized LocalDate[] getDataPesquisaLab(List<Pedido_Lab> pedidos, int inicio_ou_fim) {
            if (pedidos.size() > 0) {
                if (inicio_ou_fim == 0) {
                    //pega data inicial baseado na data inicial
                    Pedido_Lab p = pedidos.stream().sorted((p1, p2) -> p1.getDataEntrada().isAfter(p2.getDataEntrada()) ? 1 : -1).collect(Collectors.toList()).get(0);
                    LocalDate data_inicial = LocalDate.now().minusDays(60);
                    if (!data_inicial.isEqual(p.getDataEntrada())) {
                        return new LocalDate[]{data_inicial, p.getDataEntrada().minusDays(1l)};
                    }
                } else {
                    //pega data inicial baseado na data final
                    Pedido_Lab p = pedidos.stream().sorted((p1, p2) -> p1.getDataEntrada().isBefore(p2.getDataEntrada()) ? 1 : -1).collect(Collectors.toList()).get(0);
                    LocalDate data_inicial = LocalDate.now();
                    if (!data_inicial.isEqual(p.getDataEntrada())) {
                        return new LocalDate[]{p.getDataEntrada().plusDays(1l), data_inicial};
                    }
                }
            } else {
                return new LocalDate[]{LocalDate.now().minusDays(60), LocalDate.now()};
            }
            return null;
        }
    }
}
