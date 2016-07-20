
package Trivia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import resources.LocalizationUtil;

/**
 *
 * @author Yosef
 */
public class OpenScreen extends javax.swing.JFrame implements ActionListener {
    static User current;
    static String userName;
    private JMenuBar myMenuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu();
    private JMenu helpMenu = new JMenu();
    private JMenuItem changeLang = new JMenuItem();
    private JMenuItem newMenuItem = new JMenuItem();
    private JMenuItem exitMenuItem = new JMenuItem();
    private JMenuItem aboutMenuItem = new JMenuItem();
    
    /**
     * Creates new form OpenScreen
     * @param currentUser
     */
    public OpenScreen(User currentUser) {
        initComponents();
        buildMenu();
        addListeners();
        comboInit();
        this.setSize(661,375);
        this.current=currentUser;
       
       this.setTitle(LocalizationUtil.localizedResourceBundle.getString("WelcomGame"));
       jLabelLevel.setText(LocalizationUtil.localizedResourceBundle.getString("ChoseLevel"));
       LogOutBtn.setText(LocalizationUtil.localizedResourceBundle.getString("LogOutKey"));
       PleaseChoseCntQues.setText(LocalizationUtil.localizedResourceBundle.getString("PleaseChoseCntQues"));
       PleaseChoseCntQues.setHorizontalAlignment(SwingConstants.CENTER);
       btnStartGame.setText(LocalizationUtil.localizedResourceBundle.getString("StartGameKey"));
       btnShowHighScoreTble.setText(LocalizationUtil.localizedResourceBundle.getString("HighScoreTble"));
      
       if(Integer.parseInt(current.getHighstScore(current))> 40){
           ShowCrownIcon();
       }
       else if(Integer.parseInt(current.getHighstScore(current))> 30){
            ShowDragonIcon();
       }
       else if(Integer.parseInt(current.getHighstScore(current))> 20){
            ShowKnightIcon();
       }
       setBackGround();
       setLocationRelativeTo(null);
      OpenTitelWitName.setText(LocalizationUtil.localizedResourceBundle.getString("WelcomGame")+" "+current.getUserName());
      OpenTitelWitName.setHorizontalAlignment(SwingConstants.CENTER);
      currebtPlayerHighScore.setText((LocalizationUtil.localizedResourceBundle.getString("BestScore"))+" "+current.getHighstScore(current));
      currebtPlayerHighScore.setHorizontalAlignment(SwingConstants.CENTER);
      //JOptionPane.showMessageDialog(this,LocalizationUtil.localizedResourceBundle.getString("ChoseWnatedLevel"));
    }
    
    /**
     *
     */
    public void buildMenu() {
       /*build the menu acording the curent languche*/
        fileMenu = new JMenu(LocalizationUtil.localizedResourceBundle.getString("FileKey"));
        helpMenu = new JMenu(LocalizationUtil.localizedResourceBundle.getString("HelpKey"));
        changeLang = new JMenuItem(LocalizationUtil.localizedResourceBundle.getString("ChangeLanguechKey"));
        newMenuItem = new JMenuItem(LocalizationUtil.localizedResourceBundle.getString("NewGameKey"));
        exitMenuItem = new JMenuItem(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));
        aboutMenuItem = new JMenuItem(LocalizationUtil.localizedResourceBundle.getString("AboutKey"));
        fileMenu.add(newMenuItem);
        fileMenu.add(exitMenuItem);
        myMenuBar.add(fileMenu);
        helpMenu.add(aboutMenuItem);
        helpMenu.add(changeLang);
        myMenuBar.add(helpMenu);

