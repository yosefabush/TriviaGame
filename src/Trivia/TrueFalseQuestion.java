
package Trivia;

/**
 *
 * @author Yosef
 */
public class TrueFalseQuestion extends Question{
  
    private String []answer;
    private int rightAns;
    
    /**
     *
     * @param ans
     * @param rightAns
     * @param Ques
     * @param Point
     * @param level
     */
    public TrueFalseQuestion(String []ans,int rightAns,String Ques,int Point,int level){
     super(Ques,Point,level);    
     setAnswer(ans);
     setRightAns(rightAns);
    
    }
    
    @Override
    public String[] getAnswer() {
        return answer;
    }

    /**
     *
     * @param trueFalseAns
     */
    public void setAnswer(String[] trueFalseAns) {
        
        this.answer = trueFalseAns;
        
    }

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
