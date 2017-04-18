package models.prank;

import config.ConfigManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import models.mail.Group;
import models.mail.Person;

/**
 *
 * @author amh
 */
public class PrankGenerator{
    private ConfigManager configManager;
    final int MinimumVictimsPerGroup = 3;
    final int numberOfGroups ;
    
    public PrankGenerator (ConfigManager configManager){
        this.configManager = configManager;
        numberOfGroups = configManager.getNumberOfGroups();
    }
    
    public Prank[] generatePranks() throws IOException{
        Prank[] pranks= new Prank[numberOfGroups];
        Group[] groups = generateGroups();
        ArrayList<String> messages = configManager.getMessages();        
        // mix the messages
        Collections.shuffle(messages, new Random(System.nanoTime())); 
        
        for (int i = 0; i < numberOfGroups; i++){
            pranks[i] = new Prank();
            ArrayList<Person> victims = groups[i].getPersons();
            //first victim of the group will be the sender others will be receivers            
            pranks[i].setSender(victims.get(0));
            pranks[i].setReceivers(new ArrayList<Person>(victims.subList(1, victims.size())));            
            //add list of witnesses
            pranks[i].setWitnesses(configManager.getCc());
            //choose one message (message list previously shuffled)
            String message = messages.get(i%messages.size());
            //lets extract the message subject http://stackoverflow.com/questions/23756456/java-separate-string-according-to-first-line-break
            //String lineSeparator = System.lineSeparator();
            String subject = message.substring(0, message.indexOf("\n"));            
            
            String tempo = message.substring(message.indexOf("\n")+1, message.length());
            //System.out.println(tempo);
            //now let's sign the selected message
            message = "\n" + tempo + "\n" + victims.get(0).getFirstName();
            pranks[i].setSubject(subject);
            pranks[i].setMessage(message);
        }      
        return pranks;
    }
    
    private Group[] generateGroups()throws IOException{  
        Group[] groups = new Group[numberOfGroups];
        for (int g = 0; g < numberOfGroups; g++){
            groups[g] = new Group();
        }
        
        
        
        ArrayList<Person> victims = configManager.getTo();
        
        
        
        int numberOfVictims = victims.size(); // gets the total number of receivers victims
        int vpg = numberOfVictims / numberOfGroups; 
        // the minumum number of receivers victim per group should not be 
        // lower then MinimumVictimsPerGroup
        if(vpg < MinimumVictimsPerGroup){
            IOException e = new IOException("number of victims per group = " + vpg +
                                            " is below minimum authorized : " +
                                             MinimumVictimsPerGroup);
            throw(e);
        }
        
        // mix the victim recepiants
        Collections.shuffle(victims);        
        for (int i=0 ; i < numberOfVictims; i++){
            groups[i%numberOfGroups].addPerson(victims.get(i));
        }

        return groups;
    }
}
