/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg170412labosmtp03;


import config.ConfigManager;
import java.io.IOException;

/**
 *
 * @author amh
 */
public class MainLauncher {
    public static void mainrrr(String... args){
        
        /*
        Message message = new Message();
        message.setTo(new String[]{"aa@aa.cn", "bb@bb.cn, cc@cc.cn"});
        message.setCc(new String[]{"dd@aa.cn", "ee@bb.cn, ff@cc.cn"});
        message.setFrom("gg@g.cn");
        message.setSubject("S2MMM");
        message.setText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\nCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        
        try{
        SmtpClient client = new SmtpClient("localhost", 2525);
        client.sendMessage(message);
        }catch (IOException e){
            
        }
        */
        try{
        System.out.println("Hello");
        System.out.println("Hello");    
        ConfigManager m = new ConfigManager();
        System.out.println(m.getMessages());
        System.out.println(m.getTo());
        System.out.println(m.getSmtpServerAddress());
        System.out.println(m.getSmtpServerPort());
        System.out.println(m.getCc());
        System.out.println(m.getNumberOfGroups());
        System.out.println("Hello");
        System.out.println("Hello");
        
        }catch (IOException e){e.printStackTrace();}
        
        System.out.println("Hello");
    }
}
