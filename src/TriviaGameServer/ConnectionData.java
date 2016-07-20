/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;

/**
 *
 * @author Yosef
 */
public class ConnectionData {
        public static final int FINISH_TURN = 1;
        public static final int PASS_TURN = 2;
        public static final int START_GAME=3;

        private int requestCode;

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
        
     public int getRequestCode() {
        return requestCode;
    }
}
