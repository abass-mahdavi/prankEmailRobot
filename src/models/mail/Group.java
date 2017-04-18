package models.mail;

import java.util.ArrayList;

/**
 *
 * @author amh
 */
public class Group {
    public ArrayList<Person> persons; 
    
    public Group(){
        persons = new ArrayList<>();
    }
    
    public Group (String[] emails){
        persons = new ArrayList<Person>();
        for (String email : emails){
            persons.add(new Person(email));
        }
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
    
    public void addPerson (Person person){
        persons.add(person);
    }
    
    @Override
    public String toString(){
        String result = "";
        for (Person p : persons){
            result = result + " - " + p.toString();
        }
         return result;   
    }
        

}
