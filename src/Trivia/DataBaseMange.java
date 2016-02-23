/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Yosef
 */
public class DataBaseMange {

    private static DataBaseMange instance = null;
    private static Question question;
    ArrayList<Question> allQuesFromDB = new <Question>ArrayList();

    public DataBaseMange() {

    }
    
    public ArrayList getQuestion() {

        try {

            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);

            Statement statement = connection.createStatement();

            String allCustomersQuery = "SELECT tblquestion.Question,tblquestion.Point,tblquestion.QuesStatusAskOrNot,tblquestion.Level,tblanswer.`PosibleAns`,tblanswer.Answer1,tblanswer.Answer2,tblanswer.Answer3,tblanswer.Answer4,tblanswer.RightAnswer FROM tblanswer INNER JOIN tblquestion ON tblquestion.QuestionID=tblanswer.AnswerID;";
            ResultSet resultSet = statement.executeQuery(allCustomersQuery);

            while (resultSet.next()) {
                int point = resultSet.getInt("Point");
                int level = resultSet.getInt("Level");
                String ques = resultSet.getString("Question");
                int countOfPosibalans = resultSet.getInt("PosibleAns");
                String[] posibaleAns = new String[countOfPosibalans];
                
                if (countOfPosibalans == 1) {
                    String ens = resultSet.getString("Answer1");
                    String stringAns = Integer.toString(resultSet.getInt("RightAnswer"));
                    question=new OpenQuestion(ens,stringAns,ques,point,level);
                  
                }
                if (countOfPosibalans == 4) {
                    String ens1 = resultSet.getString("Answer1");
                    String ens2 = resultSet.getString("Answer2");
                    String ens3 = resultSet.getString("Answer3");
                    String ens4 = resultSet.getString("Answer4");
                    posibaleAns[0] = ens1;
                    posibaleAns[1] = ens2;
                    posibaleAns[2] = ens3;
                    posibaleAns[3] = ens4;
                    int rightAns = resultSet.getInt("RightAnswer");
                    question=new AmricanQuestion(posibaleAns,rightAns,ques,point,level);
                    
                } else if (countOfPosibalans == 2) {
                    String ens1 = resultSet.getString("Answer1");
                    String ens2 = resultSet.getString("Answer2");
                    posibaleAns[0] = ens1;
                    posibaleAns[1] = ens2;
                    int rightAns = resultSet.getInt("RightAnswer");
                    question=new TrueFalseQuestion(posibaleAns,rightAns,ques,point,level);
                    
                }
                
                 question.setCheekIfQuesWasAsked(false);
                 allQuesFromDB.add(question);
         
            }  
            resultSet.close();		// close resultSet
            statement.close();		// close statement and resultSet
            connection.close();
            return allQuesFromDB;       // close connection, statement and resultSet 	
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public int countQues() {
        int quesNum = 0;
        try {

            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);

            Statement statement = connection.createStatement();

            String allCustomersQuery = "SELECT * FROM tblquestion;";
            ResultSet resultSet = statement.executeQuery(allCustomersQuery);

            while (resultSet.next()) {
                quesNum++;
            }

            resultSet.close();		// close resultSet
            statement.close();		// close statement and resultSet
            connection.close();		// close connection, statement and resultSet 	
            return quesNum;

        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //return quesNum;
        }
        return 0;

    }
    
    public int countQues(int level) {
        int quesNum = 0;
        try {

            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);

            Statement statement = connection.createStatement();

            String allCustomersQuery = "SELECT * FROM tblquestion where level='"+level+"';";
            ResultSet resultSet = statement.executeQuery(allCustomersQuery);

            while (resultSet.next()) {
                quesNum++;
            }

            resultSet.close();		// close resultSet
            statement.close();		// close statement and resultSet
            connection.close();		// close connection, statement and resultSet 	
            return quesNum;

        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //return quesNum;
        }
        return 0;

    }


    public static DataBaseMange getInstance() {

        if (instance == null) {
            instance = new DataBaseMange();
        }
        return instance;
    }


}
