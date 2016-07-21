
package Trivia;


import java.awt.event.KeyEvent;
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
    boolean pres=false;
    static User currentPlayer;

    /**
     *
     */
    public LogIn() {
        initComponents();
        userNameField.requestFocusInWindow();
        this.setTitle(LocalizationUtil.localizedResourceBundle.getString("GameTitle"));
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
        TriviaLogo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log In");
        setIconImages(null);
        getContentPane().setLayout(null);

        userNamejl.setBackground(new java.awt.Color(0, 0, 0));
        userNamejl.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        userNamejl.setForeground(new java.awt.Color(255, 255, 255));
        userNamejl.setText("User Name");
        getContentPane().add(userNamejl);
        userNamejl.setBounds(60, 100, 80, 16);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 140, 80, 16);

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
        userNameField.setBounds(140, 100, 130, 20);

        signUpBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        signUpBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/signup-now.png"))); // NOI18N
        signUpBtn.setContentAreaFilled(false);
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });
        getContentPane().add(signUpBtn);
        signUpBtn.setBounds(110, 250, 180, 50);

        btnLogIn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Login22.png"))); // NOI18N
        btnLogIn.setContentAreaFilled(false);
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });
        btnLogIn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnLogInFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnLogInFocusLost(evt);
            }
        });
        getContentPane().add(btnLogIn);
        btnLogIn.setBounds(120, 190, 170, 70);

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(140, 140, 130, 20);

        ChangeLangBtn.setBackground(new java.awt.Color(102, 153, 255));
        ChangeLangBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/languch1.jpg"))); // NOI18N
        ChangeLangBtn.setContentAreaFilled(false);
        ChangeLangBtn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        ChangeLangBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeLangBtnActionPerformed(evt);
            }
        });
        getContentPane().add(ChangeLangBtn);
        ChangeLangBtn.setBounds(-10, -10, 50, 50);

        TriviaLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/logo1.jpg"))); // NOI18N
        TriviaLogo.setLabelFor(TriviaLogo);
        getContentPane().add(TriviaLogo);
        TriviaLogo.setBounds(110, 10, 180, 70);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/GameBackGround.jpg"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 420, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        this.dispose();
        SignUp newUser=new SignUp();
        newUser.setVisible(true);
        
        

    }//GEN-LAST:event_signUpBtnActionPerformed

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        String userName=userNameField.getText();
        String password=jPasswordField1.getText();
        
        PreparedStatement statement;
        String sql="select UserName,Password,UserId from tblusers as a where a.`UserName`='"+userName+"'and a.`Password`='"+password+"'";
        try {
//            Class.forName(DbUtilitis.dbDriver); //load the rigt server
//            Connection connection
//                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
//                            DbUtilitis.jdbcUser,
//                            DbUtilitis.jdbcPassword);
            PreparedStatement pstatment=Connect_db.getConnection().prepareStatement(sql);
            ResultSet resultSet = pstatment.executeQuery();
            
             resultSet = pstatment.executeQuery();
                
             while(resultSet.next()){
                 this.currentPlayer=new User(resultSet.getString("UserName"),resultSet.getString("Password"),resultSet.getInt("UserID"));
                 this.dispose();
                 SelectGame1 selectGame=new SelectGame1(currentPlayer);
                 selectGame.setVisible(true);
                 this.dispose();
                 return;
             }
           JOptionPane.showMessageDialog(this, "User name or password inncorrect please try agian");
              
            resultSet.close();		// close resultSet
            pstatment.close();		// close statement and resultSet
            Connect_db.getConnection().close();		// close connection, statement and resultSet 	
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        }
        
    }//GEN-LAST:event_btnLogInActionPerformed

    private void userNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameFieldActionPerformed

    private void userNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameFieldFocusGained

    private void ChangeLangBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeLangBtnActionPerformed
// TODO add your handling code here:

        String selectedLanguage="iw";
        if(!pres){
           pres=true;
            selectedLanguage="iw";
        }
        else if(pres){
            pres=false;
          selectedLanguage="en";
        }

            LocalizationUtil.localizedResourceBundle = ResourceBundle.getBundle("resources.uimessages", new Locale(selectedLanguage));
            updateCaptions();
        
    }//GEN-LAST:event_ChangeLangBtnActionPerformed

    private void btnLogInFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnLogInFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogInFocusGained

    private void btnLogInFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnLogInFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogInFocusLost

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String userName=userNameField.getText();
            String password=jPasswordField1.getText();
        
        PreparedStatement statement;
        String sql="select UserName,Password,UserId from tblusers as a where a.`UserName`='"+userName+"'and a.`Password`='"+password+"'";
        try {
//            Class.forName(DbUtilitis.dbDriver); //load the rigt server
//            Connection connection
//                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
//                            DbUtilitis.jdbcUser,
//                            DbUtilitis.jdbcPassword);
            PreparedStatement pstatment=Connect_db.getConnection().prepareStatement(sql);
            ResultSet resultSet = pstatment.executeQuery();
             resultSet = pstatment.executeQuery();
             while(resultSet.next()){
                 this.currentPlayer=new User(resultSet.getString("UserName"),resultSet.getString("Password"),resultSet.getInt("UserID"));
                 this.dispose();
                 SelectGame1 selectGame=new SelectGame1(currentPlayer);
                 selectGame.setVisible(true);
                 this.dispose();
                 return;
             }
           JOptionPane.showMessageDialog(this, "User name or password inncorrect please try agian");
            resultSet.close();		// close resultSet
            pstatment.close();		// close statement and resultSet
            //Connect_db.getConnection().close();		// close connection, statement and resultSet 	
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        }  
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1KeyPressed

    /**
     *
     */
    public void updateCaptions(){
        
        this.setTitle(LocalizationUtil.localizedResourceBundle.getString("GameTitle"));
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
    private javax.swing.JLabel TriviaLogo;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton signUpBtn;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNamejl;
    // End of variables declaration//GEN-END:variables
}
