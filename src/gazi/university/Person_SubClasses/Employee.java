package gazi.university.Person_SubClasses;

import gazi.university.OvertimePaymentRequiredException;
import gazi.university.Person;
import gazi.university.RegistryNumberLengthMismatchException;
import gazi.university.SalaryInitializationException;

public class Employee extends Person {

    private String registry_number = "NaN";
    private float salary;
    private boolean overTimePayment;

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
        }
    }

    public boolean earnedHisSalary(){
        return true;
    }
    public void overtimePayment(boolean overTime){
        this.overTimePayment = overTime;
    }

    public float paySalary(float salary) throws SalaryInitializationException, OvertimePaymentRequiredException {
        if(this.earnedHisSalary() && !(this.overTimePayment)){
            this.setSalary(salary);
        }else if(this.overTimePayment){
            throw new OvertimePaymentRequiredException(OvertimePaymentRequiredException.class.getSimpleName() + "\n");
        }
        return this.getSalary();
    }
}
