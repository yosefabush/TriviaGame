
package Trivia;

/**
 *
 * @author Yosef
 */
public class OpenQuestion extends Question{
 
     private String answer;
     private String []answer2=new String[2];
     
    /**
     *
     * @param openAns
     * @param rightAns
     * @param Ques
     * @param Point
     * @param level
     */
    public OpenQuestion(String openAns,String rightAns,String Ques,int Point,int level){
      super(Ques,Point,level);
      setAnswer(openAns);
      this.answer2[0]=answer;
      
      
 }
 
    /**
     *
     * @return
     */
    @Override
    public int getRightAns(){
        return 0;
    }
 
    /**
     *
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String []getAnswer(){
     return answer2;  
    }
 
    /**
     *
     * @return
     */
    public String getPosibaleAnswer(){
        return answer;
    }




   

  
    
}
