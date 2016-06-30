
package Trivia;

/**
 *
 * @author Yosef
 */
public class AmricanQuestion extends Question{
  
    private String [] answer;
    private int rightAns;
   
    /**
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
     *
     * @return
     */
    @Override
    public String[] getAnswer() {
        return answer;
    }

    /**
     *
     * @param answer
     */
    public void setAnswer(String[] answer) {
        this.answer = answer;
    }
    
    /**
     *
     * @return
     */
    public int getRightAns() {
        return rightAns;
    }

    /**
     *
     * @param rightAns
     */
    public void setRightAns(int rightAns) {
        this.rightAns = rightAns;
    }

    
}
