/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.frames.filtros;

import br.com.fs.api.interfaces.ManipulaFrames;

/**
 *
 * @author tiago.teixeira
 */
public final class SeletorBarraProgresso extends ManipulaFrames {

    private static final long serialVersionUID = -3052303525428755086L;
    private Long valor_total = 0l;

    /**
     * Creates new form SeletorBarraProgresso
     */
    public SeletorBarraProgresso() {
        setImageIcon();
        initComponents();
    }

    public void setValorTotal(Long valor_total) {
        this.valor_total = valor_total;
        lbProgresso.setText(valor_total.toString().concat("/0"));
    }

    public void incrementValor(Long valor) {
        lbProgresso.setText(valor_total.toString().concat("/").concat(valor.toString()));
        bpProgresso.setValue(valor.intValue());
    }

    private Runnable test() {
        return () -> {
            try {
                setValorTotal(1000l);
                for (int i = 0; i < 1000; i++) {
                    incrementValor(Long.valueOf(String.valueOf(i)));
                    Thread.sleep(1000l);
                }
            } catch (Exception e) {
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbProgresso = new javax.swing.JLabel();
        bpProgresso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Gerando Relatório:");

        lbProgresso.setText("0");

        javax.swing.GroupLayout painelMainLayout = new javax.swing.GroupLayout(painelMain);
        painelMain.setLayout(painelMainLayout);
        painelMainLayout.setHorizontalGroup(
            painelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bpProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addGroup(painelMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelMainLayout.setVerticalGroup(
            painelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMainLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(painelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbProgresso))
                .addGap(2, 2, 2)
                .addComponent(bpProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
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
            java.util.logging.Logger.getLogger(SeletorBarraProgresso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeletorBarraProgresso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeletorBarraProgresso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeletorBarraProgresso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeletorBarraProgresso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bpProgresso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbProgresso;
    private javax.swing.JPanel painelMain;
    // End of variables declaration//GEN-END:variables
}
