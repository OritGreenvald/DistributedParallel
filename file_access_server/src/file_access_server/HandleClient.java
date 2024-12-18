package file_access_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import file_access_server.FileWriting;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.io.File;

public class HandleClient implements Runnable
{

    Socket socket;
    private ObjectInputStream inputFromClient;
    private ObjectOutputStream outputToClient;

    public HandleClient(Socket socket) 
    {
        this.socket = socket;

    }

    @Override
    public void run()
    {
        FileWriting f;
        try 
        {
            inputFromClient = new ObjectInputStream(socket.getInputStream());
            outputToClient = new ObjectOutputStream(socket.getOutputStream());
            while (true)
            {
                try 
                {
                    f = (FileWriting) inputFromClient.readObject();
                    if (f != null)
                    {
                        WriteToFile(f);
                    } 
                    else
                    {
                        outputToClient.writeObject(GetFileText());
                    }
                }
                catch (Exception e) 
                {
                    Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } 
        catch (IOException ex)
        {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized String GetFileText() throws InterruptedException 
    {
        try 
        {
            synchronized (Server.file) 
            {
                FileReader fileReader = new FileReader(Server.file);
                String text = "";
                int i;
                while ((i = fileReader.read()) != -1) 
                {
                    text += ((char) i);
                }
                fileReader.close();
                while (text.equals(""))
                {
                    Server.file.wait();
                    FileReader fileReader2 = new FileReader(Server.file);
                    while ((i = fileReader2.read()) != -1) 
                    {
                        text += ((char) i);
                    }
                    fileReader2.close();
                }
                return text;
            }
        }
        catch (FileNotFoundException ex) 
        {
            return "file not found";
        } 
        catch (IOException ex)
        {
            return "io exception";
        }
    }

    public synchronized void WriteToFile(FileWriting f)
    {
        try
        {
            synchronized (Server.file) 
            {
                DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                Date date = new Date();
                FileWriter myWriter = new FileWriter(Server.file, true);
                myWriter.write(dateFormat.format(date) + " " + f.userName + ": " + f.content + "\n");
                myWriter.close();
                Server.file.notifyAll();
            }

        } 
        catch (IOException e) 
        {

        }
    }
}
