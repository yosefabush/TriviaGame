/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class Server 
{
    ServerSocket serverSocket;
    boolean isConnected = false;
    public final static int PORT = 2222;
    private int maxPlayer =0;
    static ArrayList<ConnectionThread> clients;
    
    public static void main(String[] args) {
        System.out.println("Start Conection!");
        Server server=new Server();
        server.handleRequests();
        
    }
    
    // here we will have the list of connections threads
    
    public Server()
    {
        
        clients=new ArrayList<ConnectionThread>(2);
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Listening on port " + PORT);
       isConnected = true;
       
    }
    
    // this method currently handles a single client
    // to be updated to a loop with thread
    public void handleRequests()
    {
        
        while(isConnected&&maxPlayer!=2)
        {
            try
            {      
                Socket clientSocket = serverSocket.accept();
                DataInputStream input = new DataInputStream(clientSocket.getInputStream()); 
		PrintStream output = new PrintStream (clientSocket.getOutputStream());
                output.println("Conection sucseesful!");
                ConnectionThread singleConnection = new ConnectionThread(clientSocket);
                singleConnection.setName(""+ ++maxPlayer);
                clients.add(singleConnection);
                singleConnection.start();
                
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          twoPlayerConnected();  
        
    }
    public boolean twoPlayerConnected(){
        if(clients.size()==2){
               
                System.out.println("two Connection estblish SATRT PLAY!");
                
                //clients.get(1).startGame(clients.get(0));
                //clients.get(1).startGame(clients.get(1));
                //Game newGame=new Game(numQues,current,true,level);
                
                   return true; 
            }
        return false;
    }

   
    
}
