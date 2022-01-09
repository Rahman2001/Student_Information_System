package gazi.university;

public class PersonNameTooLongException extends Exception{
    public PersonNameTooLongException(String message){
        super(message);
    }
}
