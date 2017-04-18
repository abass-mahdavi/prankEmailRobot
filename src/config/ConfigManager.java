package config;


import models.mail.Person;
import models.mail.Group;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 * @author amh
 * 
 * code adapted from : https://www.mkyong.com/java/java-properties-file-examples/
 */
public class ConfigManager {
    private String smtpServerAddress;
    private int smtpServerPort;
    private ArrayList<Person> to; //victim receivers
    private ArrayList<Person> cc; //victims to cc
    private ArrayList<String> messages;
    private int numberOfGroups;
    
    public ConfigManager()throws IOException{         
        propertiesLoad("src/config/config.properties");
        receiversLoad("src/config/receivers.utf8");
        messagesLoad("src/config/messages.utf8");  
    }
    
    private void propertiesLoad(String propertiesFilesName)throws IOException{
        FileInputStream propertiesFile = new FileInputStream(propertiesFilesName);
        Properties properties = new Properties();
        properties.load(propertiesFile);
        smtpServerAddress = properties.getProperty("smtpServerAddress");
        smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerport"));
        numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups")); 
        String[] ccArray = properties.getProperty("witnessToCC").split(",");        
        Group ccGroup = new Group(ccArray);
        cc = ccGroup.getPersons();          
    }
    
    private void receiversLoad(String receiversFileName) throws IOException {
        to = new ArrayList<Person>();
        FileInputStream receiversFile = new FileInputStream(receiversFileName);
        InputStreamReader receiversReader = new InputStreamReader(receiversFile, "UTF-8");
        BufferedReader receiversBuffer = new BufferedReader(receiversReader);
        String email;
        do {
            email = receiversBuffer.readLine();
            if (email == null)
                break;
            to.add(new Person(email));
        } while (email != null);
    }    
    
    private void messagesLoad(String mesagesFileName) throws IOException {
        //http://stackoverflow.com/a/16999974
        //puts all the file in a single string        
        String temp = new String(Files.readAllBytes(Paths.get(mesagesFileName)));
        String allMessages = stripAccents(temp); // only if you use mockmock or an non UTF-8 compatible SMPT server
        
        //then we split the String into an ArrayList of String
        //in the file messages.utf8 we have used "=_=_=" as seperator
        messages = new ArrayList<String>(Arrays.asList(allMessages.split("=_=_=\n")));
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public ArrayList<Person> getTo() {
        return to;
    }

    public ArrayList<Person> getCc() {
        return cc;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }   
    
    //helper just for mockmock server
    //http://stackoverflow.com/a/15190787
    //mockmock server is not utf-8 compatible
    //mockmock server is not compatible with \' and \"
    public static String stripAccents(String s) 
{
    s = Normalizer.normalize(s, Normalizer.Form.NFD);
    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    s= s.replace('\'', '\u0020');
    s= s.replace('\"', '\u0020');
    s= s.replace('’', '\u0020');
    s= s.replace('-', '\u0020');
    s= s.replace('«', '\u0020');
    s= s.replace('»', '\u0020');
    return s;
}
}
