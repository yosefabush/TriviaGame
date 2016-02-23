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
public class OpenQuestion extends Question{
 
     private String answer;
     private String []answer2=new String[2];
     
     
     
 public OpenQuestion(String openAns,String rightAns,String Ques,int Point,int level){
      super(Ques,Point,level);
      setAnswer(openAns);
      this.answer2[0]=answer;
      
      
 }
 
    @Override
    public int getRightAns(){
        return 0;
    }
 
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    @Override
    public String []getAnswer(){
     return answer2;  
    }
 
    
    public String getPosibaleAnswer(){
        return answer;
    }




   

  
    
}
