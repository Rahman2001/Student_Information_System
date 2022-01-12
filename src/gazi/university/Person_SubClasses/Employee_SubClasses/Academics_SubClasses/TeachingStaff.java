package gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses;

import gazi.university.Location_SubClasses.Office;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics;

public abstract class TeachingStaff extends Academics {
    private String officeHours ;
    private Office office;

    public TeachingStaff(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

}