/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaGameServer;

import Trivia.User;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * this class is server side wait each time for 2 client connections then create
 * threads for them to communicate with each other and get back to wait for more
 * 2 clients
 *
 * @author Yosef
 */
public class Server {

    static ArrayList<User> userArr = new ArrayList<User>();
    static int requestNumber = 1;
    public static int port = 55555;
    public static boolean serverIsActivate = false;
    static ArrayList<ThreadHandler> threadArray = new ArrayList<ThreadHandler>();

    public static void main(String[] args) {
        int threadNumber = 1;
        serverIsActivate = true;

        // print out a welcome message
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("Server On Line");
        System.out.println("*******************************************************");

        try {
            // open a server socket
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                // accept first client connection
                Socket client1Socket = serverSocket.accept();
                System.out.println("Client 1 connected ");
                // accept second client connection
                Socket client2Socket = serverSocket.accept();
                System.out.println("Client 2 connected ");

                // create threads
                // send client sockets to new threads, each time with a different socket as the source one
                Thread client1Thread = new ThreadHandler(client1Socket, client2Socket, threadNumber++);
                Thread client2Thread = new ThreadHandler(client2Socket, client1Socket, threadNumber++);
                // start the threads
                client1Thread.start();
                client2Thread.start();

                System.out.println("Clients Connected!");
                System.out.println();

                System.out.println("********************************");
                System.out.println("Waiting for more connections...");
                System.out.println("********************************");
                // get back to listen and wait for connections
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

}
