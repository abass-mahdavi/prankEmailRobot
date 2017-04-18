package SMTP;


import java.io.IOException;
import models.mail.Message;

/**
 *
 * @author amh
 */
public interface ISmtpClient {
    public void sendMessage (Message message) throws IOException;    
}
