package com.mycompany.fypmaven;

import java.sql.SQLException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ResetPassword extends javax.swing.JFrame {

    String password;
    String confirmPassword;
    UserAccs user;
    
    public ResetPassword() {
        initComponents();
    }

    public ResetPassword(UserAccs user) {
        initComponents();

        this.user = user;
        this.password = TxtPassword.getText().trim();
        this.confirmPassword = TxtConfirmPassword.getText().trim();

        LblWrongPass.setVisible(false);
        LblPassDetails.setVisible(false);
        LblPassDetailsPt2.setVisible(false);
        
//  DocumentListener for the confirm password field
        TxtConfirmPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPasswords();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPasswords();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPasswords();
            }

            private void checkPasswords() {
                String password = TxtPassword.getText();
                String confirmPassword = TxtConfirmPassword.getText();

                if (!password.equals(confirmPassword)) {

                    LblWrongPass.setVisible(true);

                }else{
                    
                    LblWrongPass.setVisible(false);
                }
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

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        TxtConfirmPassword = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtPassword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BtnReset = new javax.swing.JButton();
        BtnCancel = new javax.swing.JButton();
        LblPassDetailsPt2 = new javax.swing.JLabel();
        LblPassDetails = new javax.swing.JLabel();
        LblWrongPass = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(TxtConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 360, 30));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Confirm Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));
        getContentPane().add(TxtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 360, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Password");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        BtnReset.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BtnReset.setText("Reset");
        BtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetActionPerformed(evt);
            }
        });
        getContentPane().add(BtnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 112, 40));

        BtnCancel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BtnCancel.setText("Cancel");
        BtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(BtnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 112, 40));

        LblPassDetailsPt2.setForeground(new java.awt.Color(255, 0, 51));
        LblPassDetailsPt2.setText("1 lower case letter, 1 number, and 1 special character ");
        LblPassDetailsPt2.setFocusable(false);
        getContentPane().add(LblPassDetailsPt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 350, 16));

        LblPassDetails.setForeground(new java.awt.Color(255, 0, 51));
        LblPassDetails.setText("Please ensure that password has at least 1 upper case letter,");
        LblPassDetails.setFocusable(false);
        getContentPane().add(LblPassDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 350, 16));

        LblWrongPass.setForeground(new java.awt.Color(255, 51, 51));
        LblWrongPass.setText("The passwords entered are not the same");
        LblWrongPass.setFocusable(false);
        getContentPane().add(LblWrongPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 280, -1));
        getContentPane().add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 102, 18, 9));
        getContentPane().add(filler3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
//      Setting of variables
        this.password = TxtPassword.getText().trim();
        this.confirmPassword = TxtConfirmPassword.getText().trim();
        
//       Setting of variable 
        if (password.equals(confirmPassword) && GeneralFunctions.isValidPassword(password)) {

            String updateQuery = "UPDATE user_accounts SET Password = ? WHERE Name = ?";
            Object[] param = {password, user.getUsername()};

            try {

                DB_Connection connection = new DB_Connection();

                connection.getConnection();

                connection.executeQuery(updateQuery, param);

                GeneralFunctions.showNotification("The Password has been successfully reset.", "Success");

            } catch (SQLException ex) {

                ex.printStackTrace();
            }
            
            LoginPanel login = new LoginPanel();
            login.setVisible(true);
            this.dispose();
            
        } else {

            LblPassDetails.setVisible(true);
            LblPassDetailsPt2.setVisible(true);
        }

    }//GEN-LAST:event_BtnResetActionPerformed

    private void BtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelActionPerformed

        LoginPanel login = new LoginPanel();
        login.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_BtnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResetPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancel;
    private javax.swing.JButton BtnReset;
    private javax.swing.JLabel LblPassDetails;
    private javax.swing.JLabel LblPassDetailsPt2;
    private javax.swing.JLabel LblWrongPass;
    private javax.swing.JTextField TxtConfirmPassword;
    private javax.swing.JTextField TxtPassword;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
