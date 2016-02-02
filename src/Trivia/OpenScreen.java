/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yosef
 */
public class OpenScreen extends javax.swing.JFrame {
    static User current;
    static String userName;
    
    /**
     * Creates new form OpenScreen
     */
    public OpenScreen(User currentUser) {
        initComponents();
       this.setForeground(Color.red);
       int biuldComboBox=TestQuery.getInstance().countQues();
       for(int i=0;i<biuldComboBox;i++)
       this.cmbQuesNum.addItem(i+1); //set the real number of question from db
        setLocationRelativeTo(null);
        this.current=currentUser;
       OpenTitelWitName.setText("Welcom to trivia Game "+current.getUserName()); //לא הצלחתי לאתחל את שם השחקן הנוכחי
       currebtPlayerHighScore.setText("Your best score was: "+current.getHighstScore(current)+" Points");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStartGame = new javax.swing.JButton();
        title2 = new javax.swing.JLabel();
        cmbQuesNum = new javax.swing.JComboBox();
        btnShowHighScoreTble = new javax.swing.JButton();
        OpenTitelWitName = new javax.swing.JLabel();
        currebtPlayerHighScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(java.awt.Color.white);
        setIconImages(null);

        btnStartGame.setText("Ok");
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });

        title2.setFont(new java.awt.Font("Trajan Pro 3", 2, 14)); // NOI18N
        title2.setText("Please chose how much ques you want");

        cmbQuesNum.setModel(cmbQuesNum.getModel());
        cmbQuesNum.setToolTipText("");
        cmbQuesNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbQuesNumActionPerformed(evt);
            }
        });

        btnShowHighScoreTble.setText("Table of records");
        btnShowHighScoreTble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowHighScoreTbleActionPerformed(evt);
            }
        });

        OpenTitelWitName.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(213, 213, 213)
                                .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(btnShowHighScoreTble, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(237, 237, 237)
                                .addComponent(cmbQuesNum, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(currebtPlayerHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OpenTitelWitName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(OpenTitelWitName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(currebtPlayerHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbQuesNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnStartGame)
                .addGap(18, 18, 18)
                .addComponent(btnShowHighScoreTble, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        int numQues=1;  //choose the real number of wanted ques
       numQues+=cmbQuesNum.getSelectedIndex();
        System.out.println("selecetd numofques "+numQues);
        try {
            Game newGame=new Game(numQues,current,true); 
        } catch (Exception ex) {
            ex.printStackTrace(); //print all the hisory
        }
       this.dispose(); //close the window       

    }//GEN-LAST:event_btnStartGameActionPerformed

    private void cmbQuesNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbQuesNumActionPerformed

   
    }//GEN-LAST:event_cmbQuesNumActionPerformed

    private void btnShowHighScoreTbleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowHighScoreTbleActionPerformed
       
        HighstRecords highScore=new HighstRecords();
        highScore.setVisible(true);        
        
    }//GEN-LAST:event_btnShowHighScoreTbleActionPerformed

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
            java.util.logging.Logger.getLogger(OpenScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpenScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpenScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpenScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OpenScreen(current).setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel OpenTitelWitName;
    private javax.swing.JButton btnShowHighScoreTble;
    private javax.swing.JButton btnStartGame;
    public static javax.swing.JComboBox cmbQuesNum;
    private javax.swing.JLabel currebtPlayerHighScore;
    private javax.swing.JLabel title2;
    // End of variables declaration//GEN-END:variables
}
