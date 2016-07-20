/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;


import TriviaGameServer.ConnectionData;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class ConnectionThread extends Thread
{
    Socket socket;
    InputStream input;
    ObjectInputStream ois;
    OutputStream output;
    ObjectOutputStream oos;
    int userId;
    int otherPlayerId;
     static int cnt=0;
    
    public ConnectionThread(Socket socket)
    {
        this.socket = socket;
        if(cnt==0)
            userId = 0;

        otherPlayerId = (userId==1)?2:1;
    }
    
    @Override
    public void run()
    {
        try {
            output = socket.getOutputStream();
            oos = new ObjectOutputStream(output);
            input = socket.getInputStream();
            ois = new ObjectInputStream(input);
           
           ConnectionData requestData = (ConnectionData)ois.readObject();
            switch(requestData.getRequestCode())
            {
                
                case ConnectionData.START_GAME:            
                    ConnectionThread otherPlayer = Server.clients.get(otherPlayerId);
                    otherPlayer.passTurn();
                    break;
                case ConnectionData.PASS_TURN:  
                                               break;
                                              
            }
        
        } catch (Exception ex) {
            Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void passTurn() {
        try {
            ConnectionData responseData = new TriviaGameServer.ConnectionData();
            responseData.setRequestCode(ConnectionData.PASS_TURN); 
            oos.writeObject(responseData);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean startGame(ConnectionThread conection){
        
        return true;
    }
}
