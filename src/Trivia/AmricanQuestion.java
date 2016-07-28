
package Trivia;

/**
 * This class define the pattern of American question 
 * 
 * @author Yosef
 */
public class AmricanQuestion extends Question{
    /**
     * 
     */
    private String [] answer;
    /**
     * 
     */
    private int rightAns;
   
    /**
     * This is the constructor of the class
     * 
     * @param ans
     * @param rightAns
     * @param Ques
     * @param Point
     * @param level
     */
    public AmricanQuestion(String []ans,int rightAns,String Ques,int Point,int level){
        super(Ques,Point,level);
         setAnswer(ans);
         setRightAns(rightAns);
     }
     
    /**
     * This method return the right answer
     * 
     * @return String array of the possible answer
     */
    @Override
    public String[] getAnswer() {
        return answer;
    }

    /**
     * This method set possible answer
     * 
     * @param answer
     */
    public void setAnswer(String[] answer) {
        this.answer = answer;
    }
    
    /**
     * This method return the right answer
     * 
     * @return
     */
    public int getRightAns() {
        return rightAns;
    }

    /**
     * This method set the right answer
     * 
     * @param rightAns
     */
    public void setRightAns(int rightAns) {
        this.rightAns = rightAns;
    }

    
}
