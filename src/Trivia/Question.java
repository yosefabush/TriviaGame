/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

/**
 *
 * @author Yosef
 */
public abstract class Question {

    private String question;
    private int point;
    private int level;   
    private boolean cheekIfQuesWasAsked;
    
    public Question(String Ques,int Point,int Level)
    { 
        setQuestion(Ques);
        setPoint(Point);
        setLevel(Level);
     }
    
    public boolean getCheekIfQuesWasAsked() {
        return cheekIfQuesWasAsked;
        
    }
    public void setCheekIfQuesWasAsked(boolean cheekIfQuesWasAsked) {
        this.cheekIfQuesWasAsked = cheekIfQuesWasAsked;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point+= point;
    }
    
    public abstract String [] getAnswer();
    public abstract int getRightAns();
    
}
