package Trivia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    static User current;
    static ArrayList<Question> allQuesFromDB = new <Question>ArrayList();
   

    public Game(int num, User current, boolean newGame) throws Exception {

        if (newGame == true) { //if is new game need to clear thee arreyList and full it from DB
            this.allQuesFromDB.clear();
            this.allQuesFromDB = DataBaseMange.getInstance().getQuestion();
        }
        this.current = current;
        Play(num);

    }

    public void Play(int remainingQues) throws Exception {

        Random rand = new Random();

        // validation no duplcate ques
        int random = 0;
        while (remainingQues > 0) {
            random = rand.nextInt(allQuesFromDB.size());
            //System.out.println("All ques in db "+numQues);
            if (allQuesFromDB.get(random).getCheekIfQuesWasAsked() == false) {
                //System.out.println("Chosen Rand num " + random);
                allQuesFromDB.get(random).setCheekIfQuesWasAsked(true); 
                break;
            }
        }
        FormClass f1;
        TotalSummry finisGame;
        if (remainingQues > 0) {
            f1 = new FormClass(allQuesFromDB.get(random), remainingQues, current);
        } else {
            current.setPoints(FormClass.point);  //סכימה של הניקוד של המשתמש הנוכחי בסוף המשחק
            FormClass.point = 0;
            FormClass.currentLevel=0; 
            current.setLevel(0);
            if (updateFinalScore(current)) {  
                System.out.println("New High Score!");
                finisGame = new TotalSummry(current, current.getPoints());
                finisGame.setVisible(true);
            } else {
                System.out.println("Update score Faild mybe your have highr score ...");
                finisGame = new TotalSummry(current);//in the end show summry point
                finisGame.setVisible(true);
            }
        }
    }

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
}
