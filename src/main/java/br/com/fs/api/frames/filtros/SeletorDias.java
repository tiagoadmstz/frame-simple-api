/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.frames.filtros;

import br.com.fs.api.util.Datas;
import br.com.fs.api.interfaces.ManipulaFrames;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author tiago.teixeira
 */
public final class SeletorDias extends ManipulaFrames {

    private static final long serialVersionUID = -7548959667022634857L;

    /**
     * Creates new form SeletorDias
     */
    public SeletorDias() {
        setImageIcon();
        initComponents();
        comportamento();
    }

    public int getDiaInicial() {
        return Integer.parseInt(txtDiaInicial.getText());
    }

    public int getDiaFinal() {
        return Integer.parseInt(txtDiaFinal.getText());
    }

    public JButton getBtConfirmar() {
        return btConfirmar;
    }

    private void comportamento() {
        addListerners(txtDiaInicial);
        addListerners(txtDiaFinal);
    }

    private void addListerners(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    field.setText(validarDia(field.getText()));
                    trocaMaior();
                    btConfirmar.doClick();
                }
            }
        });

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                field.setText(validarDia(field.getText()));
                trocaMaior();
            }
        });
    }

    private String validarDia(String data) {
        return Datas.verificarDia(data);
    }

    private void trocaMaior() {
        try {
            if (!txtDiaInicial.getText().equals("") && !txtDiaFinal.getText().equals("")) {
                int di = Integer.parseInt(txtDiaInicial.getText());
                int df = Integer.parseInt(txtDiaFinal.getText());
                if (di > df) {
                    txtDiaFinal.setText(String.valueOf(di));
                    txtDiaInicial.setText(String.valueOf(df));
                }
            }
        } catch (Exception ex) {
            txtDiaInicial.setText("01");
            txtDiaFinal.setText(MonthDay.now().format(DateTimeFormatter.ofPattern("dd")));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbDataInicial = new javax.swing.JLabel();
        lbDataFinal = new javax.swing.JLabel();
        txtDiaInicial = new br.com.fs.api.beans.JTextFieldCBI();
        txtDiaFinal = new br.com.fs.api.beans.JTextFieldCBI();
        btConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seletor de Dias");
        setResizable(false);

        lbDataInicial.setText("Dia Inicial:");

        lbDataFinal.setText("Dia Final:");

        txtDiaInicial.setMaxLength(10);
        txtDiaInicial.setName("data"); // NOI18N

        txtDiaFinal.setMaxLength(10);
        txtDiaFinal.setName("data"); // NOI18N

        btConfirmar.setText("Confirmar");
        btConfirmar.setActionCommand("confirmar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDataInicial)
                            .addComponent(lbDataFinal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btConfirmar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataInicial)
                    .addComponent(txtDiaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataFinal)
                    .addComponent(txtDiaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btConfirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SeletorDias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeletorDias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeletorDias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeletorDias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeletorDias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConfirmar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbDataFinal;
    private javax.swing.JLabel lbDataInicial;
    private br.com.fs.api.beans.JTextFieldCBI txtDiaFinal;
    private br.com.fs.api.beans.JTextFieldCBI txtDiaInicial;
    // End of variables declaration//GEN-END:variables
}
