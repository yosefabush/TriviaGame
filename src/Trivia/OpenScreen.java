package Trivia;

import static Trivia.LogIn.currentPlayer;
import static Trivia.SelectGame.current;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    int biuldComboBox = 0;
    static boolean playerChoseLevel = false;
    PlaySounds bacgroundSound;
    MusicClass Mp3ClassPlayer = new MusicClass();
    public static int count = 1;
    int xMouse;
    int yMouse;
    static boolean firstTime = false;
    static boolean soundPlaying = false;

    int width = (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 185;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height - 180;

    /**
     * Creates new form OpenScreen
     *
     * @param currentUser
     */
    public OpenScreen(User currentUser) {
        initComponents();
        this.addWindowListener(new OpenScreen.MyWindowListener());

        //  cmbLevel.setLocation(300, 200);
        buildMenu();

        addListeners();
        comboInit();
        this.setSize(661, 375);
        this.current = currentUser;

        this.setTitle(LocalizationUtil.localizedResourceBundle.getString("WelcomGame"));
        jLabelLevel.setText(LocalizationUtil.localizedResourceBundle.getString("ChoseLevel"));
        //  LogOutBtn.setText(LocalizationUtil.localizedResourceBundle.getString("LogOutKey"));
        PleaseChoseCntQues.setText(LocalizationUtil.localizedResourceBundle.getString("PleaseChoseCntQues"));
        PleaseChoseCntQues.setHorizontalAlignment(SwingConstants.CENTER);
        //btnStartGame.setText(LocalizationUtil.localizedResourceBundle.getString("StartGameKey"));
        btnShowHighScoreTble.setText(LocalizationUtil.localizedResourceBundle.getString("HighScoreTble"));
        if (!playerChoseLevel) {
            PleaseChoseCntQues.hide();
            cmbCountOfQUes.hide();
            playerChoseLevel = true;
        }
        if (Integer.parseInt(current.getHighstScore(current)) > 40) {
            ShowCrownIcon();
        } else if (Integer.parseInt(current.getHighstScore(current)) > 30) {
            ShowDragonIcon();
        } else if (Integer.parseInt(current.getHighstScore(current)) > 20) {
            ShowKnightIcon();
        }
        setBackGround();
        setLocationRelativeTo(null);
        OpenTitelWitName.setText(LocalizationUtil.localizedResourceBundle.getString("WelcomGame") + " " + current.getUserName());
        OpenTitelWitName.setHorizontalAlignment(SwingConstants.CENTER);
        currebtPlayerHighScore.setText((LocalizationUtil.localizedResourceBundle.getString("BestScore")) + " " + current.getHighstScore(current));
        currebtPlayerHighScore.setHorizontalAlignment(SwingConstants.CENTER);
        //JOptionPane.showMessageDialog(this,LocalizationUtil.localizedResourceBundle.getString("ChoseWnatedLevel"));
        if (!firstTime) {
            loadDefultSound();
            firstTime = true;
            soundPlaying = true;
        }
        biuldComboBox = DataBaseMange.getInstance().countQues();
        for (int i = 0; i < biuldComboBox; i++) {
            this.cmbCountOfQUes.addItem(i + 1);
        }
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
    public void loadDefultSound() {
        Mp3ClassPlayer.Stop();
        Mp3ClassPlayer.Play("C:\\Users\\Yosef\\Documents\\NetBeansProjects\\Java\\TriviaGame1\\src\\Trivia\\Sounds\\ChillingMusic.mp3" + "");
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
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == changeLang) { //if change languche button pressed
            String[] buttonsName = {"English", "Hebrew"};
            String selectedLanguage;
            int res = JOptionPane.showOptionDialog(this,
                    (LocalizationUtil.localizedResourceBundle.getString("ChangeLanguechKey")),
                    (LocalizationUtil.localizedResourceBundle.getString("TitleChangeLang")),
                    JOptionPane.WARNING_MESSAGE, 0, null, buttonsName, buttonsName[0]);
            if (res == 0) {
                selectedLanguage = "en";
            } else if (res == 1) {
                selectedLanguage = "iw";
            } else {
                return;
            }
            LocalizationUtil.localizedResourceBundle = ResourceBundle.getBundle("resources.uimessages", new Locale(selectedLanguage));
            updateCaptions();
        } else if (ae.getSource() == exitMenuItem) { //if exit button pressed
            showExitDialog();
        } else if (ae.getSource() == newMenuItem) {  //if new game button pressed
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
        // LogOutBtn.setText(LocalizationUtil.localizedResourceBundle.getString("LogOutKey"));
        PleaseChoseCntQues.setText(LocalizationUtil.localizedResourceBundle.getString("PleaseChoseCntQues"));
        PleaseChoseCntQues.setHorizontalAlignment(SwingConstants.CENTER);
        // btnStartGame.setText(LocalizationUtil.localizedResourceBundle.getString("StartGameKey"));
        btnShowHighScoreTble.setText(LocalizationUtil.localizedResourceBundle.getString("HighScoreTble"));
        OpenTitelWitName.setText(LocalizationUtil.localizedResourceBundle.getString("WelcomGame") + " " + current.getUserName());
        OpenTitelWitName.setHorizontalAlignment(SwingConstants.CENTER);
        currebtPlayerHighScore.setText((LocalizationUtil.localizedResourceBundle.getString("BestScore")) + " " + current.getHighstScore(current));
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
            try {
                PreparedStatement ps = Connect_db.getConnection().prepareStatement("UPDATE tblusers SET LogInStatus = ? WHERE UserId = ?");
                ps.setInt(1, 0);
                ps.setInt(2, current.getUserID());
                int res = ps.executeUpdate();
                if (res > 0) {
                 System.exit(0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormClass.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            this.dispose();
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
    public void comboInit() {
        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[]{(LocalizationUtil.localizedResourceBundle.getString("Rand")), "1", "2", "3"}));
        cmbLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLevelActionPerformed(evt);
            }
        });
        getContentPane().add(cmbLevel);
        cmbLevel.setBounds(270, 70, 90, 20);
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
        loopPlay = new javax.swing.JLabel();
        stop = new javax.swing.JLabel();
        play = new javax.swing.JLabel();
        pause = new javax.swing.JLabel();
        chooseFile = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        BackgroudTrivia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome To Trivia Game");
        setForeground(java.awt.Color.white);
        setIconImages(null);
        getContentPane().setLayout(null);

        btnStartGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/start_now_button.png"))); // NOI18N
        btnStartGame.setBorder(null);
        btnStartGame.setContentAreaFilled(false);
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });
        getContentPane().add(btnStartGame);
        btnStartGame.setBounds(240, 190, 170, 60);

        PleaseChoseCntQues.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PleaseChoseCntQues.setText("Please chose how much ques you want");
        getContentPane().add(PleaseChoseCntQues);
        PleaseChoseCntQues.setBounds(140, 90, 390, 40);

        cmbCountOfQUes.setModel(cmbCountOfQUes.getModel());
        cmbCountOfQUes.setToolTipText("");
        cmbCountOfQUes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCountOfQUesActionPerformed(evt);
            }
        });
        getContentPane().add(cmbCountOfQUes);
        cmbCountOfQUes.setBounds(300, 160, 45, 20);

        btnShowHighScoreTble.setText("Table of records");
        btnShowHighScoreTble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowHighScoreTbleActionPerformed(evt);
            }
        });
        getContentPane().add(btnShowHighScoreTble);
        btnShowHighScoreTble.setBounds(260, 270, 140, 30);

        OpenTitelWitName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(OpenTitelWitName);
        OpenTitelWitName.setBounds(0, 170, 200, 80);

        currebtPlayerHighScore.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        currebtPlayerHighScore.setForeground(new java.awt.Color(0, 204, 204));
        getContentPane().add(currebtPlayerHighScore);
        currebtPlayerHighScore.setBounds(0, 250, 220, 30);

        LogOutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/logout.png"))); // NOI18N
        LogOutBtn.setBorder(null);
        LogOutBtn.setContentAreaFilled(false);
        LogOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutBtnActionPerformed(evt);
            }
        });
        getContentPane().add(LogOutBtn);
        LogOutBtn.setBounds(570, 250, 80, 70);

        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        cmbLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLevelActionPerformed(evt);
            }
        });
        getContentPane().add(cmbLevel);
        cmbLevel.setBounds(280, 130, 90, 20);
        getContentPane().add(CrownImg);
        CrownImg.setBounds(20, 10, 0, 90);

        jLabelLevel.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        getContentPane().add(jLabelLevel);
        jLabelLevel.setBounds(260, 40, 110, 30);

        loopPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loopPlayMouseReleased(evt);
            }
        });
        getContentPane().add(loopPlay);
        loopPlay.setBounds(470, 10, 20, 60);

        stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                stopMouseReleased(evt);
            }
        });
        getContentPane().add(stop);
        stop.setBounds(500, 10, 30, 60);

        play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playMouseReleased(evt);
            }
        });
        getContentPane().add(play);
        play.setBounds(540, 10, 30, 60);

        pause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pauseMouseReleased(evt);
            }
        });
        getContentPane().add(pause);
        pause.setBounds(580, 10, 30, 50);

        chooseFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chooseFileMouseReleased(evt);
            }
        });
        getContentPane().add(chooseFile);
        chooseFile.setBounds(620, 10, 20, 50);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/BackgroundPlayer.png"))); // NOI18N
        background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backgroundMousePressed(evt);
            }
        });
        background.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                backgroundMouseDragged(evt);
            }
        });
        getContentPane().add(background);
        background.setBounds(470, 0, 190, 60);

        BackgroudTrivia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/OpenScreenIcon11.jpg"))); // NOI18N
        getContentPane().add(BackgroudTrivia);
        BackgroudTrivia.setBounds(0, 0, 650, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     */
    public void setBackGround() {
        BackgroudTrivia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/OpenScreenIcon11.jpg")));
        getContentPane().add(BackgroudTrivia);
        BackgroudTrivia.setBounds(0, 0, 650, 320);
    }

    /**
     *
     */
    public void ShowCrownIcon() {

        CrownImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Crown.png")));
        getContentPane().add(CrownImg);
        CrownImg.setBounds(20, 10, 90, 90);
    }

    /**
     *
     */
    public void ShowDragonIcon() {

        CrownImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Dragon.png")));
        getContentPane().add(CrownImg);
        CrownImg.setBounds(20, 10, 90, 90);
    }

    /**
     *
     */
    public void ShowKnightIcon() {

        CrownImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trivia/Images/Knight.png")));
        getContentPane().add(CrownImg);
        CrownImg.setBounds(20, 10, 90, 90);
    }

    private void btnShowHighScoreTbleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowHighScoreTbleActionPerformed

        HighstRecords highScore = new HighstRecords();

        highScore.setVisible(true);

    }//GEN-LAST:event_btnShowHighScoreTbleActionPerformed

    private void LogOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutBtnActionPerformed
        try {
            Mp3ClassPlayer.Stop();
            PreparedStatement ps = Connect_db.getConnection().prepareStatement("UPDATE tblusers SET LogInStatus = ? WHERE UserId = ?");
            ps.setInt(1, 0);
            ps.setInt(2, current.getUserID());
            int res = ps.executeUpdate();
            if (res > 0) {
                this.dispose();
                SelectGame selectGame = new SelectGame(currentPlayer);
                selectGame.setVisible(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpenScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_LogOutBtnActionPerformed

    private void cmbCountOfQUesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCountOfQUesActionPerformed

    }//GEN-LAST:event_cmbCountOfQUesActionPerformed

    private void cmbLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLevelActionPerformed
        cmbCountOfQUes.removeAllItems();
        PleaseChoseCntQues.show();
        cmbCountOfQUes.show();
        int biuldComboBox;

        if (cmbLevel.getSelectedItem().equals(LocalizationUtil.localizedResourceBundle.getString("Rand"))) {
            biuldComboBox = DataBaseMange.getInstance().countQues();
        } else {
            biuldComboBox = DataBaseMange.getInstance().countQues(Integer.parseInt(cmbLevel.getSelectedItem().toString()));
        }

        for (int i = 0; i < biuldComboBox; i++) {
            this.cmbCountOfQUes.addItem(i + 1);//for loop to set the real number of question from db
        }
    }//GEN-LAST:event_cmbLevelActionPerformed

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        if (cmbCountOfQUes.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("LeveDidntChoose"));
            return;
        }
        int numQues = 1;  //choose the real number of wanted ques
        int level = 0;
        numQues += cmbCountOfQUes.getSelectedIndex();
        current.setLevel(0);
        current.setPoints(0);
        current.setWrongAnsCnt(0);
        if (cmbLevel.getSelectedItem() == "Rand") {
            level = 0;
        } else {
            level = cmbLevel.getSelectedIndex();
        }
        try {
            Game newGame = new Game(numQues, current, true, level);
        } catch (Exception ex) {
            ex.printStackTrace(); //print all the hisory
        }
        this.dispose(); //close the window
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void backgroundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_backgroundMousePressed

    private void backgroundMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_backgroundMouseDragged

    private void loopPlayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loopPlayMouseReleased
        switch (count) {
            case 0:
                count = 1;
                break;

            case 1:
                count = 0;
                break;

        }
    }//GEN-LAST:event_loopPlayMouseReleased

    private void stopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopMouseReleased
        soundPlaying = false;
    }//GEN-LAST:event_stopMouseReleased

    private void playMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playMouseReleased
        if (!soundPlaying) {
            Mp3ClassPlayer.Resume();
            soundPlaying = true;
        }

    }//GEN-LAST:event_playMouseReleased

    private void pauseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pauseMouseReleased
        try {
            // TODO add your handling code here:
            Mp3ClassPlayer.Pause();
            soundPlaying = false;
        } catch (IOException ex) {
            Logger.getLogger(OpenScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pauseMouseReleased

    private void chooseFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseFileMouseReleased
        FileFilter filter = new FileNameExtensionFilter("MP3 Files", "mp3", "mpeg3");

        JFileChooser chooser = new JFileChooser("C:\\Music");
        chooser.addChoosableFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Mp3ClassPlayer.Stop();
            File myFile = chooser.getSelectedFile();
            String song = myFile + "";

            Mp3ClassPlayer.Play(song);

        }
    }//GEN-LAST:event_chooseFileMouseReleased
    private void StartGameActionPerformed() {

        int numQues = 10;  //set conts 10 qyestion
        int level = 0;
        current.setLevel(0);
        current.setPoints(0);
        current.setWrongAnsCnt(0);
        try {
            Game newGame = new Game(numQues, current, true, level);
        } catch (Exception ex) {
            ex.printStackTrace(); //print all the hisory
        }
        this.dispose(); //close the window
    }

    class MyWindowListener extends WindowAdapter {

        /*add are "you shure exit?" promp */
        @Override
        public void windowClosing(WindowEvent we
        ) {
            showExitDialog();
        }
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
    private javax.swing.JLabel background;
    private javax.swing.JButton btnShowHighScoreTble;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JLabel chooseFile;
    public static javax.swing.JComboBox cmbCountOfQUes;
    private javax.swing.JComboBox cmbLevel;
    private javax.swing.JLabel currebtPlayerHighScore;
    private javax.swing.JLabel jLabelLevel;
    private javax.swing.JLabel loopPlay;
    private javax.swing.JLabel pause;
    private javax.swing.JLabel play;
    private javax.swing.JLabel stop;
    // End of variables declaration//GEN-END:variables
}
