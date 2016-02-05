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
public class Question {

    private String question;
    private int rightAns;
    private String openAnser;
    private String [] enswer;
    private boolean cheekIfQuesWasAsked;
    private int point;
    private int level;
    private boolean trueFalseQues=false; //need to set to all question this to ask how much radio button to generte
    private boolean openQues=false;
        
    public Question(){
        
        
    }

   
       public Question(String Ques,String[] ans,int RightAns, int Point,int level,boolean trueFalseQues,boolean ifOpenQues )
     { 
         setQuestion(Ques);
         setEnswer(ans);
         setRightAns(RightAns);
         setPoint(Point);
         setTrueFalseQues(trueFalseQues);
         setOpenQues(ifOpenQues);
         setLevel(level);
       
     }

  
          public Question(String Ques,String[] ans,String openAnser, int Point,int level,boolean trueFalseQues,boolean ifOpenQues )
     { 
         setQuestion(Ques);
         setEnswer(ans);
         setOpenAnser(openAnser);
         setPoint(Point);
         setTrueFalseQues(trueFalseQues);
         setOpenQues(ifOpenQues);
         setLevel(level);
       
     }

    public boolean isOpenQues() {
        return openQues;
    }

      public String getOpenAnser() {
        return openAnser;
    }

    public void setOpenAnser(String openAnser) {
        this.openAnser = openAnser;
    }
    
    public void setOpenQues(boolean openQues) {
        this.openQues = openQues;
    }

    public boolean isTrueFalseQues() {
        return trueFalseQues;
    }

    public void setTrueFalseQues(boolean trueFalseQues) {
        this.trueFalseQues = trueFalseQues;
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

    public int getRightAns() {
        return rightAns;
    }

    public void setRightAns(int rightAns) {
        this.rightAns = rightAns;
    }
    
    public boolean checkAnswer(int userAns) throws Exception
    {
        if(userAns<1||userAns>4)
            throw new Exception("***invalid input has chosen! please try agian***\n");
        if(userAns==rightAns)
            return true;
        else 
            return false;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String[] getEnswer() {
        return enswer;
    }
    public void setEnswer(String[] enswer) {
        this.enswer = enswer;
    }
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point+= point;
    } 
    public void printQes(){
        
        System.out.println(getQuestion());
        
           for(int j=0;j<4;j++){
            System.out.println(j+1+")" +this.enswer[j]);   
        }
       
        
        
    }
    
}
