/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import resources.LocalizationUtil;

/**
 *
 * @author Yosef
 */
public class LogIn extends javax.swing.JFrame {

    /**
     * Creates new form LogIn
     */
    static User currentPlayer;
    public LogIn() {
        initComponents();
        userNameField.requestFocusInWindow();
        signUpBtn.setText(LocalizationUtil.localizedResourceBundle.getString("SignUpKey"));
        btnLogIn.setText(LocalizationUtil.localizedResourceBundle.getString("LogInKey"));
        userNamejl.setText(LocalizationUtil.localizedResourceBundle.getString("UserNameKey"));
        jLabel2.setText(LocalizationUtil.localizedResourceBundle.getString("PasswordKEY"));
        ChangeLangBtn.setText(LocalizationUtil.localizedResourceBundle.getString("ChangeLanguechKey"));
        this.setSize(416,360);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userNamejl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        signUpBtn = new javax.swing.JButton();
        btnLogIn = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        ChangeLangBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log In");
        setIconImages(null);
        getContentPane().setLayout(null);

        userNamejl.setText("User Name");
        getContentPane().add(userNamejl);
        userNamejl.setBounds(29, 103, 80, 14);

        jLabel2.setText("Password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(29, 162, 80, 14);

        userNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userNameFieldFocusGained(evt);
            }
        });
        userNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(userNameField);
        userNameField.setBounds(142, 100, 112, 20);

        signUpBtn.setText("SignUp");
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });
        getContentPane().add(signUpBtn);
        signUpBtn.setBounds(275, 50, 90, 23);

        btnLogIn.setText("LogIn");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogIn);
        btnLogIn.setBounds(150, 240, 100, 23);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(142, 159, 112, 20);

        ChangeLangBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/languch.jpg"))); // NOI18N
        ChangeLangBtn.setText("Change Languch");
        ChangeLangBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeLangBtnActionPerformed(evt);
            }
        });
        getContentPane().add(ChangeLangBtn);
        ChangeLangBtn.setBounds(290, 230, 70, 40);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Login.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 420, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        
        SignUp newUser=new SignUp();
        newUser.setVisible(true);
        
        

    }//GEN-LAST:event_signUpBtnActionPerformed

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        String userName=userNameField.getText();
        String password=jPasswordField1.getText();
        
        PreparedStatement statement;
        String sql="select UserName,Password,UserId from tblusers as a where a.`UserName`='"+userName+"'and a.`Password`='"+password+"'";
        try {
            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);
            PreparedStatement pstatment=connection.prepareStatement(sql);
            ResultSet resultSet = pstatment.executeQuery();
            
             resultSet = pstatment.executeQuery();
                
             while(resultSet.next()){
//                 System.out.println(resultSet.getInt("UserID"));
//                 System.out.println(resultSet.getString("UserName"));
//                 System.out.println(resultSet.getString("Password"));
                 //resultSet.next();
                 this.currentPlayer=new User(resultSet.getString("UserName"),resultSet.getString("Password"),resultSet.getInt("UserID"));
                 OpenScreen newGame=new OpenScreen(currentPlayer);
                 newGame.setVisible(true);
                 this.dispose();
                 return;
             }
           JOptionPane.showMessageDialog(this, "User name or password inncorrect please try agian");
             
                 
            
//                if(resultSet.next()){
//                  this.currentPlayer.setUserName(resultSet.getString("UserName"));
//                  this.currentPlayer.setPassword(resultSet.getString("Password"));
//                  JOptionPane.showMessageDialog(this, "login succsed");
//                   OpenScreen newGame=new OpenScreen(currentPlayer);
//                   newGame.setVisible(true);
//                }
//                else
//                     JOptionPane.showMessageDialog(this, "User name or password inncorrect please try agian");
//            
            
              
            resultSet.close();		// close resultSet
            pstatment.close();		// close statement and resultSet
            connection.close();		// close connection, statement and resultSet 	
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  
        
//        if(TestQuery.insertToDB(userName,password)){
//           JOptionPane.showConfirmDialog(this, "login succsed");
//           this.dispose();
//        }
//        else
//            JOptionPane.showConfirmDialog(this, "login faild");
        
        
    }//GEN-LAST:event_btnLogInActionPerformed

    private void userNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameFieldActionPerformed

    private void userNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameFieldFocusGained

    private void ChangeLangBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeLangBtnActionPerformed
// TODO add your handling code here:
        String []buttonsName={"English","Hebrew"};
             String selectedLanguage;
            int res=JOptionPane.showOptionDialog(this,
                    (LocalizationUtil.localizedResourceBundle.getString("ChangeLanguechKey")),
                    (LocalizationUtil.localizedResourceBundle.getString("TitleChangeLang")),
                    JOptionPane.WARNING_MESSAGE,0,null,buttonsName,buttonsName[0]);
            if(res==0)
             selectedLanguage="en";
            else if(res==1)
             selectedLanguage="iw";
            else
                return;
            LocalizationUtil.localizedResourceBundle = ResourceBundle.getBundle("resources.uimessages", new Locale(selectedLanguage));
            updateCaptions();
        
    }//GEN-LAST:event_ChangeLangBtnActionPerformed

    public void updateCaptions(){
        signUpBtn.setText(LocalizationUtil.localizedResourceBundle.getString("SignUpKey"));
        btnLogIn.setText(LocalizationUtil.localizedResourceBundle.getString("LogInKey"));
        userNamejl.setText(LocalizationUtil.localizedResourceBundle.getString("UserNameKey"));
        ChangeLangBtn.setText(LocalizationUtil.localizedResourceBundle.getString("ChangeLanguechKey"));
        jLabel2.setText(LocalizationUtil.localizedResourceBundle.getString("PasswordKEY"));
    }
    
    
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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChangeLangBtn;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton signUpBtn;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNamejl;
    // End of variables declaration//GEN-END:variables
}
