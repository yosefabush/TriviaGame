
package Trivia;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import resources.LocalizationUtil;

/**
 *
 * @author Yosef
 */
public class HighstRecords extends javax.swing.JFrame {

    /**
     * Creates new form HighstRecords
     */
    private int[] highScore5 = new int[5];
    int i=0;

    /**
     *
     */
    public HighstRecords() {
        
        initComponents();
        updateTableScore();
        getAllTop5Score();
        updatePictureLevel();
        setBackGround();
        this.setSize(520, 500);
        this.setTitle(LocalizationUtil.localizedResourceBundle.getString("TitleTop5Key"));
        TitleTop5.setText(LocalizationUtil.localizedResourceBundle.getString("TitleTop5Key"));
        TitleTop5.setHorizontalAlignment(SwingConstants.CENTER);
        btnBackToMainScreen.setText(LocalizationUtil.localizedResourceBundle.getString("BackKey"));
        setLocationRelativeTo(null);
    }
    private void updateTableScore(){
       PreparedStatement statement;
        String sql="select UserName,Score,date from tblusers as a,tblrecords as b where a.`UserId`=b.`UserID` order by Score desc  LIMIT 5";
        try {
            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);
            PreparedStatement pstatment=connection.prepareStatement(sql);
            ResultSet resultSet = pstatment.executeQuery();
            
            jTableGridViewHighScore.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            resultSet.close();		// close resultSet
            pstatment.close();		// close statement and resultSet
            connection.close();		// close connection, statement and resultSet 	
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  
        
    }

    /**
     *
     */
    public void setBackGround(){
       Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/woodBackground.png"))); 
       getContentPane().add(Background);
       Background.setBounds(0, 0, 520, 500);
 }
    
     private void getAllTop5Score(){
         try {

//            Class.forName(DbUtilitis.dbDriver); //load the rigt server
//            Connection connection
//                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
//                            DbUtilitis.jdbcUser,
//                            DbUtilitis.jdbcPassword);
            
            Statement statement = Connect_db.getConnection().createStatement();

            String allCustomersQuery = "select UserName,Score,date from tblusers as a,tblrecords as b where a.`UserId`=b.`UserID` order by Score desc  LIMIT 5";
            ResultSet resultSet = statement.executeQuery(allCustomersQuery);
            
            while (resultSet.next()){
                        this.highScore5[i]=Integer.parseInt(resultSet.getString("Score"));
                           i++;
                       }
            resultSet.close();		// close resultSet
            statement.close();		// close statement and resultSet
            //Connect_db.getConnection().close();
                 // close connection, statement and resultSet 	
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
           
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

        TitleTop5 = new javax.swing.JLabel();
        btnBackToMainScreen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGridViewHighScore = new javax.swing.JTable();
        picLev5 = new javax.swing.JLabel();
        picLev2 = new javax.swing.JLabel();
        picLev3 = new javax.swing.JLabel();
        picLev4 = new javax.swing.JLabel();
        picLev1 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Summry");
        getContentPane().setLayout(null);

        TitleTop5.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        TitleTop5.setText("     Top 5 Score :");
        getContentPane().add(TitleTop5);
        TitleTop5.setBounds(113, 25, 255, 29);

        btnBackToMainScreen.setText("Back");
        btnBackToMainScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMainScreenActionPerformed(evt);
            }
        });
        getContentPane().add(btnBackToMainScreen);
        btnBackToMainScreen.setBounds(210, 420, 94, 35);

        jTableGridViewHighScore.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "User Name", "Score", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableGridViewHighScore.setPreferredSize(new java.awt.Dimension(225, 275));
        jTableGridViewHighScore.setRowHeight(55);
        jScrollPane1.setViewportView(jTableGridViewHighScore);
        if (jTableGridViewHighScore.getColumnModel().getColumnCount() > 0) {
            jTableGridViewHighScore.getColumnModel().getColumn(0).setHeaderValue("User Name");
            jTableGridViewHighScore.getColumnModel().getColumn(1).setHeaderValue("Score");
            jTableGridViewHighScore.getColumnModel().getColumn(2).setHeaderValue("Date");
        }
        jTableGridViewHighScore.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(102, 72, 310, 310);
        getContentPane().add(picLev5);
        picLev5.setBounds(50, 320, 50, 50);
        getContentPane().add(picLev2);
        picLev2.setBounds(50, 150, 50, 50);
        getContentPane().add(picLev3);
        picLev3.setBounds(50, 200, 50, 50);
        getContentPane().add(picLev4);
        picLev4.setBounds(50, 260, 50, 50);
        getContentPane().add(picLev1);
        picLev1.setBounds(50, 100, 50, 50);
        getContentPane().add(Background);
        Background.setBounds(0, 0, 520, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackToMainScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMainScreenActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackToMainScreenActionPerformed

    /**
     *
     * @param a
     */
    public void setCrown(JLabel a){
        
        a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/SmallCrown.png"))); 
        getContentPane().add(a);
        
    } 

    /**
     *
     * @param a
     */
    public void setDragon (JLabel a){
        
        a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/SmallDragon.png"))); 
        getContentPane().add(a);
        
    } 

    /**
     *
     * @param a
     */
    public void setKnight (JLabel a){
        
        a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/SmallKnight.png"))); 
        getContentPane().add(a);
        
    } 
    
    /**
     *
     */
    public void updatePictureLevel(){
         
        if(highScore5[0]>40)
            setCrown(picLev1);
        else if(highScore5[0]>30)
            setDragon(picLev1);
        else if(highScore5[0]>20)
            setKnight(picLev1);
        picLev1.setBounds(50, 100, 50, 50);
        
      if(highScore5[1]>40)
            setCrown(picLev2);
        else if(highScore5[1]>30)
            setDragon(picLev2);
        else if(highScore5[1]>20)
            setKnight(picLev2);
        picLev2.setBounds(50, 150, 50, 50);
        
      if(highScore5[2]>40)
            setCrown(picLev3);
        else if(highScore5[2]>30)
            setDragon(picLev3);
        else if(highScore5[2]>20)
            setKnight(picLev3);
        picLev3.setBounds(50, 200, 50, 50);

        if(highScore5[3]>40)
            setCrown(picLev4);
        else if(highScore5[3]>30)
            setDragon(picLev4);
        else if(highScore5[3]>20)
            setKnight(picLev4);
        picLev4.setBounds(50, 260, 50, 50);
        
         if(highScore5[4]>40)
            setCrown(picLev5);
        else if(highScore5[4]>30)
            setDragon(picLev5);
        else if(highScore5[4]>20)
            setKnight(picLev5);
        picLev5.setBounds(50, 320, 50, 50);
       
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
            java.util.logging.Logger.getLogger(HighstRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HighstRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HighstRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HighstRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HighstRecords().setVisible(true);
            }
        });
    }

    /**
     *
     * @param myTable
     * @param sql
     */
    public static void FillTable(JTable myTable,String sql){
    
        DefaultTableModel dtm = new DefaultTableModel();
        //Window gui = new Window();
         
}
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel TitleTop5;
    private javax.swing.JButton btnBackToMainScreen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGridViewHighScore;
    private javax.swing.JLabel picLev1;
    private javax.swing.JLabel picLev2;
    private javax.swing.JLabel picLev3;
    private javax.swing.JLabel picLev4;
    private javax.swing.JLabel picLev5;
    // End of variables declaration//GEN-END:variables
}
