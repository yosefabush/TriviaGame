/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Yosef
 */
public class DataBase {
    private static DataBase instance = null;
    private int wrongAnsCnt;

    
    
    public static DataBase getInstance() {
        if(instance == null)
            instance=new DataBase();
        return instance;
    }
    
    public ArrayList allQuestiont = new ArrayList();
    public Question[] question = new Question[10];
    
    private int poinSummry = 0;

    public DataBase() {

        NewQuestion();
        //getAllQuestionFromDb();

    }
    
    public void getAllQuestionFromDb(){
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
                
                question[0].setQuestion(resultSet.getString("Question"));
            }

            resultSet.close();		// close resultSet
            statement.close();		// close statement and resultSet
            connection.close();		// close connection, statement and resultSet 	
         }catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
        
    public void NewQuestion() {
        for (int i = 0; i < 10; i++) {
            String[] ansArr = new String[4];
            switch (i) {
                case 0:
                    ansArr[0] = "2";
                    ansArr[1] = "10";
                    question[i] = new Question("How much years Banjmin Natnyahu is prime minster? ", ansArr, 2, 1,1,true,false);
                    allQuestiont.add(question[i]);
                    break;
                case 1:
                    ansArr[0] = "1900";
                    ansArr[1] = "1988";
                    question[i] = new Question("When JAVA was made out? ", ansArr, 2, 1,2,true,false);
                    allQuestiont.add(question[i]);
                    break;
                case 2:
                    ansArr[0] = "Sundar Pichai";
                    ansArr[1] = "Bil gats";
                    ansArr[2] = "Mark zokerberg";
                    ansArr[3] = "Stive jobs";
                    question[i] = new Question("Who is th CEO of Google? ", ansArr, 1, 1,3,false,false);
                    allQuestiont.add(question[i]);
                    break;
                case 3:
                    ansArr[0] = "2008";
                    ansArr[1] = "2011";
                    ansArr[2] = "2005";
                    ansArr[3] = "2012";
                    question[i] = new Question("Wich year the androaid OS was created? ", ansArr, 1, 1,2,false,false);
                    allQuestiont.add(question[i]);
                    break;
                case 4:
                    ansArr[0] = "yahud";
                    ansArr[1] = "alabama";
                    ansArr[2] = "mala adomim";
                    ansArr[3] = "ariel";
                    question[i] = new Question("In which State would you find the city of Birmingham? ", ansArr, 2, 1,2,false,false);
                    allQuestiont.add(question[i]);
                    break;

                case 5:
                    ansArr[0] = "True";
                    ansArr[1] = "False";;
                    question[i] = new Question("Bob Marly reggae singing star died 11th May 1981? ", ansArr, 1, 1,3,true,false);
                    allQuestiont.add(question[i]);
                    break;
                case 6:
                    ansArr[0] = "67";
                    ansArr[1] = "8";
                    ansArr[2] = "9";
                    ansArr[3] = "10";
                    question[i] = new Question("How old is Isreal? ", ansArr, 1, 1,3,false,false);
                    allQuestiont.add(question[i]);
                    break;
                case 7:
                    ansArr[0] = "2008";
                    ansArr[1] = "2004";
                    ansArr[2] = "2011";
                    ansArr[3] = "2012";
                    question[i] = new Question("Wich year Osama bin laden diye? ", ansArr, 3, 1,2,false,false);
                    allQuestiont.add(question[i]);
                    break;
                case 8:
                    ansArr[0] = "Bark Obama";
                    ansArr[1] = "Bibi";
                    ansArr[2] = "Ben laden";
                    ansArr[3] = "Benet";
                    question[i] = new Question("Who is the presdent of USA? ", ansArr, 1, 1,1,false,false);
                    allQuestiont.add(question[i]);

                    break;
                case 9:
                    ansArr[0] = "Jeruselem";
                    question[i] = new Question("what is isreal capital?", ansArr, 2, 1,3,false,true);
                    allQuestiont.add(question[i]);
                    break;
            }

        }

    }

    public int getPoinSummry() {
        return poinSummry;
    }

    public void setPoinSummry(int poinSummry) {
        this.poinSummry += poinSummry;
    }
    
    public int getWrongAnsCnt() {
        return wrongAnsCnt;
    }

    public void setWrongAnsCnt(int wrongAnsCnt) {
        this.wrongAnsCnt+= wrongAnsCnt;
    }

   public boolean GetUser(String userName, String Password) {
        return true;
    }

    public void AddUser(String userName, String Password) {
        
    }

}
