/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;

import Trivia.FormClass;
import Trivia.SelectGame;
import Trivia.TotalSummry;
import Trivia.User;
import Trivia.Loading;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yosef
 */
public class ThreadHandler extends Thread {

    Socket client1Socket;
    Socket client2Socket;
    int threadID;
    public static boolean gameOver = false;
    static Loading load3 = new Loading();

    public ThreadHandler(Socket socket1, Socket socket2, int thread) {
        // initialize sockets of 2 clients upon creation of the thread
        client1Socket = socket1;
        client2Socket = socket2;
        threadID = thread;
    }

    public void run() {
        try {

            ObjectOutputStream out = new ObjectOutputStream(client1Socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client1Socket.getInputStream());
            //  ObjectOutputStream out2 = new ObjectOutputStream(client2Socket.getOutputStream());
//            ObjectInputStream in2 = new ObjectInputStream(client2Socket.getInputStream());

            out.writeObject("Start");
            // out2.writeObject("Start");
            System.out.println("befor while");

           
                User u1 = (User) in.readObject();
                System.out.println("user " + u1.getUserName() + " Add!");

                Server.userArr.add(u1);
                if(Server.userArr.size()==1){
                    
                    load3.setVisible(true);
                }
                //this loop wating for 2 player to finish play
                while (Server.userArr.size() != 2) {

                }
                System.out.println("all trheads get out from the loop ");
                System.out.println("Befor cheek score");
                if (Server.userArr.get(0).getPoints() > Server.userArr.get(1).getPoints()) {
                    // System.out.println(" " + Server.userArr.get(0).getUserName() + " won!");
                    load3.dispose();
                    out.writeObject("you Lose");
                    TotalSummry total = new TotalSummry(Server.userArr.get(0),"You Win",true);
                    total.setVisible(true);
                    
//                   out.writeObject("you Win");
                } else if (Server.userArr.get(0).getPoints() < Server.userArr.get(1).getPoints()) {
                    //System.out.println(" " + Server.userArr.get(1).getUserName() + " won!");
                    load3.dispose();
                    out.writeObject("You Win");
                    TotalSummry total = new TotalSummry(Server.userArr.get(0),"You Lose",true);
                    total.setVisible(true);
                  
//                    out.writeObject("You Lose");
                } else {
                    
                    out.writeObject("EQUL!! The bothe Score indentical!");
                    // out2.writeObject("EQUL!! The bothe Score indentical!");
                }
                gameOver = true;
            } catch (IOException ex) {   
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }   
        

       
        try {
            client1Socket.close();
            client2Socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }

    public static boolean sendToClientGameOver() {
        if (gameOver) {
            return true;
        } else {
            return false;
        }
    }

}
