package Trivia;

import static Trivia.PlaySounds.correctSound;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import resources.LocalizationUtil;

public class FormClass extends JFrame implements ActionListener {

    private JButton okButton;
    private JButton exitButton;
    private JLabel liveResult;
    private JLabel showQues = new JLabel();
    private JLabel backgroundImage=new JLabel();
    private JTextField ansField = new JTextField();
    private JMenuBar myMenuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu();
    private JMenu helpMenu = new JMenu();
    private JMenuItem changeLang = new JMenuItem();
    private JMenuItem newMenuItem = new JMenuItem();
    private JMenuItem exitMenuItem = new JMenuItem();
    private JMenuItem aboutMenuItem = new JMenuItem();
    private JRadioButton option1Button = new JRadioButton();
    private JRadioButton option2Button = new JRadioButton();
    private JRadioButton option3Button = new JRadioButton();
    private JRadioButton option4Button = new JRadioButton();
    private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel optionsPanel = new JPanel(new GridLayout(5,1));
    private JPanel okExitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel liveResultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel fullScreenPanel = new JPanel(new BorderLayout());
    private ButtonGroup answersGroup = new ButtonGroup();
    private Question question ;
    private int rightAns;
    private static User current;
    static int remainedQuestios;
    static int point = 0;
    static int currentLevel;
    

    public FormClass(Question question, int remainedQuestios, User currentPlayer,int chosenLevel) {
        this.setTitle((LocalizationUtil.localizedResourceBundle.getString("GameTitle")));
        this.currentLevel=chosenLevel;
        this.current = currentPlayer;
        this.question = question;
        this.remainedQuestios = remainedQuestios;//keep remined question
        this.rightAns = question.getRightAns();
        this.setSize(700,500);
        
        setBackruond();
        initComponents();
        buildMenu();
        addListeners();
      
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //preess on exit will not close the window but wee override method thate will show  relvane promp
        this.setVisible(true);
        setLocationRelativeTo(null); //sett the postin of the screen in the center

    }
    
   public void setBackruond(){
        
        backgroundImage.setIcon(new ImageIcon(getClass().getResource("/Trivia/Images/GameBackGround.jpg"))); 
        add(backgroundImage);
        backgroundImage.setLayout(new BorderLayout());
    
    }
   
