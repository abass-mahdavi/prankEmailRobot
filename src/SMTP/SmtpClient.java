package SMTP;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;
import models.mail.Message;

/**
 *
 * @author amh
 */
public class SmtpClient implements ISmtpClient{
    
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    
    private String smtpServerAddress;
    private int   smtpServerPort;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    
    public SmtpClient(String smtpServerAddress, int smtpServerPort ) throws IOException{
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
        socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        reader.readLine();
        writer.println("EHLO THERE");
        writer.flush();
        
        while(reader.readLine().substring(0, 5) == "250-"){}
        writer.println("MAIL FROM: anything@anything.cn" );
        writer.flush();
        
        reader.readLine(); 
        writer.println("RCPT TO: something@something.cn");
        writer.flush();
        
        reader.readLine();
        writer.println("DATA");
        writer.flush();
        
        reader.readLine();
        writer.println("from: " + message.getFrom());
        writer.flush();
        
        String to = "";
        for (String t : message.getTo()){
            to += t + ", ";
        }
        to = to.substring(0, to.length()-2);
        writer.println("to: " + to);
        writer.flush();
                
        String cc = "";
        for (String c : message.getCc()){
            cc += c + ", ";
        }
        cc = cc.substring(0, cc.length()-2);        
        writer.println("cc: " + cc);
        writer.flush();   

        writer.println("SUBJECT: " + message.getSubject());
        writer.flush();
        
        writer.println(message.getText());
        writer.flush();
        
        writer.println(".");
        writer.flush();
        
        reader.readLine();
        writer.println("quit: ");
        writer.flush();        
        
        reader.readLine();
        socket.close();
    }
    
}
