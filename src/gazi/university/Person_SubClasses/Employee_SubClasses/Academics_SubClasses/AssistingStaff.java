package gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses;

import gazi.university.Location_SubClasses.Lab;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics;

public abstract class AssistingStaff extends Academics {
    private Lab lab ;

    public AssistingStaff(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }
    public void addLab(Lab lab){
        this.lab = lab;
    }
    public Lab getLab(){
        return this.lab;
    }
}
