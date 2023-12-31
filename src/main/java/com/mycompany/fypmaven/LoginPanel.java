package com.mycompany.fypmaven;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingWorker;

public class LoginPanel extends javax.swing.JFrame {

    public LoginPanel() {
        initComponents();

//        Clears the username textbox on focus and fills it back on focus loss
        TxtUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (TxtUsername.getText().equals("Enter your Username")) {
                    TxtUsername.setText("");
                }

                TxtUsername.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (TxtUsername.getText().isEmpty()) {
                    TxtUsername.setText("Enter your Username");
                    TxtUsername.setForeground(Color.GRAY);
                }
            }
        });

//         Clears the password textbox on focus and fills it back on focus loss
        TxtPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (TxtPassword.getText().equals("Enter your Password")) {
                    TxtPassword.setText("");
                }

                TxtUsername.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (TxtPassword.getText().isEmpty()) {
                    TxtPassword.setText("Enter your Password");
                    TxtPassword.setForeground(Color.GRAY);
                }
            }

        });

        // Add a mouse listener to the label
        LblForgetPassword.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Change the label's format on hover
                LblForgetPassword.setForeground(Color.RED);
                LblForgetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
                // You can also change other attributes like font, background color, etc.
            }

            public void mouseExited(MouseEvent e) {
                // Restore the label's format when the mouse exits
                LblForgetPassword.setForeground(Color.BLACK);
                LblForgetPassword.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                // You can revert any other changes made during hover here.
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

        BtnLogin = new javax.swing.JButton();
        BtnRegister = new javax.swing.JButton();
        TxtPassword = new javax.swing.JTextField();
        TxtUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        LblForgetPassword = new javax.swing.JLabel();
        loadingBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        BtnLogin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BtnLogin.setText("Login");
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });

        BtnRegister.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BtnRegister.setText("Register");
        BtnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRegisterMouseClicked(evt);
            }
        });

        TxtPassword.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TxtPassword.setForeground(new java.awt.Color(0, 0, 0));
        TxtPassword.setText("Enter your Password");

        TxtUsername.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TxtUsername.setForeground(new java.awt.Color(0, 0, 0));
        TxtUsername.setText("Enter your Username");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Password");

        LblForgetPassword.setText("Forgot your password?");
        LblForgetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblForgetPasswordMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(TxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addComponent(TxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(BtnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(BtnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loadingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(LblForgetPassword))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1))
                    .addComponent(TxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(TxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(loadingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblForgetPassword)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegisterMouseClicked

        RegisterPanel registerPanel = new RegisterPanel();
        registerPanel.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_BtnRegisterMouseClicked

    private void LblForgetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblForgetPasswordMouseClicked

        RecoverPassword recover = new RecoverPassword();
        recover.setVisible(true);
        
        this.dispose();

    }//GEN-LAST:event_LblForgetPasswordMouseClicked

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed

        loadingBar.setIndeterminate(true);
        loadingBar.setString("Loading...");

        //      Variable setup
                String Username = TxtUsername.getText();
                String Password = TxtPassword.getText();

                UserAccs useraccount = new UserAccs(Username, Password);
                
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                
                if (useraccount.Login() == true) {

                    try {

                        List<Account> matchedAccounts = GeneralFunctions.checkPasswords(useraccount.getUserID());

                        GeneralFunctions.showMatchingAccounts(matchedAccounts);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                   
                    Dashboard dashboard = new Dashboard(useraccount);
                    dashboard.setVisible(true);

                    LoginPanel.this.dispose();

                } else {
                    loadingBar.setIndeterminate(false);
                    loadingBar.setString("");

                    GeneralFunctions.showNotification("Invalid Username or Password", "Success");
                }
                return null;
            }

            @Override
            protected void done() {
                 
            }
        };
        worker.execute(); // Start the background task


    }//GEN-LAST:event_BtnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(LoginPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLogin;
    private javax.swing.JButton BtnRegister;
    private javax.swing.JLabel LblForgetPassword;
    private javax.swing.JTextField TxtPassword;
    private javax.swing.JTextField TxtUsername;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar loadingBar;
    // End of variables declaration//GEN-END:variables
}
