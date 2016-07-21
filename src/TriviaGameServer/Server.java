/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;

import Trivia.User;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
     ArrayList<Socket> clientSocket=new ArrayList<Socket>();
    static User []player1;
    static int i=0;
    ArrayList<PrintStream> output=new ArrayList();
    DataInputStream input;
    int payerScore;
    boolean finish=false;
    
    
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
                 clientSocket.add(serverSocket.accept());
                 input = new DataInputStream(clientSocket.get(i).getInputStream()); 
		 output.add(new PrintStream (clientSocket.get(i).getOutputStream()));
                output.get(i).println("Conection sucseesful!");
                ConnectionThread singleConnection = new ConnectionThread(clientSocket.get(i));
                singleConnection.setName(""+ ++maxPlayer);
                i++;
                clients.add(singleConnection);
               // singleConnection.start();
                //User player =input.;
                
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
   
        
          if(twoPlayerConnected()){
                 for(PrintStream p:output)
                    p.println("Statrt playe");
                 finish=true;
          }  
          
          clientSocket=null;
         while(finish)
        {
          try
            {      
                 clientSocket.add(serverSocket.accept());
                 input = new DataInputStream(clientSocket.get(i).getInputStream()); 
		 output.add(new PrintStream (clientSocket.get(i).getOutputStream()));
                 output.get(i).println("Conection sucseesful!");
                 if(!clientSocket.isEmpty()){
                    try {
                        payerScore=input.readInt();
                        System.out.println(payerScore);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public boolean twoPlayerConnected(){
        if(clients.size()==2){
             System.out.println("two Connection estblish SATRT PLAY!");                
                   return true; 
            }
        return false;
    }

   
    
}
