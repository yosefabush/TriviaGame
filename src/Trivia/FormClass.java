package Trivia;


// test irit
//test
import static Trivia.PlaySounds.correctSound;
import static Trivia.PlaySounds.worngSound;
import static Trivia.TotalSummry.currentPlayer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
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

    JButton okButton;
    JButton exitButton;
    JButton languageButton;
    static int point=0;
    static int currentLevel=0;
    JLabel liveResult;
    static int cntLevel=0;
    JLabel showQues=new JLabel();
    JTextField ansField = new JTextField(20);
    JMenuBar myMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu();
    JMenu helpMenu = new JMenu();
    JMenuItem changeLang=new JMenuItem();
    JMenuItem newMenuItem = new JMenuItem();
    JMenuItem exitMenuItem = new JMenuItem();
    JMenuItem aboutMenuItem = new JMenuItem();
    JRadioButton option1Button = new JRadioButton();
    JRadioButton option2Button = new JRadioButton();
    JRadioButton option3Button = new JRadioButton();
    JRadioButton option4Button = new JRadioButton();
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
    JPanel okExitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel liveResultPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    ButtonGroup answersGroup = new ButtonGroup();
    private Question question = new Question();
    public ArrayList allQuestiont = new ArrayList();
    Random rand = new Random();
    int ranQues;
    static int remainedQuestios;
    int selected;
    int rightAns;
    static User current;
    private boolean UpLevel1,UpLevel2=false;
    private String openAnser;
    ChoseLanguage choseLan=new ChoseLanguage();

    public FormClass(Question question, int remainedQuestios,User currentPlayer) {
        
        //this.allQuestiont=question;
        this.current=currentPlayer;
        this.question=question;//(Question)question.get(remainedQuestios);
        this.remainedQuestios=remainedQuestios;
        this.rightAns=question.getRightAns();
        initComponents();
        buildMenu();
        addListeners();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(500, 400);
        this.setVisible(true);
        setLocationRelativeTo(null);

    }
    
    /**
     *
     */
    public void initComponents() {

        okButton = new JButton(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        exitButton = new JButton(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));
        languageButton = new JButton(LocalizationUtil.localizedResourceBundle.getString("languageKey"));
        
        
        liveResult=new JLabel(" "+(LocalizationUtil.localizedResourceBundle.getString("PointsKey"))
                             +" "+current.getPoints()
                             +" "+(LocalizationUtil.localizedResourceBundle.getString("YourLevelKey"))
                             +" "+current.getLevel());
        okExitPanel.add(okButton, BorderLayout.CENTER);
        okExitPanel.add(exitButton, BorderLayout.CENTER);
        okExitPanel.add(languageButton,BorderLayout.NORTH);
        this.add(okExitPanel, BorderLayout.SOUTH);

        showQues.setText(question.getQuestion());

        if(question.isOpenQues()==true){   //if is open question need to get strinng  
          openAnser=question.getEnswer()[0];
            optionsPanel.add(ansField);
          
        }
       else if(question.isTrueFalseQues()==true){  //if true false ques it will add 2 radio button  
        option1Button.setText(question.getEnswer()[0]);
        option2Button.setText(question.getEnswer()[0 + 1]);
        answersGroup.add(option1Button);
        answersGroup.add(option2Button);
        
        optionsPanel.add(option1Button);
        optionsPanel.add(option2Button);
            
        }else{                              //add 4 radio button
        option1Button.setText(question.getEnswer()[0]);
        option2Button.setText(question.getEnswer()[0 + 1]);
        option3Button.setText(question.getEnswer()[0 + 2]);
        option4Button.setText(question.getEnswer()[0 + 3]);
        
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
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(optionsPanel, BorderLayout.CENTER);
        this.add(liveResultPanel,BorderLayout.EAST);

    }
    public void addListeners() {
        
        okButton.addActionListener(this);
        exitButton.addActionListener(this);
        newMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);
        changeLang.addActionListener(this);
        languageButton.addActionListener(this);       
        this.addWindowListener(new MyWindowListener());
        exitMenuItem.addActionListener(this);
        
    } //button listner not workin
    public void buildMenu() {
        fileMenu = new JMenu(LocalizationUtil.localizedResourceBundle.getString("FileKey"));
        helpMenu = new JMenu(LocalizationUtil.localizedResourceBundle.getString("HelpKey"));
        changeLang=new JMenuItem(LocalizationUtil.localizedResourceBundle.getString("ChangeLanguechKey"));
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

        @Override
        public void windowClosing(WindowEvent we
        ) {
            showExitDialog();
        }
    }
    public void showNonSelctedDialog() {
        
        JOptionPane.showConfirmDialog(FormClass.this, (LocalizationUtil.localizedResourceBundle.getString("didntChoseAnswer")),(LocalizationUtil.localizedResourceBundle.getString("titleWorning")),JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        
    }
    public void showExitDialog() {
        int result = JOptionPane.showConfirmDialog(FormClass.this, // parent component
                (LocalizationUtil.localizedResourceBundle.getString("areYouSureKey")), // message
                (LocalizationUtil.localizedResourceBundle.getString("titlrExitDialog")), // title of the dialog box
                JOptionPane.YES_NO_OPTION,// indicates buttons ot display
                JOptionPane.QUESTION_MESSAGE);
      
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }
    public void newGameDialog(){
        
        int result = JOptionPane.showConfirmDialog(this, (LocalizationUtil.localizedResourceBundle.getString("AreYouSureYouStartNewGame")),
                    (LocalizationUtil.localizedResourceBundle.getString("NewGameDialog")), 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);
         if (result == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                 current.setPoints(0);
                 current.setWrongAnsCnt(0);
                 cntLevel=0;
                OpenScreen newGame = new OpenScreen(current);
                newGame.setVisible(true);
         }
    }
    public void aboutDialog(){
        JOptionPane.showMessageDialog(this, (LocalizationUtil.localizedResourceBundle.getString("AllReservd")));
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
         boolean correct=false;
         String sound;
        if (ae.getSource() == okButton) {  
                String selectedAnswer;
               
                if(question.isTrueFalseQues()){  /*Cheek if true false question*/
                        if (option1Button.isSelected()) {
                            selectedAnswer = option1Button.getText();
                            if (question.getRightAns() == 1) {
                                    correct=true;
                                }
                        }else if (option2Button.isSelected()) {
                            selectedAnswer = option2Button.getText();
                            if (question.getRightAns() == 2) {
                                correct=true;
                            }
                        }else{
                                showNonSelctedDialog();
                                return;
                            }
                     }
                else if(question.getEnswer().length==4){ /* Cheek if 4 opation question */

                        if (option1Button.isSelected()) {
                            selectedAnswer = option1Button.getText();
                            if (question.getRightAns() == 1) {
                                correct=true;
                            }
                        } else if (option2Button.isSelected()) {
                            selectedAnswer = option2Button.getText();
                            if (question.getRightAns() == 2) {
                                correct=true;
                            }
                        } else if (option3Button.isSelected()) {
                            selectedAnswer = option3Button.getText();
                            if (question.getRightAns() == 3) {
                                correct=true;
                            }
                        } else if (option4Button.isSelected()) {
                            selectedAnswer = option4Button.getText();
                            if (question.getRightAns() == 4) {
                                correct=true;
                            }
                          }
                        else{
                        showNonSelctedDialog();
                        return;
                        }
                    } 
                    else {//if(question.isOpenQues()==true){/*Cheek if is Open question*/
                        if(ansField.getText().equals("")){
                                 showNonSelctedDialog();
                                 return;
                        }
                        if(ansField.getText().equalsIgnoreCase(openAnser)){
                            System.out.println(openAnser);
                                correct=true;      
                        }  
                      }
                
                if(correct) //if the answer correct sum the value of point of this question
                {
                    PlaySounds h=new PlaySounds(correctSound);  
                      System.out.println("this ques point "+question.getPoint());
                      point+=question.getPoint();
                      current.setPoints(point);
                      correct=false;

//                     if(point>2&&UpLevel1==false){
//                         UpLevel1=true;
//                         currentLevel=++cntLevel;
//                      current.setLevel(cntLevel);
//                         JOptionPane.showConfirmDialog(FormClass.this, "you passe to the next Levevl!","Congratlition!" ,JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
//                     }
               }
                else {//sum the incorrect answer
                    
                    PlaySounds h=new PlaySounds(worngSound);
                     current.setWrongAnsCnt(1);
        }
                //in any case correct or not open the game with less quse (n-1)
            try {    
                Game game = new Game(--remainedQuestios,current,false);//false mining the is not new game
                this.dispose();
            } catch (Exception ex) {
               ex.printStackTrace();
            }

        }else if(ae.getSource()==languageButton||ae.getSource()==changeLang){ //מצריך לחיצה כפןלה על השפה לתקן
            choseLan.setVisible(true);
            updateCaptions();
            String selectedLanguage =choseLan.getLanguche();
            LocalizationUtil.localizedResourceBundle = ResourceBundle.getBundle("resources.uimessages", new Locale(selectedLanguage));
            updateCaptions();
        }else if (ae.getSource() == exitMenuItem) {
            showExitDialog();
        } else if (ae.getSource() == exitButton) {
            showExitDialog();    
        } else if (ae.getSource() == newMenuItem) {
            newGameDialog();
           
        } else if (ae.getSource() == aboutMenuItem) {
            aboutDialog();
        }
    }

    public void updateCaptions()
    {
        okButton.setText(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        exitButton.setText(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));
        languageButton.setText(LocalizationUtil.localizedResourceBundle.getString("languageKey"));
        
        fileMenu.setText(LocalizationUtil.localizedResourceBundle.getString("FileKey"));
        newMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("NewGameKey"));
        exitMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("ExitKey"));
        aboutMenuItem.setText(LocalizationUtil.localizedResourceBundle.getString("AboutKey"));
        helpMenu.setText(LocalizationUtil.localizedResourceBundle.getString("HelpKey"));
        changeLang.setText(LocalizationUtil.localizedResourceBundle.getString("languageKey"));
        
    }

}
    
