/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;

import Trivia.User;
import static TriviaGameServer.Server.threadArray;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yosef
 */
public class ThreadHandler extends Thread {

    // socket number
    Socket clientSocket;
    // thread id
    int threadNumber;
    DataInputStream input ;
    PrintStream output;

    public ThreadHandler(Socket socket, int thread) {

        // initialize upon creation
        clientSocket = socket;
        threadNumber = thread;
    }

    public void run() {

        try {
            // getting the input stream from which the server can read from
            // the output of the client will become the input of the server 
             input = new DataInputStream(clientSocket.getInputStream());
           
            // getting the output stream to which the server can write to
            // the output of the server will become the input of the client
             output = new PrintStream(clientSocket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
             
            boolean moreDataAvailable = true;

           while (moreDataAvailable) {
               //if((threadNumber%2)==0){
                   System.out.println("Send reuest to client");
                   output.println("Start");
                  
               
                User inputNextLine=null;
                 try {
                     inputNextLine = (User) ois.readObject();
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
                      moreDataAvailable = false;
                 }

               // if (inputNextLine == null) {
              //      moreDataAvailable = false;
              //  } else {
                  //  output.println("Start");

                    // trim() removes whitespace in order to catch "Exit ", " Exit", etc.
                    //if (inputNextLine.equals("FinishGame")) {
                        System.out.println("User "+inputNextLine.getUserName()+" Score: "+inputNextLine.getPoints());
                       
                        moreDataAvailable = false;
                    
              //  }
            }
            clientSocket.close();
        } catch (IOException ioe) {

            ioe.printStackTrace();
        }
    }
    
}
