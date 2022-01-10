package gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses;

import gazi.university.Person_SubClasses.Employee_SubClasses.Academics;

public class AssistingStaff extends Academics {
    private boolean hasLab = false;

    public AssistingStaff(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }
    public void addLab(){
        this.hasLab = true;
    }
    public boolean hasLab(){
        return this.hasLab;
    }
}
