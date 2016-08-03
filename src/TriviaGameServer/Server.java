/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class Server
{
    static int  requestNumber = 1;
    static int  port = 55555;
    static ArrayList<ThreadHandler> threadArray=new ArrayList<ThreadHandler>();

	public static void main(String[] args) {

		
		
	 	System.out.println();
 		System.out.println("*************************************");
	 	System.out.println("Server side console");
	 	System.out.println("*************************************");
	 
        try {
        	
            ServerSocket serverSocket = new ServerSocket(port);
            
            while (true) {
            	
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conection number "+requestNumber);
                System.out.println("Creating thread for client connected from");
                ThreadHandler newThread = new ThreadHandler(clientSocket, requestNumber);
               // newThread.start();
                threadArray.add(newThread);
                 
                if((requestNumber%2)==0){  
                    threadArray.get(0).start();
                    threadArray.get(1).start();
                    System.out.println("2 Connection estblish ! strart play!");
                    //requestNumber=0;
                    //threadArray=null;
                    
                }
                requestNumber++;
            }            
        }        
        catch (IOException ioe) {
        	
        	ioe.printStackTrace();
        }
	}	

//    ServerSocket serverSocket;
//    boolean isConnected = false;
//    public final static int PORT = 2222;
//    private int maxPlayer =0;
//    static ArrayList<ConnectionThread> clients;
//     ArrayList<Socket> clientSockets=new ArrayList<Socket>();
//    static User []player1;
//    static int i=0;
//    ArrayList<PrintStream> thisClientOutputsArray=new ArrayList();
//    DataInputStream thisClientInputStream;
//    PrintStream thisClientOutputStream;
//    int payerScore;
//    boolean finish=false;
//     static int finishGameCnt=0;
//     int player1Score=0;
//     int player2Score=0;
//     String player1Name;
//     String player2Name;
//    
//    public static void main(String[] args) {
//        System.out.println("Start Conection!");
//        Server server;
//        try {
//            server = new Server();
//             server.handleRequests();
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//        
//    }
//    
//    // here we will have the list of connections threads
//    
//    public Server() throws IOException
//    {
//        
//        clients=new ArrayList<ConnectionThread>(2);
// 
//            serverSocket = new ServerSocket(PORT);
//             System.out.println("Listening on port " + PORT);
//             isConnected = true;
//        
//      
//       
//       
//    }
//    
//    // this method currently handles a single client
//    // to be updated to a loop with thread
//    public void handleRequests()
//    {
//        
//        while(isConnected&&(maxPlayer!=2))
//        {
//            try
//            {   Socket thisClient = serverSocket.accept();
//                clientSockets.add(thisClient);
//                thisClientInputStream = new DataInputStream(thisClient.getInputStream()); 
//		thisClientOutputStream = new PrintStream (thisClient.getOutputStream());
//                //thisClientOutputsArray.add(thisClientOutputStream);
//                thisClientOutputStream.println("Conection sucseesful!");
//                ConnectionThread singleConnection = new ConnectionThread(clientSockets.get(i));
//                singleConnection.setName(""+ ++maxPlayer);
//                i++;
//                clients.add(singleConnection);
//                //singleConnection.start();
//                
//                
//            } catch (Exception ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//   
//        
//          if(twoPlayerConnected()){
//                 for(PrintStream p:thisClientOutputsArray)
//                    p.println("Statrt playe");
//                 //finish=true;
//          } 
//        try {
//            while(thisClientInputStream.readBoolean()){
//                finishGameCnt++;
//                try {
//                    if(finishGameCnt==1){
//                    player1Score=thisClientInputStream.read();
//                    player1Name=thisClientInputStream.readLine();
//                    System.out.println(player1Name+" is connected whit "+player1Score+" Score");
//                    }
//                    else{
//                    player2Score=thisClientInputStream.read();
//                     player2Name=thisClientInputStream.readLine();
//                     System.out.println(player2Name+" is connected whit "+player2Score+" Score");
//                    }
//                    
//                } catch (IOException ex) {
//                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                if(finishGameCnt==2)
//                    break;
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        /*  try {
//            if(input.readBoolean()){
//                finishGameCnt++;
//                player1Score=input.read();
//                player1Name=input.readLine();
//                System.out.println(player1Name+" is connected whit "+input.read()+" Score");
//                
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            if(input.readBoolean()){
//                finishGameCnt++;
//                player2Score=input.read();
//                player2Name=input.readLine();
//                System.out.println(player2Name+" is connected whit "+input.read()+" Score");
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }*/
//          
//          
//         if(finishGameCnt==2){
//            if(player1Score>player2Score)
//                 System.out.println("player 1 "+player1Name+" Won! and is score is: "+player1Score);
//            else if(player1Score<player2Score)
//                System.out.println("player 2 "+player2Name+" Won! and is score is: "+player2Score);
//            else
//                System.out.println("Equal socre!");
//         }
//
//        
//    }
//    public boolean twoPlayerConnected(){
//        if(clients.size()==2){
//             System.out.println("two Connection estblish SATRT PLAY!");                
//                   return true; 
//            }
//        return false;
//    }
//    

  




}
   
   
