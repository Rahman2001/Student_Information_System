package gazi.university.Location_SubClasses;

import gazi.university.Location;
import gazi.university.Person_SubClasses.Employee_SubClasses.MaintanenceStaff_SubClasses.Gardener;
import gazi.university.Person_SubClasses.Employee_SubClasses.MaintenanceStaff;

public class Garden extends Location {

    public Garden(String identityNumber, String address, double space){
        super(identityNumber, address, space);
    }

    public void addResponsibleOneFor(MaintenanceStaff maintenanceStaff, Location location){
        String staffType = maintenanceStaff.getClass().getSimpleName();
        if(staffType.equalsIgnoreCase(Gardener.class.getSimpleName()) &&
                location.getClass().getSimpleName().equalsIgnoreCase(this.getClass().getSimpleName())){
            super.addResponsibleOneFor(maintenanceStaff, location);
        }else{
            System.out.println("Incorrect type of staff! (should be " + Gardener.class.getSimpleName() + " type to give" +
                    "that responsibility!\n");
        }
    }
}