   public void initComponents() {
        okButton = new JButton(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        exitButton = new JButton(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));
        okButton.setSize(12,12);
        okExitPanel.add(okButton, BorderLayout.CENTER);
        okExitPanel.add(exitButton, BorderLayout.CENTER);
        fullScreenPanel.add(okExitPanel, BorderLayout.SOUTH);
        
        if(currentLevel!=0){
        liveResult = new JLabel(" " + (LocalizationUtil.localizedResourceBundle.getString("PointsKey"))
                + ": " + point
                + " " + (LocalizationUtil.localizedResourceBundle.getString("YourLevelKey"))
                + ": " + currentLevel);
        }
        else{
             liveResult = new JLabel(" " + (LocalizationUtil.localizedResourceBundle.getString("PointsKey"))
                + ": " + point
                + " " + (LocalizationUtil.localizedResourceBundle.getString("YourLevelKey"))
                + ": " + (LocalizationUtil.localizedResourceBundle.getString("Rand")));
        }

        showQues.setText(question.getQuestion());
        showQues.setFont(new Font("Serif",Font.BOLD,20));
        
        if (question instanceof OpenQuestion) { //if is open question need to get strinng answer
            ansField.setSize(100, 10); 
            optionsPanel.add(ansField);

        } else if (question instanceof TrueFalseQuestion) {  //if true false ques it will add 2 radio button  
            option1Button.setText(question.getAnswer()[0]);
            option2Button.setText(question.getAnswer()[0 + 1]);
            answersGroup.add(option1Button);
            answersGroup.add(option2Button);
            
            option1Button.setFont(new Font("Calibri",Font.BOLD,17));
            option2Button.setFont(new Font("Calibri",Font.BOLD,17));

            optionsPanel.add(option1Button);
            optionsPanel.add(option2Button);

        } else if(question instanceof AmricanQuestion) {  //if is amrican question add 4 radio button
            option1Button.setText(question.getAnswer()[0]);
            option2Button.setText(question.getAnswer()[0 + 1]);
            option3Button.setText(question.getAnswer()[0 + 2]);
            option4Button.setText(question.getAnswer()[0 + 3]);
            
            option1Button.setFont(new Font("Calibri",Font.BOLD,17));
            option2Button.setFont(new Font("Calibri",Font.BOLD,17));
            option3Button.setFont(new Font("Calibri",Font.BOLD,17));
            option4Button.setFont(new Font("Calibri",Font.BOLD,17));
            

            answersGroup.add(option1Button);
            answersGroup.add(option2Button);
            answersGroup.add(option3Button);
            answersGroup.add(option4Button);

            optionsPanel.add(option1Button);
            optionsPanel.add(option2Button);
            optionsPanel.add(option3Button);
            optionsPanel.add(option4Button);
        }
       
        
        topPanel.add(showQues);
        liveResultPanel.add(liveResult);
        
        fullScreenPanel.add(topPanel, BorderLayout.NORTH);
        fullScreenPanel.add(optionsPanel, BorderLayout.CENTER);
        fullScreenPanel.add(liveResultPanel, BorderLayout.EAST);
        fullScreenPanel.add(okExitPanel,BorderLayout.SOUTH);
        
        
        okButton.setOpaque(false);
        exitButton.setOpaque(false);
        okExitPanel.setOpaque(false);
        option1Button.setOpaque(false);
        option2Button.setOpaque(false);
        option3Button.setOpaque(false);
        option4Button.setOpaque(false);
        topPanel.setOpaque(false);
        optionsPanel.setOpaque(false);
        liveResultPanel.setOpaque(false);
        fullScreenPanel.setOpaque(false);

        backgroundImage.add(fullScreenPanel, BorderLayout.CENTER);

    }
   
