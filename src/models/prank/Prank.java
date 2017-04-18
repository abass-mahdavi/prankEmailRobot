
package models.prank;

import models.mail.Person;
import java.util.ArrayList;

/**
 *
 * @author amh
 */
public class Prank {
    private Person sender;
    private ArrayList<Person> receivers;
    private ArrayList<Person> witnesses;
    private String message;
    private String subject;
    
    public Prank(){
        receivers = new ArrayList<Person>();
        witnesses = new ArrayList<Person>();
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void addReceiver(Person r){
        receivers.add(r);
    }
    
    public void addWiteness(Person w){
        witnesses.add(w);
    }

    public void setReceivers(ArrayList<Person> receivers) {
        this.receivers = receivers;
    }

    public void setWitnesses(ArrayList<Person> witnesses) {
        this.witnesses = witnesses;
    }    

    public ArrayList<Person> getReceivers() {
        return receivers;
    }

    public ArrayList<Person> getWitnesses() {
        return witnesses;
    }
    
    public String[] getToEmails(){
        String[] to = new String[receivers.size()];
        for (int p = 0; p< receivers.size(); p++){
            to[p] = receivers.get(p).getEmail();
        }
        return to;
    }
    
    public String[] getCcEmails(){
        String[] cc = new String[witnesses.size()];
        for (int p = 0; p< witnesses.size(); p++){
            cc[p] = witnesses.get(p).getEmail();
        }
        return cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    
}
