package gazi.university.UMS.Accounting_Exception;

import gazi.university.Person_SubClasses.Employee_SubClasses.AdministrativeStaff;

public class SalaryInitializationException extends Exception{
    public SalaryInitializationException(String message){
        super(message);
        int hashNumber = hashCode();
        AdministrativeStaff bashkan = new AdministrativeStaff("12345678911", "Rahman Rejepov",
                "1234567891123");
        bashkan.addError("Exception type: " + this.getClass().getPackageName() + " => " +
                "exception detail: " + this.getClass().getSimpleName() + " of number: " + hashNumber);
    }

}
