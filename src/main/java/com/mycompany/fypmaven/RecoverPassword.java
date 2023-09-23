package com.mycompany.fypmaven;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

/**
 *
 * @author Work
 */
public class RecoverPassword extends javax.swing.JFrame {

    /**
     * Creates new form RecoverPassword
     */
    public RecoverPassword() {
        initComponents();

        TxtRecover.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {

                TxtRecover.setText("");

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        TxtRecover = new javax.swing.JTextField();
        BtnCancel = new javax.swing.JButton();
        BtnSbmt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Ebrima", 1, 16)); // NOI18N
        jLabel4.setText("Enter the account recovery phrase below:");

        TxtRecover.setText("Enter the recovery phrase here");

        BtnCancel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BtnCancel.setText("Cancel");
        BtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });

        BtnSbmt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BtnSbmt.setText("Submit");
        BtnSbmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSbmtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 145, Short.MAX_VALUE))
                    .addComponent(TxtRecover)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnSbmt, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(TxtRecover, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSbmt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelActionPerformed

        this.dispose();

        LoginPanel login = new LoginPanel();
        login.setVisible(true);

    }//GEN-LAST:event_BtnCancelActionPerformed

    private void BtnSbmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSbmtActionPerformed

//        Variable Setup
        String RecoveryPhrase = TxtRecover.getText().trim();
        String query = "Select * from user_accounts where Recovery_Phrase = ?";
        Object[] param = {RecoveryPhrase};
        
         try {

                DB_Connection connection = new DB_Connection();
                connection.getConnection();

                String recovery = connection.executeSelect(query,param,"Recovery_Phrase");
                
                if(recovery.equals(RecoveryPhrase)){
                    
                    String password = connection.executeSelect(query,param,"Password");
                    String username = connection.executeSelect(query,param,"Name");
                    
                    UserAccs user = new UserAccs(username,password);
                    
                    ResetPassword resetPassword = new ResetPassword(user);
                    resetPassword.setVisible(true);
                    
                    this.dispose();
                    
                }else{
                     GeneralFunctions.showNotification("The recovery phrase entered is incorrect. Please reenter it", "Failed");
                }

            } catch (SQLException ex) {
               
            }
        

    }//GEN-LAST:event_BtnSbmtActionPerformed

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
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecoverPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancel;
    private javax.swing.JButton BtnSbmt;
    private javax.swing.JButton BtnSubmit;
    private javax.swing.JButton BtnSubmit1;
    private javax.swing.JButton BtnSubmit2;
    private javax.swing.JTextField TxtRecover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
