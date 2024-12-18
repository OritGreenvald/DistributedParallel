package file_access_project;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import file_access_server.FileWriting;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s-sys
 */
public class Client {
    //ערוץ תקשורת
    private Socket socket;
    // קריאה  של אוביקט לשרת
    private static ObjectOutputStream toServer;
    // כתיבה של אוביקט לשרת
    private static ObjectInputStream fromserver; 
    //constructor
    public Client()
    {
        try 
        {
            //כתובת ipconfig  ,באיזה port ירוץ
            socket=new Socket("localhost",7000);
            //היכן לכתוב או לקרא
            toServer=new ObjectOutputStream(socket.getOutputStream());
            fromserver=new ObjectInputStream(socket.getInputStream());
            System.out.println("success...");
        } 
        catch (IOException ex) 
        {
           Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //כתיבה לשרת
    public void writeToserver(FileWriting f) throws IOException
    {
       toServer.writeObject(f);   
       toServer.flush();
    } 
    //קריאה מהשרת
    public String readFromserver() throws InterruptedException
    {
        try 
        { 
            String str=(String)fromserver.readObject();
           return str;
        }  
        catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return "";
    }   
}
