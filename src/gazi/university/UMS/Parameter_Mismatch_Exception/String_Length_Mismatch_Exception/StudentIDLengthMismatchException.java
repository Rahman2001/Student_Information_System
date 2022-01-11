package gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception;

import gazi.university.Person_SubClasses.Employee_SubClasses.AdministrativeStaff;

public class StudentIDLengthMismatchException extends Exception{
    public StudentIDLengthMismatchException(String message){
        super(message);
        int hashNumber = hashCode();
        AdministrativeStaff bashkan = new AdministrativeStaff("12345678911", "Rahman Rejepov",
                "1234567891123");
        bashkan.addError("Exception type: " + this.getClass().getPackageName() + " => " +
                "exception detail: " + this.getClass().getSimpleName() + " of number: " + hashNumber);
    }
}
