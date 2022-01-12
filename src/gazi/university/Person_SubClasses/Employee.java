package gazi.university.Person_SubClasses;

import gazi.university.*;
import gazi.university.Person_SubClasses.Employee_SubClasses.AdministrativeStaff;
import gazi.university.UMS.Accounting_Exception.Pay_Salary_Exception.OvertimePaymentRequiredException;
import gazi.university.UMS.Accounting_Exception.Pay_Salary_Exception.SalaryCannotBePaidException;
import gazi.university.UMS.Accounting_Exception.SalaryInitializationException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.RegistryNumberLengthMismatchException;

public abstract class Employee extends Person {
    private static final ThreadLocal<AdministrativeStaff> bashkan = ThreadLocal.withInitial(() -> new AdministrativeStaff
            ("12345678911", "Rahman Rejepov", "1234567891123")); // In order to avoid deadlock, we try to use multithreading

    private String registry_number = "NaN";
    private float salary;
    private boolean overTimePayment = false;

    public Employee(String identity_no, String name, String registry_number){
        super(identity_no, name);
        this.registry_number = registry_number;
    }

    public String getRegistryNumber() {
        return registry_number;
    }

    public void setRegistryNumber(String registry_number) throws RegistryNumberLengthMismatchException {
        if(registry_number.length() != 13){
            throw new RegistryNumberLengthMismatchException(RegistryNumberLengthMismatchException.class.getSimpleName() +
                    "\nRegistry Number must be of length 13!\n");
        }else {
            this.registry_number = registry_number;

            bashkan.get().addPerformedOperation("'setRegistryNumber' operation of number: "
                    + hashCode() + " is successfully performed."); //adds successful operations into the list of operations
        }
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) throws SalaryInitializationException {
        if(salary <= 0){
            throw new SalaryInitializationException(SalaryInitializationException.class.getSimpleName() +
                    "\nThe salary must not be less or equal to zero (0) !\n");
        }else {
            this.salary = salary;

            bashkan.get().addPerformedOperation("'setSalary' operation of number: "
                    + hashCode() + " is successfully performed."); //adds successful operations into the list of operations
        }
    }

    public boolean earnedHisSalary(){
        return true;
    }

    protected void overtimePayment(){
        this.overTimePayment = true;
    } // only maintenance class call this method to make overtime return true

    public float paySalary(float salary) throws SalaryInitializationException, OvertimePaymentRequiredException, SalaryCannotBePaidException {
        if(!this.earnedHisSalary()){
            throw new SalaryCannotBePaidException(SalaryCannotBePaidException.class.getSimpleName() + "\n");
        }else if(this.earnedHisSalary() && !(this.overTimePayment)){
            this.setSalary(salary);
        }else if(this.earnedHisSalary() && this.overTimePayment){
            throw new OvertimePaymentRequiredException(OvertimePaymentRequiredException.class.getSimpleName() + "\n");
        }
        bashkan.get().addPerformedOperation("'paySalary' operation of number: "
                + hashCode() + " is successfully performed."); //adds successful operations into the list of operations
        return this.getSalary();
    }
}