package file_access_server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author s-sys
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Server
{
    ServerSocket serversocket;
    static File file;
    public Server() 
    {
        
        try
        {
            serversocket=new ServerSocket(7000);
             file = new File("C:\\מקבילי מבוזר סופי\\commonFile.txt");
             FileWriter myWriter = new FileWriter(Server.file, true);
             myWriter.write("");
             myWriter.close();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
   public Socket Accept()
   {
        try 
        {
            return serversocket.accept();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
   }  
}

