/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg170412labosmtp03;

import SMTP.SmtpClient;
import config.ConfigManager;
import java.io.IOException;
import models.mail.Message;
import models.prank.Prank;
import models.prank.PrankGenerator;

/**
 *
 * @author amh
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        ConfigManager configManager = new ConfigManager();
        Prank[] pranks = (new PrankGenerator(configManager)).generatePranks();
        for (Prank p : pranks){
            Message message = new Message();
            message.setTo(p.getToEmails());
            message.setCc(p.getCcEmails());
            message.setFrom(p.getSender().getEmail());
            message.setSubject(p.getSubject());
            message.setText(p.getMessage());
            
            System.out.println(p.getSubject() + "\n" + p.getMessage());
            
            
            try {
                SmtpClient client = new SmtpClient(
                        configManager.getSmtpServerAddress(), 
                        configManager.getSmtpServerPort());
                client.sendMessage(message);
            } catch (IOException e) {
            e.printStackTrace();}            
        }
    }    
}
