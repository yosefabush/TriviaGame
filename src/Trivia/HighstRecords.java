/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public HighstRecords() {
        
        initComponents();
        updateTableScore();
        TitleTop5.setText(LocalizationUtil.localizedResourceBundle.getString("TitleTop5Key"));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleTop5.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        TitleTop5.setText("     Top 5 Score :");

        btnBackToMainScreen.setText("Back");
        btnBackToMainScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMainScreenActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(jTableGridViewHighScore);
        if (jTableGridViewHighScore.getColumnModel().getColumnCount() > 0) {
            jTableGridViewHighScore.getColumnModel().getColumn(0).setHeaderValue("User Name");
            jTableGridViewHighScore.getColumnModel().getColumn(1).setHeaderValue("Score");
            jTableGridViewHighScore.getColumnModel().getColumn(2).setHeaderValue("Date");
        }
        jTableGridViewHighScore.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBackToMainScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TitleTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(TitleTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(btnBackToMainScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackToMainScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMainScreenActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackToMainScreenActionPerformed

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
    public static void FillTable(JTable myTable,String sql){
    
        DefaultTableModel dtm = new DefaultTableModel();
        //Window gui = new Window();
         
}
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleTop5;
    private javax.swing.JButton btnBackToMainScreen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGridViewHighScore;
    // End of variables declaration//GEN-END:variables
}