   public void addListeners() {
        okButton.addActionListener(this);
        exitButton.addActionListener(this);
        newMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);
        changeLang.addActionListener(this);
        this.addWindowListener(new MyWindowListener());
        exitMenuItem.addActionListener(this);

    } 

   public void buildMenu() {
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

   class MyWindowListener extends WindowAdapter {
       /*add are "you shure exit?" promp */
        @Override
        public void windowClosing(WindowEvent we
        ) {
            showExitDialog();
        }
    }

   public void showNonSelctedDialog() {
       /*Non Selcted Dialog acording the current languche*/
        JOptionPane.showConfirmDialog(FormClass.this, (LocalizationUtil.localizedResourceBundle.getString("didntChoseAnswer")), (LocalizationUtil.localizedResourceBundle.getString("titleWorning")), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

    }

   public void showExitDialog() {
       /*"you shure exit?" promp acorrding curent languche*/
        int result = JOptionPane.showConfirmDialog(FormClass.this, // parent component
                (LocalizationUtil.localizedResourceBundle.getString("areYouSureKey")), // message
                (LocalizationUtil.localizedResourceBundle.getString("titlrExitDialog")), // title of the dialog box
                JOptionPane.YES_NO_OPTION,// indicates buttons ot display
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

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
   
   public void aboutDialog() {
       /*about promp acorrding curent languche*/
        JOptionPane.showMessageDialog(this, (LocalizationUtil.localizedResourceBundle.getString("AllReservd")));
    }

   @Override
   /*action whan any button was pressed*/
   public void actionPerformed(ActionEvent ae) {
        boolean correct = false;
        String sound;
        String selectedAnswer;
        if (ae.getSource() == okButton) {
            if (question instanceof TrueFalseQuestion) {  /*Cheek if true false question*/

                if (option1Button.isSelected()) {
                    selectedAnswer = option1Button.getText();
                    if (question.getRightAns() == 1) {
                        correct = true;
                    }
                } else if (option2Button.isSelected()) {
                    selectedAnswer = option2Button.getText();
                    if (question.getRightAns() == 2) {
                        correct = true;
                    }
                } else {
                    showNonSelctedDialog();
                    return;
                }
            } else if (question instanceof AmricanQuestion) { /* Cheek if 4 opation question */

                if (option1Button.isSelected()) {
                    selectedAnswer = option1Button.getText();
                    if (question.getRightAns() == 1) {
                        correct = true;
                    }
                } else if (option2Button.isSelected()) {
                    selectedAnswer = option2Button.getText();
                    if (question.getRightAns() == 2) {
                        correct = true;
                    }
                } else if (option3Button.isSelected()) {
                    selectedAnswer = option3Button.getText();
                    if (question.getRightAns() == 3) {
                        correct = true;
                    }
                } else if (option4Button.isSelected()) {
                    selectedAnswer = option4Button.getText();
                    if (question.getRightAns() == 4) {
                        correct = true;
                    }
                } else {
                    showNonSelctedDialog();
                    return;
                }
            } else if (question instanceof OpenQuestion) {/*Cheek if is Open question*/

                if (ansField.getText().equals("")) {
                    showNonSelctedDialog();
                    return;
                }
                
                if(ansField.getText().equalsIgnoreCase(question.getAnswer()[0]))
                    correct = true;
                
            }

            if (correct) //if the answer correct
            {
                PlaySounds h = new PlaySounds(correctSound);//play corect sound
                System.out.println("Corect! question value point " + question.getPoint());
                current.setCorrectAnsCnt(1); //count the time of the right ansewr
                point += question.getPoint(); //sum the value of point of this question 
                correct = false; //return the static var to false
            } else {
                //point -= question.getPoint();  //if player wrong sub point
                current.setWrongAnsCnt(1);//sum the incorrect answer
            }
            //in any case correct or not open the game with less quse (n-1) with the same current
            try {
                Game game = new Game(--remainedQuestios, current, false,currentLevel);//false mining the is not new game keep use the same ArrayList
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (ae.getSource() == changeLang) { //if change languche button pressed
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
        } else if (ae.getSource() == exitButton) {   //if exit button pressed
            showExitDialog();
        } else if (ae.getSource() == newMenuItem) {  //if new game button pressed
            newGameDialog();
        } else if (ae.getSource() == aboutMenuItem) {   //if about button pressed
            aboutDialog();
        }
    }

   public void updateCaptions() { //method that refresh all the label+button languche
        okButton.setText(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        exitButton.setText(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));
        
        if(currentLevel!=0){
        liveResult.setText(" " + (LocalizationUtil.localizedResourceBundle.getString("PointsKey"))
                + ": " + current.getPoints()
                + " " + (LocalizationUtil.localizedResourceBundle.getString("YourLevelKey"))
                + ": " + current.getLevel());
        }
        else{
             liveResult.setText(" " + (LocalizationUtil.localizedResourceBundle.getString("PointsKey"))
                + ": " + current.getPoints()
                + " " + (LocalizationUtil.localizedResourceBundle.getString("YourLevelKey"))
                + ": " + (LocalizationUtil.localizedResourceBundle.getString("Rand")));
        }
        
        this.setTitle((LocalizationUtil.localizedResourceBundle.getString("GameTitle")));
        fileMenu.setText(LocalizationUtil.localizedResourceBundle.getString("FileKey"));
        newMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("NewGameKey"));
        exitMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));
        aboutMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("AboutKey"));
        helpMenu.setText(LocalizationUtil.localizedResourceBundle.getString("HelpKey"));
        changeLang.setText(LocalizationUtil.localizedResourceBundle.getString("languageKey"));
    }

}
