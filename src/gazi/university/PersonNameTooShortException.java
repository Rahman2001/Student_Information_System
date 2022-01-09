package gazi.university;

public class PersonNameTooShortException extends Exception{
    public PersonNameTooShortException(String message){
        super(message);
    }
}
