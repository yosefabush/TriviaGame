/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import static Trivia.FormClass.cntLevel;
import javax.swing.SwingConstants;
import resources.LocalizationUtil;
/**
 *
 * @author Yosef
 */
public class TotalSummry extends javax.swing.JFrame {

    /**
     * Creates new form TotalSummry
     */
    static User currentPlayer;
    public TotalSummry(User current) {
        initComponents();
        this.currentPlayer=current;
        updateLang();
        this.setSize(530,360);
        setBackGround();
        this.setLocationRelativeTo(null);
        this.setTitle(LocalizationUtil.localizedResourceBundle.getString("Summry"));
        this.NewHighScore.setText(LocalizationUtil.localizedResourceBundle.getString("YouCanBetrr"));
        NewHighScore.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    public TotalSummry (User current,int newHighScore){ //if user get highr score the he was in DB  
         initComponents();
         this.currentPlayer=current;
         updateLang();
         this.NewHighScore.setText(LocalizationUtil.localizedResourceBundle.getString("NewHigScor"));
         NewHighScore.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
        public void setBackGround(){
       background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/gameOver.jpg"))); 
       getContentPane().add(background);
       background.setBounds(0, 0, 530,360);
 }
    public void updateLang() {
        
        this.setTitle(LocalizationUtil.localizedResourceBundle.getString("Summry"));
        NewGameBtn.setText(LocalizationUtil.localizedResourceBundle.getString("NewGameKey"));
        ExitBtn.setText(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));     
        GameOverTitle.setText(LocalizationUtil.localizedResourceBundle.getString("GameOver"));
        GameOverTitle.setHorizontalAlignment(SwingConstants.CENTER);
        wrongCnt.setText(LocalizationUtil.localizedResourceBundle.getString("wrongCntMesg"));
        CorrectCnt.setText(LocalizationUtil.localizedResourceBundle.getString("CorrectCntMesg"));
        TotalScoreJl.setText(LocalizationUtil.localizedResourceBundle.getString("TotalScoreMseg"));
        timesJl.setText(LocalizationUtil.localizedResourceBundle.getString("timesKey"));
        timesJl2.setText(LocalizationUtil.localizedResourceBundle.getString("timesKey"));
        jlPoint.setText(LocalizationUtil.localizedResourceBundle.getString("PointsKey"));
        setLocationRelativeTo(null);
         this.scoreFiled.setText(Integer.toString(currentPlayer.getPoints()));
         this.WrongFiled1.setText(Integer.toString(currentPlayer.getWrongAnsCnt()));
         this.correctCnt.setText(Integer.toString(currentPlayer.getCorrectAnsCnt()));        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scoreFiled = new javax.swing.JLabel();
        ExitBtn = new javax.swing.JButton();
        NewGameBtn = new javax.swing.JButton();
        GameOverTitle = new javax.swing.JLabel();
        TotalScoreJl = new javax.swing.JLabel();
        wrongCnt = new javax.swing.JLabel();
        WrongFiled1 = new javax.swing.JLabel();
        timesJl = new javax.swing.JLabel();
        jlPoint = new javax.swing.JLabel();
        NewHighScore = new javax.swing.JLabel();
        CorrectCnt = new javax.swing.JLabel();
        correctCnt = new javax.swing.JLabel();
        timesJl2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        scoreFiled.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(scoreFiled);
        scoreFiled.setBounds(270, 220, 76, 24);

        ExitBtn.setForeground(new java.awt.Color(51, 51, 51));
        ExitBtn.setText("Exit");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtn(evt);
            }
        });
        getContentPane().add(ExitBtn);
        ExitBtn.setBounds(300, 270, 90, 23);

        NewGameBtn.setForeground(new java.awt.Color(51, 51, 51));
        NewGameBtn.setText("New Game");
        NewGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameBtn(evt);
            }
        });
        getContentPane().add(NewGameBtn);
        NewGameBtn.setBounds(130, 270, 100, 23);

        GameOverTitle.setBackground(new java.awt.Color(153, 255, 153));
        GameOverTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        GameOverTitle.setForeground(new java.awt.Color(255, 255, 255));
        GameOverTitle.setText("     Game Over!");
        GameOverTitle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(GameOverTitle);
        GameOverTitle.setBounds(180, 50, 161, 50);

        TotalScoreJl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TotalScoreJl.setForeground(new java.awt.Color(255, 255, 255));
        TotalScoreJl.setText("Your Total Score is :");
        getContentPane().add(TotalScoreJl);
        TotalScoreJl.setBounds(80, 220, 190, 22);

        wrongCnt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        wrongCnt.setForeground(new java.awt.Color(255, 255, 255));
        wrongCnt.setText("You anser wrong  :");
        getContentPane().add(wrongCnt);
        wrongCnt.setBounds(90, 150, 170, 22);

        WrongFiled1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(WrongFiled1);
        WrongFiled1.setBounds(270, 150, 76, 22);

        timesJl.setForeground(new java.awt.Color(255, 255, 255));
        timesJl.setText("Times");
        getContentPane().add(timesJl);
        timesJl.setBounds(370, 160, 40, 14);

        jlPoint.setForeground(new java.awt.Color(255, 255, 255));
        jlPoint.setText("Points");
        getContentPane().add(jlPoint);
        jlPoint.setBounds(370, 220, 40, 14);

        NewHighScore.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        NewHighScore.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(NewHighScore);
        NewHighScore.setBounds(140, 100, 234, 24);

        CorrectCnt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CorrectCnt.setForeground(new java.awt.Color(255, 255, 255));
        CorrectCnt.setText("You answer correct:");
        getContentPane().add(CorrectCnt);
        CorrectCnt.setBounds(80, 180, 180, 22);

        correctCnt.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(correctCnt);
        correctCnt.setBounds(270, 190, 76, 24);

        timesJl2.setForeground(new java.awt.Color(255, 255, 255));
        timesJl2.setText("Times");
        getContentPane().add(timesJl2);
        timesJl2.setBounds(370, 190, 40, 14);
        getContentPane().add(background);
        background.setBounds(0, 0, 530, 360);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newGameBtn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameBtn
        
        currentPlayer.setPoints(0);
        currentPlayer.setZeroWrongAnsCnt(0);
        currentPlayer.setZeroLevel(0);
        currentPlayer.setZeroCorrectAnsCnt(0);
        cntLevel=0;
        OpenScreen newGame =new OpenScreen(currentPlayer);
        newGame.setVisible(true);
        this.dispose();
      

    }//GEN-LAST:event_newGameBtn

    private void exitBtn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtn

         System.exit(0);
    }//GEN-LAST:event_exitBtn

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
            java.util.logging.Logger.getLogger(TotalSummry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TotalSummry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TotalSummry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TotalSummry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TotalSummry(currentPlayer).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CorrectCnt;
    private javax.swing.JButton ExitBtn;
    private javax.swing.JLabel GameOverTitle;
    private javax.swing.JButton NewGameBtn;
    private javax.swing.JLabel NewHighScore;
    private javax.swing.JLabel TotalScoreJl;
    private javax.swing.JLabel WrongFiled1;
    private javax.swing.JLabel background;
    private javax.swing.JLabel correctCnt;
    private javax.swing.JLabel jlPoint;
    private javax.swing.JLabel scoreFiled;
    private javax.swing.JLabel timesJl;
    private javax.swing.JLabel timesJl2;
    private javax.swing.JLabel wrongCnt;
    // End of variables declaration//GEN-END:variables
}
