package Trivia;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yosef
 */
public class Game implements Serializable{
    /**
     * 
     */
    private static User current;
     /**
     * 
     */
    private static ArrayList<Question> allQuesFromDB = new <Question>ArrayList();
     /**
     * 
     */
    private static int level;
   
    /**
     *
     * @param wantedQuestion
     * @param current
     * @param newGame
     * @param level
     * @throws Exception
     */
    public Game(int wantedQuestion, User current, boolean newGame,int level) throws Exception {
        if (newGame == true) { //if is new game need to clear thee arreyList and full it from DB
            this.level=level;
            this.allQuesFromDB.clear();
            this.allQuesFromDB = DataBaseMange.getInstance().getQuestion();
        }
       
        this.current = current;
        Play(wantedQuestion);

    }
    /**
     *
     * @param remainingQues
     * @throws Exception
     */
    public void Play(int remainingQues) throws Exception {

        Random rand = new Random();

        // validation no duplcate ques
        int random = 0;
        while (remainingQues > 0) {
            random = rand.nextInt(allQuesFromDB.size());
            if(level!=0){
                if (allQuesFromDB.get(random).getCheekIfQuesWasAsked() == false&&allQuesFromDB.get(random).getLevel()==level) {
                    allQuesFromDB.get(random).setCheekIfQuesWasAsked(true); 
                    break;
                }
            }
            else{
                if (allQuesFromDB.get(random).getCheekIfQuesWasAsked() == false) {
                    allQuesFromDB.get(random).setCheekIfQuesWasAsked(true); 
                    break;
                }
            }
        }
        FormClass f1;
        TotalSummry finisGame;
        if (remainingQues > 0) {
            f1 = new FormClass(allQuesFromDB.get(random), remainingQues, current,level);
        } else {
            current.setPoints(FormClass.point);  //set the point to the current player just when game over
            FormClass.point = 0; //set the static varibale to zero 
            FormClass.currentLevel=0;   //set the static varibale to zero 
            
            if (updateFinalScore(current)) {//cheek if the point of current player get new high score and update in DB  
  
                finisGame = new TotalSummry(current,"NewHigScor"); //open summery screen
                finisGame.setVisible(true);
                 //here wee need to send the result of the player to server and set how is the winner
                sendToServerTotalScore(current);
            } else {
               
                System.out.println("Update score Faild mybe your have highr score in DB...");
                finisGame = new TotalSummry(current,"YouCanBetrr");//in the end show summry point
                finisGame.setVisible(true);
                 //here wee need to send the result of the player to server and set how is the winner
                sendToServerTotalScore(current);
            }
        }
    }
    /**
     *
     * @param player
     * @return
     */
    public boolean updateFinalScore(User player) {

        try {
            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);
            PreparedStatement ps = connection.prepareStatement("update tblrecords set Score='" + player.getPoints() + "'where tblrecords.UserID='" + player.getUserID() + "'and tblrecords.`Score`<'" + player.getPoints() + "'");
            int res = ps.executeUpdate();

            if (res > 0) {
                ps.close();
                return true;
            } else {
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
     /**
     *this method send the total score to server 
     * and the server tell us how is the winner between the players
     * @param player
     * 
     */
    public void sendToServerTotalScore(User player){
        //player say to the uther player finish to play
        SelectGame1.output.println(true);
        
        //Socket clientSocket = null;
       // SelectGame1.output.println(true);
        //SelectGame1.output.println(player.getUserName());
        //SelectGame1.output.println(player.getPoints());

      /*  try {
                    clientSocket = new Socket("localhost", 2222);
                    input = new DataInputStream(clientSocket.getInputStream());
                    PrintStream output = new PrintStream(clientSocket.getOutputStream());
                    //Scanner userInput = new Scanner(System.in);	
                    output.println(player.getPoints());

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
        
    }
}