        this.setJMenuBar(myMenuBar);
    }
    
    /**
     *
     */
    public void addListeners() {
       /*add action listener to all button */
       
        newMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);
        changeLang.addActionListener(this);
        exitMenuItem.addActionListener(this);

    } 
    
    @Override
   /*action whan any button was pressed*/
   public void actionPerformed(ActionEvent ae){
       if (ae.getSource() == changeLang) { //if change languche button pressed
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
        } else if (ae.getSource() == exitMenuItem) { //if exit button pressed
           showExitDialog();
        }else if (ae.getSource() == newMenuItem) {  //if new game button pressed
            newGameDialog();
        } else if (ae.getSource() == aboutMenuItem) {   //if about button pressed
            aboutDialog();
        }
       
   }
   
    /**
     *
     */
    public void updateCaptions() { //method that refresh all the label+button languche
       
        this.setTitle((LocalizationUtil.localizedResourceBundle.getString("GameTitle")));
        fileMenu.setText(LocalizationUtil.localizedResourceBundle.getString("FileKey"));
        newMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("NewGameKey"));
        exitMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));
        aboutMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("AboutKey"));
        helpMenu.setText(LocalizationUtil.localizedResourceBundle.getString("HelpKey"));
        changeLang.setText(LocalizationUtil.localizedResourceBundle.getString("languageKey"));
        jLabelLevel.setText(LocalizationUtil.localizedResourceBundle.getString("ChoseLevel"));
        LogOutBtn.setText(LocalizationUtil.localizedResourceBundle.getString("LogOutKey"));
        PleaseChoseCntQues.setText(LocalizationUtil.localizedResourceBundle.getString("PleaseChoseCntQues"));
        PleaseChoseCntQues.setHorizontalAlignment(SwingConstants.CENTER);
        btnStartGame.setText(LocalizationUtil.localizedResourceBundle.getString("StartGameKey"));
        btnShowHighScoreTble.setText(LocalizationUtil.localizedResourceBundle.getString("HighScoreTble"));
        OpenTitelWitName.setText(LocalizationUtil.localizedResourceBundle.getString("WelcomGame")+" "+current.getUserName());
        OpenTitelWitName.setHorizontalAlignment(SwingConstants.CENTER);
        currebtPlayerHighScore.setText((LocalizationUtil.localizedResourceBundle.getString("BestScore"))+" "+current.getHighstScore(current));
        currebtPlayerHighScore.setHorizontalAlignment(SwingConstants.CENTER);
        comboInit();
    }
  
    /**
     *
     */
    public void showExitDialog() {
       /*"you shure exit?" promp acorrding curent languche*/
        int result = JOptionPane.showConfirmDialog(this, // parent component
                (LocalizationUtil.localizedResourceBundle.getString("areYouSureKey")), // message
                (LocalizationUtil.localizedResourceBundle.getString("titlrExitDialog")), // title of the dialog box
                JOptionPane.YES_NO_OPTION,// indicates buttons ot display
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    /**
     *
     */
    public void newGameDialog() {
       /*"you shure new game?" promp acorrding curent languche*/
        int result = JOptionPane.showConfirmDialog(this, (LocalizationUtil.localizedResourceBundle.getString("AreYouSureYouStartNewGame")),
                (LocalizationUtil.localizedResourceBundle.getString("NewGameDialog")),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            OpenScreen newGame = new OpenScreen(current);
            newGame.setVisible(true);
        }
    }
   
    /**
     *
     */
    public void aboutDialog() {
       /*about promp acorrding curent languche*/
        JOptionPane.showMessageDialog(this, (LocalizationUtil.localizedResourceBundle.getString("AllReservd")));
    }
   
    /**
     *
     */
    public void comboInit(){
        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { (LocalizationUtil.localizedResourceBundle.getString("Rand")), "1", "2", "3" }));
        cmbLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLevelActionPerformed(evt);
            }
        });
        getContentPane().add(cmbLevel);
        cmbLevel.setBounds(520, 280, 90, 20);
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
        PleaseChoseCntQues = new javax.swing.JLabel();
        cmbCountOfQUes = new javax.swing.JComboBox();
        btnShowHighScoreTble = new javax.swing.JButton();
        OpenTitelWitName = new javax.swing.JLabel();
        currebtPlayerHighScore = new javax.swing.JLabel();
        LogOutBtn = new javax.swing.JButton();
        cmbLevel = new javax.swing.JComboBox();
        CrownImg = new javax.swing.JLabel();
        jLabelLevel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        BackgroudTrivia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome To Trivia Game");
        setForeground(java.awt.Color.white);
        setIconImages(null);
        getContentPane().setLayout(null);

        btnStartGame.setText("Ok");
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });
        getContentPane().add(btnStartGame);
        btnStartGame.setBounds(250, 220, 150, 30);

        PleaseChoseCntQues.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PleaseChoseCntQues.setText("Please chose how much ques you want");
        getContentPane().add(PleaseChoseCntQues);
        PleaseChoseCntQues.setBounds(120, 110, 430, 40);

        cmbCountOfQUes.setModel(cmbCountOfQUes.getModel());
        cmbCountOfQUes.setToolTipText("");
        cmbCountOfQUes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCountOfQUesActionPerformed(evt);
            }
        });
        getContentPane().add(cmbCountOfQUes);
        cmbCountOfQUes.setBounds(300, 170, 45, 20);

        btnShowHighScoreTble.setText("Table of records");
        btnShowHighScoreTble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowHighScoreTbleActionPerformed(evt);
            }
        });
        getContentPane().add(btnShowHighScoreTble);
        btnShowHighScoreTble.setBounds(260, 270, 130, 30);

        OpenTitelWitName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(OpenTitelWitName);
        OpenTitelWitName.setBounds(140, 20, 360, 40);

        currebtPlayerHighScore.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        currebtPlayerHighScore.setForeground(new java.awt.Color(51, 51, 255));
        getContentPane().add(currebtPlayerHighScore);
        currebtPlayerHighScore.setBounds(130, 70, 360, 30);

        LogOutBtn.setText("Log Out");
        LogOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutBtnActionPerformed(evt);
            }
        });
        getContentPane().add(LogOutBtn);
        LogOutBtn.setBounds(531, 10, 100, 30);

        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        cmbLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLevelActionPerformed(evt);
            }
        });
        getContentPane().add(cmbLevel);
        cmbLevel.setBounds(530, 280, 90, 20);
        getContentPane().add(CrownImg);
        CrownImg.setBounds(20, 10, 0, 90);

        jLabelLevel.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        getContentPane().add(jLabelLevel);
        jLabelLevel.setBounds(520, 230, 110, 30);

        jButton1.setText("1 vs 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(120, 223, 80, 30);
        getContentPane().add(BackgroudTrivia);
        BackgroudTrivia.setBounds(0, 0, 650, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     */
    public void setBackGround(){
       BackgroudTrivia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/OpenScreenIcon.jpg"))); 
       getContentPane().add(BackgroudTrivia);
       BackgroudTrivia.setBounds(0, 0, 650, 320);
 }

    /**
     *
     */
    public void  ShowCrownIcon(){

        CrownImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Crown.png")));
        getContentPane().add(CrownImg);
        CrownImg.setBounds(20, 10, 90, 90);       
      }

    /**
     *
     */
    public void  ShowDragonIcon(){

        CrownImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Dragon.png")));
        getContentPane().add(CrownImg);
        CrownImg.setBounds(20, 10, 90, 90);       
      }

    /**
     *
     */
    public void  ShowKnightIcon(){

        CrownImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Knight.png")));
        getContentPane().add(CrownImg);
        CrownImg.setBounds(20, 10, 90, 90);       
      }
    
    private void btnShowHighScoreTbleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowHighScoreTbleActionPerformed
       
        HighstRecords highScore=new HighstRecords();
        
        highScore.setVisible(true);        
        
    }//GEN-LAST:event_btnShowHighScoreTbleActionPerformed

    private void LogOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutBtnActionPerformed
        this.dispose();
        LogIn loGin=new LogIn();
        loGin.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_LogOutBtnActionPerformed

    private void cmbCountOfQUesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCountOfQUesActionPerformed

    }//GEN-LAST:event_cmbCountOfQUesActionPerformed

    private void cmbLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLevelActionPerformed
        cmbCountOfQUes.removeAllItems();
        int biuldComboBox;
                                                                        
         if(cmbLevel.getSelectedItem().equals(LocalizationUtil.localizedResourceBundle.getString("Rand")))
         biuldComboBox=DataBaseMange.getInstance().countQues();
         else
         biuldComboBox=DataBaseMange.getInstance().countQues(Integer.parseInt(cmbLevel.getSelectedItem().toString()));
       
        
        for(int i=0;i<biuldComboBox;i++)
         this.cmbCountOfQUes.addItem(i+1);//for loop to set the real number of question from db

    }//GEN-LAST:event_cmbLevelActionPerformed

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        if(cmbCountOfQUes.getSelectedItem()==null){
            JOptionPane.showMessageDialog(this,LocalizationUtil.localizedResourceBundle.getString("LeveDidntChoose"));
            return;
        }
        int numQues=1;  //choose the real number of wanted ques
        int level=0;
        numQues+=cmbCountOfQUes.getSelectedIndex();
        current.setLevel(0);
        current.setPoints(0);
        current.setWrongAnsCnt(0);
        if(cmbLevel.getSelectedItem()=="Rand")
        level=0;
        else
        level=cmbLevel.getSelectedIndex();
        try {
            Game newGame=new Game(numQues,current,true,level);
        } catch (Exception ex) {
            ex.printStackTrace(); //print all the hisory
        }
        this.dispose(); //close the window
    }//GEN-LAST:event_btnStartGameActionPerformed
    private void StartGameActionPerformed() {                                             
       
        int numQues=10;  //set conts 10 qyestion
        int level=0;
        current.setLevel(0);
        current.setPoints(0);
        current.setWrongAnsCnt(0);
        try {
            Game newGame=new Game(numQues,current,true,level);
        } catch (Exception ex) {
            ex.printStackTrace(); //print all the hisory
        }
        this.dispose(); //close the window
    }    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  
         try (Socket clientSocket = new Socket ("localhost", 2222)) {
			
			DataInputStream input = new DataInputStream(clientSocket.getInputStream()); 
			PrintStream output = new PrintStream (clientSocket.getOutputStream());
			Scanner userInput = new Scanner(System.in);	
			output.println();
	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
         
         //if(Server.class.twoPlayerConnected())
           // StartGameActionPerformed();

    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JLabel BackgroudTrivia;
    private javax.swing.JLabel CrownImg;
    private javax.swing.JButton LogOutBtn;
    public javax.swing.JLabel OpenTitelWitName;
    private javax.swing.JLabel PleaseChoseCntQues;
    private javax.swing.JButton btnShowHighScoreTble;
    private javax.swing.JButton btnStartGame;
    public static javax.swing.JComboBox cmbCountOfQUes;
    private javax.swing.JComboBox cmbLevel;
    private javax.swing.JLabel currebtPlayerHighScore;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelLevel;
    // End of variables declaration//GEN-END:variables
}
