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
public class AmricanQuestion extends Question{
  
    private String [] answer;
    private int rightAns;
   
    
    public AmricanQuestion(String []ans,int rightAns,String Ques,int Point,int level){
        super(Ques,Point,level);
         setAnswer(ans);
         setRightAns(rightAns);
     }
     

    @Override
    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }
    
    public int getRightAns() {
        return rightAns;
    }

    public void setRightAns(int rightAns) {
        this.rightAns = rightAns;
    }

    
}
