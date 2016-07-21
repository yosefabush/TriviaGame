/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import resources.LocalizationUtil;

/**
 *
 * @author lidor
 */
public class SelectGame1 extends javax.swing.JFrame {

    PlaySounds bacgroundSound;
    static User current;
    DataInputStream input = null;
    Socket clientSocket = null;

    /**
     * Creates new form SelectGame1
     */
    public SelectGame1(User curentPlayer) {
        initComponents();
        this.current = curentPlayer;
        setLocationRelativeTo(null);
        title.setText(current.getUserName());
        //bacgroundSound = new PlaySounds("C:\\Users\\Yosef\\Documents\\NetBeansProjects\\Java\\TriviaGame1\\src\\Trivia\\Sounds\\ChillingMusic.wav");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogOutBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(650, 320));
        getContentPane().setLayout(null);

        LogOutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/logout.png"))); // NOI18N
        LogOutBtn.setContentAreaFilled(false);
        LogOutBtn.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        LogOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutBtnActionPerformed(evt);
            }
        });
        getContentPane().add(LogOutBtn);
        LogOutBtn.setBounds(570, 240, 70, 70);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/1vs1n.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(270, 110, 200, 70);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Singleplayer_Button.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(250, 180, 240, 60);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Select game");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(300, 50, 150, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/headgears1.gif"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-10, 0, 370, 400);
        getContentPane().add(title);
        title.setBounds(210, 10, 260, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/OpenScreenIcon22.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 650, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutBtnActionPerformed
        this.dispose();
        LogIn loGin = new LogIn();
        loGin.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_LogOutBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Thread t = new Thread() {
            public void run() {
                try {
                    clientSocket = new Socket("localhost", 2222);
                    input = new DataInputStream(clientSocket.getInputStream());
                    PrintStream output = new PrintStream(clientSocket.getOutputStream());
                    //Scanner userInput = new Scanner(System.in);	
                    //output.println();

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        if (input.readLine().equals("Statrt playe")) {
                            Thread t2 = new Thread(){
                                      public void run() {
                            try {
                                Game game = new Game(2, current, true, 1);
                               
                            } catch (Exception ex) {
                                Logger.getLogger(SelectGame1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                            };
                            t2.start();
                             break;
                    }//else
                       // JOptionPane.showMessageDialog(SelectGame1.this, "Watting for another player");

                    } catch (IOException ex) {
                        Logger.getLogger(SelectGame1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        OpenScreen newGame = new OpenScreen(current);
        newGame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(SelectGame1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectGame1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectGame1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectGame1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectGame1(current).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogOutBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
