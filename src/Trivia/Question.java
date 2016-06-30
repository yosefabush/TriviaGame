
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
    
    /**
     *
     * @param Ques
     * @param Point
     * @param Level
     */
    public Question(String Ques,int Point,int Level)
    { 
        setQuestion(Ques);
        setPoint(Point);
        setLevel(Level);
     }
    
    /**
     *
     * @return
     */
    public boolean getCheekIfQuesWasAsked() {
        return cheekIfQuesWasAsked;
        
    }

    /**
     *
     * @param cheekIfQuesWasAsked
     */
    public void setCheekIfQuesWasAsked(boolean cheekIfQuesWasAsked) {
        this.cheekIfQuesWasAsked = cheekIfQuesWasAsked;
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
        this.level = level;
    }

    /**
     *
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     *
     * @return
     */
    public int getPoint() {
        return point;
    }

    /**
     *
     * @param point
     */
    public void setPoint(int point) {
        this.point+= point;
    }
    
    /**
     *
     * @return
     */
    public abstract String [] getAnswer();

    /**
     *
     * @return
     */
    public abstract int getRightAns();
    
}
