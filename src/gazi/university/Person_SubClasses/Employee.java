package gazi.university.Person_SubClasses;

import gazi.university.Person;

public class Employee extends Person {

    private String registry_number = "NaN";
    private int salary;

    public Employee(String identity_no, String name, String registry_number, int salary){
        super(identity_no, name);
        this.registry_number = registry_number;
        this.salary = salary;
    }


    public String getRegistry_number() {
        return registry_number;
    }

    public void setRegistry_number(String registry_number) {
        this.registry_number = registry_number;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean EarnedHisSalary(){
        return true;
    }

}
