package Trivia;

import TriviaGameServer.ThreadHandler;
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
public class Game implements Serializable {

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
    public Game(int wantedQuestion, User current, boolean newGame, int level) throws Exception {
        if (newGame == true) { //if is new game need to clear thee arreyList and full it from DB
            this.level = level;
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
            if (level != 0) {
                if (allQuesFromDB.get(random).getCheekIfQuesWasAsked() == false && allQuesFromDB.get(random).getLevel() == level) {
                    allQuesFromDB.get(random).setCheekIfQuesWasAsked(true);
                    break;
                }
            } else if (allQuesFromDB.get(random).getCheekIfQuesWasAsked() == false) {
                allQuesFromDB.get(random).setCheekIfQuesWasAsked(true);
                break;
            }
        }
        FormClass f1;
        TotalSummry finisGame;
        if (remainingQues > 0) {
            f1 = new FormClass(allQuesFromDB.get(random), remainingQues, current, level);
        } else {
            int score = FormClass.point;
            current.setPoints(FormClass.point);  //set the point to the current player just when game over
            FormClass.point = 0; //set the static varibale to zero 
            FormClass.currentLevel = 0;   //set the static varibale to zero 
            if (SelectGame.multiPlayerGame == false) {
                System.out.println("Finish single player game");
                if (updateFinalScore(current)) {//cheek if the point of current player get new high score and update in DB  
                    finisGame = new TotalSummry(current, "NewHigScor"); //open summery screen
                    finisGame.setVisible(true);

                } else {
                    System.out.println("Update score Faild mybe your have highr score in DB...");
                    finisGame = new TotalSummry(current, "YouCanBetrr");//in the end show summry point
                    finisGame.setVisible(true);

                }
            }else {
                System.out.println("Finish multi player game");
                //cheek if the point of current player get new high score and update in DB  
                    if (updateFinalScore(current)) {

                       // finisGame = new TotalSummry(current, "NewHigScor"); //open summery screen
                       // finisGame.setVisible(true);
                       //here wee need to send the result of the player to server and set how is the winner
                       //loading load=new loading();
                       //load.setVisible(true);
                       sendToServerTotalScore(current, score);
                   } else {

                       System.out.println("Update score Faild mybe your have highr score in DB...");
                       // finisGame = new TotalSummry(current, "YouCanBetrr");//in the end show summry point
                       //finisGame.setVisible(true);
                       //here wee need to send the result of the player to server and set how is the winner
                      // loading load=new loading();
                      // load.setVisible(true);
                       sendToServerTotalScore(current, score);
                   }
                        try {
                            loading load=new loading(SelectGame.ois.readObject().toString());
                            load.setVisible(true);
                            //System.out.println(SelectGame.ois.readObject());
                            System.out.println("i finish play");
                        } catch (IOException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            System.out.println("one Player still play");
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
     * this method send the total score to server and the server tell us how is
     * the winner between the players
     *
     * @param player
     * @param score
     *
     */
    public void sendToServerTotalScore(User player, int score) {

        try {
            SelectGame.oos.writeObject(player);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
