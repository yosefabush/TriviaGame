package Trivia;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contain all the logic of the game
 *
 * @author Yosef
 */
public class Game {

    /**
     *
     */
    private static User current;
    /**
     *This collection variable will hold all the question from the DataBase
     */
    private static ArrayList<Question> allQuesFromDB = new <Question>ArrayList();
    /**
     *
     */
    private static int level;

    /**
     * Game constructor
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
        try {
            Play(wantedQuestion);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Start game method
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
            } else {
                System.out.println("Finish multi player game");
                sendToServerTotalScore(current, score);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TotalSummry total = new TotalSummry(current, SelectGame.ois.readObject().toString(), true);
                            total.setVisible(true);
                        } catch (IOException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                thread.start();

            }
        }
    }

    /**
     *this method try to update the score of the player in DB if the score
     * that player get higher from what he has in DB
     * @param player Object
     * @return boolean true or false
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
