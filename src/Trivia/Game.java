package Trivia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    //private DataBase dataBase=new DataBase();
    private static Question[] question ;
    private int Ans;
    private boolean ansValid=false;
    private int playerScore;
    private int level;
    int i=0;
    static User current;
    static ArrayList<Question> allQuesFromDB=new <Question>ArrayList();
    int numQues=TestQuery.getInstance().countQues();
    int setTheRangeOfRand=1+OpenScreen.cmbQuesNum.getSelectedIndex();
    static int n =0;

    

    public Game(int num,User current,boolean newGame) throws Exception {
        if(newGame ==true)
                n=0;
        if(n==0){
            this.allQuesFromDB.clear();
            this.allQuesFromDB=TestQuery.getInstance().getQuestion();
        }
        n++;
        
        this.current=current;
        
        fullLocalQuestionArrayFromArrayList();
        //printQues();
        Play(num);
     
    }
    
   
    public void Play(int number) throws Exception{

       
         Random rand = new Random();
          
             // validation no duplcate ques
         int random=0;
         while(true&&number>0){
               random=rand.nextInt(question.length); 
               System.out.println("All ques in db "+numQues);
              if(question[random].getCheekIfQuesWasAsked()==false)
              {
                 System.out.println("Rand num "+random);
                allQuesFromDB.get(random).setCheekIfQuesWasAsked(true);
                question[random].setCheekIfQuesWasAsked(true);
                break;
              }
         }
             FormClass f1;
             TotalSummry finisGame;
             if(number>0){
                 f1=new FormClass(question[random],number,current);
                  //f1=new FormClass(allQuesFromDB,number,current);
             }
             else{
                  current.setPoints(FormClass.point);
                  FormClass.point=0;                    //סכימה של הניקוד של המשתמש הנוכחי בסוף המשחק
                  if(updateFinalScore(current)){
                     System.out.println("New High Score!");
                   finisGame=new TotalSummry(current,current.getPoints());
                     //current.setPoints(0);  //in the end show summry point
                   finisGame.setVisible(true);
                  }
                  else{
                   System.out.println("Update score Faild mybe your have highr score ...");
                   finisGame=new TotalSummry(current);//in the end show summry point
                   finisGame.setVisible(true);
             }
             }
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level+= level;
    }
    public void printScore() {
        System.out.println("The total score is: " + getPlayerScore());
    }
    public int getPlayerScore() {
        return playerScore;
    }
    public void setPlayerScore(int playerScore) {
        this.playerScore += playerScore;
    }
    public void CheekLevel(int num) {
        for (int i = 0; i < num; i++) {
            playerScore += question[i].getPoint();
        }

    }
    public boolean updateFinalScore(User player) {
        
       try {
            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);
            PreparedStatement ps = connection.prepareStatement("update tblrecords set Score='"+player.getPoints()+"'where tblrecords.UserID='"+player.getUserID()+"'and tblrecords.`Score`<'"+player.getPoints()+"'");
             int res=ps.executeUpdate();
             
               if(res>0){
                  ps.close();
                 return true;
               }
               else{
                 ps.close();
                 return false; 
               }
         
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
             return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    
    }
    public void fullLocalQuestionArrayFromArrayList(){
        int i=0;
        int countAues=allQuesFromDB.size();//TestQuery.getInstance().countQues(); //count the real number of question from data base
        this.question=new Question[countAues];
        for(Question q:this.allQuesFromDB){
            if(q instanceof Question ){
                this.question[i]=q;  //loop 51 time!!! WTF out of bound
                i++;
            }
           // System.out.println(q.getQuestion()); //print only 5 ques@#!!!!!!
        }
        
        
        
    }
    public void printQues()
    {
        for(int i=0;i<question.length;i++)
            System.out.println(question[i].getQuestion());
    }
}
