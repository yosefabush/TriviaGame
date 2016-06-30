
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
    
    /**
     *
     * @param username
     * @param password
     * @param userID
     */
    public User(String username,String password,int userID){
     setUserName(username);
     setPassword(password);
     setUserID(userID);
    }
    
    /**
     *
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     *
     * @param points
     */
    public void setPoints(int points) {
        this.points= points;
    }

    /**
     *
     * @param zero
     */
    public void setZeroPoints(int zero) {
        this.points= zero;
    }
    
    /**
     *
     * @return
     */
    public int getCorrectAnsCnt() {
        return correctAnsCnt;
    }

    /**
     *
     * @param correctAnsCnt
     */
    public void setCorrectAnsCnt(int correctAnsCnt) {
        this.correctAnsCnt += correctAnsCnt;
    }

    /**
     *
     * @param zero
     */
    public void setZeroCorrectAnsCnt(int zero) {
        this.correctAnsCnt = zero;
    }
  
    /**
     *
     * @return
     */
    public int getWrongAnsCnt() {
        return wrongAnsCnt;
    }

    /**
     *
     * @param wrongAnsCnt
     */
    public void setWrongAnsCnt(int wrongAnsCnt) {
        this.wrongAnsCnt+= wrongAnsCnt;
    }

    /**
     *
     * @param zero
     */
    public void setZeroWrongAnsCnt(int zero) {
        this.wrongAnsCnt= zero;
    }
    
    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level+= level;
    }

    /**
     *
     * @param zero
     */
    public void setZeroLevel(int zero) {
        this.level= level;
    }
    
    /**
     *
     * @param cuurent
     * @return
     */
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
    
    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    
    
}
