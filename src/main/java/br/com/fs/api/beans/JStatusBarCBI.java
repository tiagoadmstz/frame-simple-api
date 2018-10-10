/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.beans;

import br.com.fs.api.util.ControleUsuario;
import br.com.fs.api.util.Toogle;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 *
 * @author tiago.teixeira
 */
public class JStatusBarCBI extends JPanel implements Runnable {

    private static final long serialVersionUID = 8346474837990227371L;
    private Map<String, JLabel> mapLabel;
    private JLabel[] labels;
    private JSeparator[] separadores;
    private Thread relogio;
    public JFrame frame;
    private boolean start = true;

    public JStatusBarCBI() {
        inicializeComponents();
    }

    public JLabel getLabel(String key) {
        return mapLabel.get(key);
    }

    public void setUsuarioAtual(String nomeUsuario) {
        mapLabel.get("usuarioAtual").setText(nomeUsuario);
    }

    public void setVersao(String versao) {
        mapLabel.get(versao).setText(versao);
    }

    public void setDataFalhaAutenticacao(String dataFalha) {
        mapLabel.get("F-AT").setText(dataFalha);
    }

    /**
     * Este método verifica se existe sobreposição ou salto temporal em locais
     * onde existe horário de verão DST(Daylight Saving Time)
     * 
     * YYYY-MM-DDThh:mm:ssTZD = 2018-03-19T11:58:08-03:00
     */
    private void verificacaoDST() {
        LocalDateTime ldt = LocalDateTime.now();
        OffsetDateTime odt = ldt.atOffset(ZoneOffset.UTC);
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        String d = zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx"));
    }

    private void inicializeComponents() {
        this.setPreferredSize(new Dimension(500, 28));
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        separadores = new JSeparator[7];
        labels = new JLabel[8];
        labels[0] = new JLabel("Usuário"); //usuário atual do S-RES
        labels[1] = new JLabel("0.0.1"); //versão do S-RES
        labels[2] = new JLabel("F-AT"); //data da útilma atenticação falha
        labels[3] = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))); //data atual
        labels[4] = new JLabel(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))); //horário
        labels[5] = new JLabel("INS"); //status da tecla insert
        labels[6] = new JLabel("CAPS"); //status da tecla caps-lock
        labels[7] = new JLabel("NUM"); //status da tecla num-lock

        for (int i = 0; i < 7; i++) {
            separadores[i] = new JSeparator(SwingConstants.VERTICAL);
            separadores[i].setPreferredSize(new Dimension(5, 18));
        }

        labels[0].setToolTipText("Usuário atual do sistema");
        labels[1].setHorizontalAlignment(SwingConstants.CENTER);
        labels[1].setToolTipText("Versão atual do sistema");
        labels[2].setHorizontalAlignment(SwingConstants.CENTER);
        labels[2].setForeground(Color.red);
        labels[2].setToolTipText("Data da última falha de autenticação do usuário");
        labels[3].setHorizontalAlignment(SwingConstants.CENTER);
        labels[3].setToolTipText("Data da última autenticação bem sucedida");
        labels[4].setHorizontalAlignment(SwingConstants.CENTER);
        labels[4].setToolTipText("Hora atual do sistema");
        labels[5].setHorizontalAlignment(SwingConstants.CENTER);
        labels[5].setToolTipText("Status da tecla Insert");
        labels[6].setHorizontalAlignment(SwingConstants.CENTER);
        labels[6].setToolTipText("Status da tecla Caps-Lock");
        labels[7].setHorizontalAlignment(SwingConstants.CENTER);
        labels[7].setToolTipText("Status da tecla Num-Lock");

        mapLabel = new HashMap();
        mapLabel.put("usuarioAtual", labels[0]);
        mapLabel.put("versaoAtual", labels[1]);
        mapLabel.put("f-at", labels[2]);
        mapLabel.put("dataAtual", labels[3]);
        mapLabel.put("horaAtual", labels[4]);
        mapLabel.put("INS", labels[5]);
        mapLabel.put("CAPS", labels[6]);
        mapLabel.put("NUM", labels[7]);

        labels[5].setEnabled(false);
        labels[6].setEnabled(false);
        labels[7].setEnabled(false);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labels[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(separadores[0], GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labels[1], GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separadores[1], GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labels[2], GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separadores[2], GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labels[3], GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separadores[3], GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labels[4], GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separadores[4], GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labels[5], GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separadores[5], GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labels[6], GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separadores[6], GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labels[7], GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()
                        ));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(labels[0])
                                        .addComponent(separadores[0])
                                        .addComponent(labels[1])
                                        .addComponent(separadores[1])
                                        .addComponent(labels[2])
                                        .addComponent(separadores[2])
                                        .addComponent(labels[3])
                                        .addComponent(separadores[3])
                                        .addComponent(labels[4])
                                        .addComponent(separadores[4])
                                        .addComponent(labels[5])
                                        .addComponent(separadores[5])
                                        .addComponent(labels[6])
                                        .addComponent(separadores[6])
                                        .addComponent(labels[7])
                                )
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );

    }

    public void startStatusBar(JFrame frame) {
        this.frame = frame;
        relogio = new Thread(this);
        relogio.start();
    }

    @Override
    public void run() {

        if (frame != null) {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    start = false;
                }
            });
        }

        labels[0].setText(ControleUsuario.getUsuarioAtual() != null ? ControleUsuario.getUsuarioAtual().getNomeUsuario() : "Não há usuário logado");
        labels[6].setEnabled(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
        labels[7].setEnabled(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK));

        Toolkit.getDefaultToolkit().addAWTEventListener((event) -> {
            if (event.paramString().contains("keyCode=155") && event.paramString().contains("KEY_PRESSED")) {
                Toogle.toogleEnable(labels[5]);
            } else if (event.paramString().contains("keyCode=20")) {
                labels[6].setEnabled(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
            } else if (event.paramString().contains("keyCode=144")) {
                labels[7].setEnabled(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK));
            }
        }, AWTEvent.KEY_EVENT_MASK);

        while (start) {
            try {
                Thread.sleep(1000);
                labels[4].setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            } catch (InterruptedException ex) {
                Logger.getLogger(JStatusBarCBI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
