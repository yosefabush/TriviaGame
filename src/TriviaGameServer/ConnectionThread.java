/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;


import Trivia.Game;
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
//    Socket socket;
//    InputStream input;
//    ObjectInputStream ois;
//    OutputStream output;
//    ObjectOutputStream oos;
//    int userId;
//    int otherPlayerId;
//     static int cnt=0;
//    
//    public ConnectionThread(Socket socket)
//    {
//        this.socket = socket;
//        if(cnt==0)
//            userId = 0;
//
//        otherPlayerId = (userId==1)?2:1;
//    }
//    
//    @Override
//    public void run()
//    {
//        try {
//            output = socket.getOutputStream();
//            oos = new ObjectOutputStream(output);
//            input = socket.getInputStream();
//            ois = new ObjectInputStream(input);
//           
//           ConnectionData requestData = (ConnectionData)ois.readObject();
//            switch(requestData.getRequestCode())
//            {
//                
//                case ConnectionData.START_GAME:            
//                    ConnectionThread otherPlayer = Server.clients.get(otherPlayerId);
//                    otherPlayer.passTurn();
//                    break;
//                case ConnectionData.PASS_TURN:  
//                                               break;
//                                              
//            }
//        
//        } catch (Exception ex) {
//            Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//    public void passTurn() {
//        try {
//            ConnectionData responseData = new TriviaGameServer.ConnectionData();
//            responseData.setRequestCode(ConnectionData.PASS_TURN); 
//            oos.writeObject(responseData);
//        } catch (IOException ex) {
//            Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void startGame(ConnectionThread conection){
//      //  conection.ois;
//       // Game game=new Game(conection,true,Server.player1[0]);
//    }
    
     String line=null;
    BufferedReader  is = null;
    PrintWriter os=null;
    Socket s=null;

    public ConnectionThread(Socket s){
        this.s=s;
    }

    public void run() {
    try{
        is= new BufferedReader(new InputStreamReader(s.getInputStream()));
        os=new PrintWriter(s.getOutputStream());

    }catch(IOException e){
        System.out.println("IO error in server thread");
    }

    try {
        line=is.readLine();
        while(line.compareTo("QUIT")!=0){

            os.println(line);
            os.flush();
            System.out.println("Response to Client  :  "+line);
            line=is.readLine();
        }   
    } catch (IOException e) {

        line=this.getName(); //reused String line for getting thread name
        System.out.println("IO Error/ Client "+line+" terminated abruptly");
    }
    catch(NullPointerException e){
        line=this.getName(); //reused String line for getting thread name
        System.out.println("Client "+line+" Closed");
    }

    finally{    
    try{
        System.out.println("Connection Closing..");
        if (is!=null){
            is.close(); 
            System.out.println(" Socket Input Stream Closed");
        }

        if(os!=null){
            os.close();
            System.out.println("Socket Out Closed");
        }
        if (s!=null){
        s.close();
        System.out.println("Socket Closed");
        }

        }
    catch(IOException ie){
        System.out.println("Socket Close Error");
    }
    }//end finally
    }
}
