package gazi.university.UMS.Student_Affairs_Exception;

import gazi.university.Person_SubClasses.Employee_SubClasses.AdministrativeStaff;
import gazi.university.Person_SubClasses.Student;

public class MissingGradeException extends Exception{
    public MissingGradeException(String message){
        super(message);
        int hashNumber = hashCode();
        AdministrativeStaff bashkan = new AdministrativeStaff("12345678911", "Rahman Rejepov",
                "1234567891123");
        bashkan.addError("Exception type: " + this.getClass().getPackageName() + " => " +
                "exception detail: " + this.getClass().getSimpleName() + " of number: " + hashNumber);
    }
}
