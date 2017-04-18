package models.mail;

/**
 *
 * @author amh
 */
public class Person {    
    private String firstName, lastName, email; //first name, last name, email address
    
    public Person(String firstName, String lastName, String email){        
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public Person(String email){  
        this.email = email;       
        String[] temp = email.split("@");
        String[] names = temp[0].split("\\.");
        firstName = names[0];        
        if (names.length >= 2){            
            lastName  = names[1];
        } else {
            lastName = "";
        }
    }
    
    public String getFirstName() {
        return firstName;
    }    
    
    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }    
    
    @Override
    public String toString(){
        return firstName + "  " + lastName + "  " + email;
    }
}
