/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yosef
 */
public class User {
    private String userName;
    private String password;
    private int userID;
    private int points=0;
    private int wrongAnsCnt=0;
    private int level;
    private int correctAnsCnt=0;
    
    public User(String username,String password,int userID){
     setUserName(username);
     setPassword(password);
     setUserID(userID);
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points= points;
    }
    public void setZeroPoints(int zero) {
        this.points= zero;
    }
    
     public int getCorrectAnsCnt() {
        return correctAnsCnt;
    }

    public void setCorrectAnsCnt(int correctAnsCnt) {
        this.correctAnsCnt += correctAnsCnt;
    }
     public void setZeroCorrectAnsCnt(int zero) {
        this.correctAnsCnt = zero;
    }
  
    public int getWrongAnsCnt() {
        return wrongAnsCnt;
    }

    public void setWrongAnsCnt(int wrongAnsCnt) {
        this.wrongAnsCnt+= wrongAnsCnt;
    }
    public void setZeroWrongAnsCnt(int zero) {
        this.wrongAnsCnt= zero;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level+= level;
    }
    public void setZeroLevel(int zero) {
        this.level= level;
    }
    
    public String getHighstScore(User cuurent){
        String userScore=null;
        String sql="select Score from tblrecords where UserId='"+cuurent.userID+"'";
        
         try {
            Class.forName(DbUtilitis.dbDriver); //load the rigt server
            Connection connection
                    = DriverManager.getConnection(DbUtilitis.jdbcUrl,
                            DbUtilitis.jdbcUser,
                            DbUtilitis.jdbcPassword);
            PreparedStatement pstatment=connection.prepareStatement(sql);
            ResultSet resultSet = pstatment.executeQuery();
            while(resultSet.next()){
                  userScore=Integer.toString(resultSet.getInt("Score"));
                  return userScore;
             }
            resultSet.close();		// close resultSet
            pstatment.close();		// close statement and resultSet
            connection.close();		// close connection, statement and resultSet 	
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
             return userScore;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
             return userScore;
        }  
        return userScore;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    
    
}
