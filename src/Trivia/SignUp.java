/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
//import java.util.Date;
import javax.swing.JOptionPane;
import resources.LocalizationUtil;

/**
 *
 * @author Yosef
 */
public class SignUp extends javax.swing.JFrame {

    /**
     * Creates new form SignIn
     */
    int defultPoint=0;
    java.util.Date newDate=Calendar.getInstance().getTime();
     private Date date=new Date(newDate.getTime()); 
       
       
//      
      
    public SignUp() {
  
        initComponents();
        this.setSize(446,336);
        setLocationRelativeTo(null);
        userNameField.requestFocusInWindow();
        this.setTitle(LocalizationUtil.localizedResourceBundle.getString("signUpMainTitle"));
        signUpTitle.setText(LocalizationUtil.localizedResourceBundle.getString("signUpTitle"));
        userIDLab.setText(LocalizationUtil.localizedResourceBundle.getString("UserIdKey"));
        PasswordLab.setText(LocalizationUtil.localizedResourceBundle.getString("PasswordKEY"));
        userNameLab.setText(LocalizationUtil.localizedResourceBundle.getString("UserNameKey"));
        SignUpBtn.setText(LocalizationUtil.localizedResourceBundle.getString("SignUpKey"));
        Backbtn.setText((LocalizationUtil.localizedResourceBundle.getString("BackbKey")));
        changeLang.setText((LocalizationUtil.localizedResourceBundle.getString("ChangeLanguechKey")));
        JOptionPane.showMessageDialog(this,LocalizationUtil.localizedResourceBundle.getString("ValidInput"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userNameLab = new javax.swing.JLabel();
        PasswordLab = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        SignUpBtn = new javax.swing.JButton();
        signUpTitle = new javax.swing.JLabel();
        UserIDFild = new javax.swing.JTextField();
        userIDLab = new javax.swing.JLabel();
        Backbtn = new javax.swing.JButton();
        changeLang = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        userNameLab.setText("User Name");
        getContentPane().add(userNameLab);
        userNameLab.setBounds(120, 100, 70, 14);

        PasswordLab.setText("Password");
        getContentPane().add(PasswordLab);
        PasswordLab.setBounds(120, 140, 70, 17);
        getContentPane().add(userNameField);
        userNameField.setBounds(210, 100, 133, 20);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(210, 140, 133, 20);

        SignUpBtn.setText("SignUp");
        SignUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBtnActionPerformed(evt);
            }
        });
        getContentPane().add(SignUpBtn);
        SignUpBtn.setBounds(150, 260, 80, 23);

        signUpTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        signUpTitle.setForeground(new java.awt.Color(204, 255, 255));
        getContentPane().add(signUpTitle);
        signUpTitle.setBounds(160, 40, 230, 42);

