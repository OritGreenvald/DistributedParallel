package file_access_server;

import java.awt.BorderLayout;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerGui extends JFrame
{
    Server server = new Server();
    JTextArea txta = new JTextArea();
    
    public ServerGui()
    {
        setLayout(new BorderLayout());
        add(new JScrollPane(txta), BorderLayout.CENTER);
        setTitle("MultiThreadServer");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!  

        txta.append("MultiThreadServer started at " + new Date() + '\n');

        while (true)
        {
            Socket socket = server.Accept();
            txta.append("starting thread for  client\n");
            InetAddress address = socket.getInetAddress();
            txta.append("Client host name is" + address.getHostName() + "\n");
            txta.append("Client IP Address is" + address.getHostAddress() + "\n");
            Thread task = new Thread(new HandleClient(socket));
            task.start();

        }

    }
    public static void main(String[] args) 
    {

        new ServerGui().setVisible(true);

    }
}
