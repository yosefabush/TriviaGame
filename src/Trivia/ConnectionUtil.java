/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trivia;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yosef
 */
public class ConnectionUtil {
    // the ip address of the server hosting the java server project
    public static final String HOST_ADDRESS = "127.0.0.1";
    public static final int HOST_PORT = 2222;
    
    Socket clientSocket;
    OutputStream output;
    ObjectOutputStream oos;
    InputStream input;
    ObjectInputStream ois;
    
    
   
    /*
     * When opening the connection we prepare both ways of interaction
     * input and output
     */
    public void openConnection()
    {
        try {
            clientSocket = new Socket(HOST_ADDRESS, HOST_PORT);
            output = clientSocket.getOutputStream();
            oos = new ObjectOutputStream(output);
            
            input = clientSocket.getInputStream();
            ois  = new ObjectInputStream(input);

        } catch (UnknownHostException ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    public void closeConnection()
    {
        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
