package file_access_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import file_access_server.FileWriting;

public class ClientGui44 extends JFrame 
{
    private JLabel lblTitle = new JLabel("Common File Access");
    private JLabel lblUName = new JLabel("Name:");
    private JLabel lblUText = new JLabel("your comment:");
    private JTextArea txtUserText = new JTextArea(10, 20);
    
    private JTextField txtUserName = new JTextField(10);
    
    private JButton btnSend = new JButton("Send");
    private JButton btnShow = new JButton("show");
    private JTextArea fileContent = new JTextArea(10, 20);
    // private JButton btnOpenFile = new JButton("open file");

    Client client;

    public ClientGui44() throws HeadlessException
    {
        client = new Client();
        JPanel pMain = new JPanel();
    
        pMain.setLayout(new GridLayout(0, 1));
        txtUserName.setBorder(null);
        //fileContent.setLocationRelativeTo(null);
        JPanel p0 = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        btnSend.setSize(100, 3);
        //p1.setLayout(new GridLayout(1, 1));
        //pMain.add(btnOpenFile);
        p0.add(lblTitle);
        p1.add(lblUName);
        p1.add(txtUserName);
        p2.add(lblUText);
        p2.add(txtUserText);
        p3.add(btnSend);
        p3.add(btnShow);
        p4.add(fileContent);
        p2.setLayout(new FlowLayout());
        // p3.setLayout(new GridLayout(1, 1));
        setContentPane(pMain);
        setTitle("Client");
        setSize(600, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pMain.add(p0);
        pMain.add(p1);
        pMain.add(p2);
        pMain.add(p3);
        pMain.add(p4);
        btnSend.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                FileWriting f = new FileWriting(txtUserName.getText(), txtUserText.getText());

                try {
                    client.writeToserver(f);
                    JOptionPane.showMessageDialog(null, "text added");
                    txtUserText.setText("");
                    txtUserName.setText("");

                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ClientGui44.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        btnShow.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
              try
               {
                  client.writeToserver(null);
                  String text = client.readFromserver();
                  JOptionPane.showMessageDialog(null, text);
                }
              catch(InterruptedException ex)
              {
              }  
               catch (IOException ex) 
                {
                    Logger.getLogger(ClientGui44.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       

}
}