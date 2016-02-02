package Trivia;

import static Trivia.PlaySounds.correctSound;
import static Trivia.PlaySounds.worngSound;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
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
public class FormClass extends JFrame implements ActionListener {

    JButton okButton;
    JButton exitButton;
    static int point=0;
    static int currentLevel=0;
    JLabel liveResult;
    static int cntLevel=0;
    JLabel showQues = new JLabel("Hello");
    JTextField ansField = new JTextField(20);
    JMenuBar myMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu helpMenu = new JMenu("Help");
    JMenuItem changeLang=new JMenuItem("Change languech");
    JMenuItem newMenuItem = new JMenuItem("New Game");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenuItem aboutMenuItem = new JMenuItem("About");
    JRadioButton option1Button = new JRadioButton();
    JRadioButton option2Button = new JRadioButton();
    JRadioButton option3Button = new JRadioButton();
    JRadioButton option4Button = new JRadioButton();
    //JRadioButton[] responses;
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
    JPanel okExitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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

    public FormClass(Question question, int remainedQuestios,User currentPlayer) {
        
        //this.allQuestiont=question;
        this.current=currentPlayer;
        this.question=question;//(Question)question.get(remainedQuestios);
        this.remainedQuestios=remainedQuestios;
        this.rightAns=question.getRightAns();
        //Random rand = new Random();
        //int random = rand.nextInt(10);
        initComponents();
        addListeners();
        buildMenu();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(500, 400);
        this.setVisible(true);
        setLocationRelativeTo(null);

    }
    
    /**
     *
     */
    public void initComponents() {

        okButton = new JButton("OK");
        exitButton = new JButton("Exit");
        
        
        liveResult=new JLabel(" "
                + "points: "+current.getPoints()+" Your Level: "+current.getLevel());
        okExitPanel.add(okButton, BorderLayout.CENTER);
        okExitPanel.add(exitButton, BorderLayout.CENTER);
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
        topPanel.add(liveResult);
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(optionsPanel, BorderLayout.CENTER);
        

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
        
        JOptionPane.showConfirmDialog(FormClass.this, "you didnt Chose answer", "Worning!",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        
    }
    public void showExitDialog() {
        int result = JOptionPane.showConfirmDialog(FormClass.this, // parent component
                "Are you sure you want to quit?", // message
                "Exit Dialog", // title of the dialog box
                JOptionPane.YES_NO_OPTION,// indicates buttons ot display
                JOptionPane.QUESTION_MESSAGE);
        //null);//new ImageIcon("images/questionmark.png")); 
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }
    public void newGameDialog(){
        
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to start new game?",
                    "New Game Dialog", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);
         if (result == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                OpenScreen newGame = new OpenScreen(current);
                newGame.setVisible(true);
         }
    }
    public void aboutDialog(){
        JOptionPane.showMessageDialog(this, "All Reservd to: \n  C Yosef & Lidor");
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
         boolean correct=false;
         String sound;
        if (ae.getSource() == okButton) {  
                String selectedAnswer;
               
                if(question.isTrueFalseQues()){  /*Cheek if true false questio*/
                        if (option1Button.isSelected()) {
                            selectedAnswer = option1Button.getText();
                            if (question.getRightAns() == 1) {
                                    correct=true;
                                }
                        }if (option2Button.isSelected()) {
                            selectedAnswer = option2Button.getText();
                            if (question.getRightAns() == 2) {
                                correct=true;
                            }
                        }

                     }
                else if(question.getEnswer().length==4){/*Cheek if 4 opation questio*/
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
                    } 
                    else if(question.isOpenQues()==true){/*Cheek if Open questio*/
                        if(ansField.getText().equalsIgnoreCase(openAnser)){
                            System.out.println(openAnser);//problem with test the ans because Question ans is int 
                                correct=true;
                        }
                      }

                     else {
                        showNonSelctedDialog();
                        return;
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
                Game game = new Game(--remainedQuestios,current,false);
                this.dispose();
            } catch (Exception ex) {
               ex.printStackTrace();
            }

        } else if (ae.getSource() == exitMenuItem) {
            showExitDialog();
        } else if (ae.getSource() == exitButton) {
            showExitDialog();    
        } else if (ae.getSource() == newMenuItem) {
            newGameDialog();
        } else if (ae.getSource() == aboutMenuItem) {
            aboutDialog();
        } else if (ae.getSource() == changeLang){
            
        }

   

    }

}
    