        UserIDFild.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UserIDFildKeyPressed(evt);
            }
        });
        getContentPane().add(UserIDFild);
        UserIDFild.setBounds(210, 180, 133, 20);

        userIDLab.setText("UserID");
        getContentPane().add(userIDLab);
        userIDLab.setBounds(120, 180, 70, 14);

        Backbtn.setText("Back");
        Backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackbtnActionPerformed(evt);
            }
        });
        getContentPane().add(Backbtn);
        Backbtn.setBounds(260, 260, 70, 23);

        changeLang.setText("Change Languch");
        changeLang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeLangActionPerformed(evt);
            }
        });
        getContentPane().add(changeLang);
        changeLang.setBounds(10, 10, 130, 23);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/SignUp background.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 450, 330);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpBtnActionPerformed
          String userName=this.userNameField.getText();
            String password=this.jPasswordField1.getText();
            int realUserId=Integer.parseInt(this.UserIDFild.getText());
        try {
            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);
            PreparedStatement ps = connection.prepareStatement("insert into tblusers (userId, userName, password) values (?, ?, ?)");
            ps.setInt(1, realUserId);
            ps.setString(2, userName);
            ps.setString(3, password);
            
                int res=ps.executeUpdate();
               if(res>0){
                 JOptionPane.showMessageDialog(this, "User added succssefuly!\nPlease log in");
                  ps = connection.prepareStatement("insert into tblrecords (userId, Score, Date) values (?, ?, ?)");
                   ps.setInt(1, realUserId);
                   ps.setInt(2, defultPoint);
                   ps.setDate(3,date);
                   int res2=ps.executeUpdate();
                   if(res<0)
                       JOptionPane.showMessageDialog(this, "Ther was proble to add to record tbale");
                 LogIn logIn=new LogIn();
                 logIn.setVisible(true);
                 this.dispose();
                 
           }
           else{
                JOptionPane.showMessageDialog(this, "User alerdy exisit or invaild input");
           }
                ps.close();
            
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            JOptionPane.showMessageDialog(this, "User alerdy exisit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_SignUpBtnActionPerformed

    private void BackbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackbtnActionPerformed
        this.dispose();

    }//GEN-LAST:event_BackbtnActionPerformed

    private void changeLangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeLangActionPerformed
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
      // TODO add your handling code here:
    }//GEN-LAST:event_changeLangActionPerformed

    private void UserIDFildKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserIDFildKeyPressed
      if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           String userName=this.userNameField.getText();
            String password=this.jPasswordField1.getText();
            int realUserId=Integer.parseInt(this.UserIDFild.getText());
        try {
            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);
            PreparedStatement ps = connection.prepareStatement("insert into tblusers (userId, userName, password) values (?, ?, ?)");
            ps.setInt(1, realUserId);
            ps.setString(2, userName);
            ps.setString(3, password);
            
                int res=ps.executeUpdate();
               if(res>0){
                 JOptionPane.showMessageDialog(this, "User added succssefuly!\nPlease log in");
                  ps = connection.prepareStatement("insert into tblrecords (userId, Score, Date) values (?, ?, ?)");
                   ps.setInt(1, realUserId);
                   ps.setInt(2, defultPoint);
                   ps.setDate(3,date);
                   int res2=ps.executeUpdate();
                   if(res<0)
                       JOptionPane.showMessageDialog(this, "Ther was proble to add to record tbale");
                 LogIn logIn=new LogIn();
                 logIn.setVisible(true);
                 this.dispose();
                 
           }
           else{
                JOptionPane.showMessageDialog(this, "User alerdy exisit or invaild input");
           }
                ps.close();
            
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            JOptionPane.showMessageDialog(this, "User alerdy exisit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      }
      
        // TODO add your handling code here:
    }//GEN-LAST:event_UserIDFildKeyPressed

    public void updateCaptions(){
        this.setTitle(LocalizationUtil.localizedResourceBundle.getString("signUpMainTitle"));
        signUpTitle.setText(LocalizationUtil.localizedResourceBundle.getString("signUpTitle"));
        userIDLab.setText(LocalizationUtil.localizedResourceBundle.getString("UserIdKey"));
        PasswordLab.setText(LocalizationUtil.localizedResourceBundle.getString("PasswordKEY"));
        userNameLab.setText(LocalizationUtil.localizedResourceBundle.getString("UserNameKey"));
        SignUpBtn.setText(LocalizationUtil.localizedResourceBundle.getString("SignUpKey"));
        Backbtn.setText((LocalizationUtil.localizedResourceBundle.getString("BackbKey")));
        changeLang.setText((LocalizationUtil.localizedResourceBundle.getString("ChangeLanguechKey")));
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
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Backbtn;
    private javax.swing.JLabel PasswordLab;
    private javax.swing.JButton SignUpBtn;
    private javax.swing.JTextField UserIDFild;
    private javax.swing.JLabel background;
    private javax.swing.JButton changeLang;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel signUpTitle;
    private javax.swing.JLabel userIDLab;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNameLab;
    // End of variables declaration//GEN-END:variables
}
