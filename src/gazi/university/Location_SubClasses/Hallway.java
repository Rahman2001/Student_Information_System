package gazi.university.Location_SubClasses;

import gazi.university.Location;
import gazi.university.Person_SubClasses.Employee_SubClasses.MaintanenceStaff_SubClasses.CareTaker;
import gazi.university.Person_SubClasses.Employee_SubClasses.MaintenanceStaff;

public class Hallway extends Location {

    public Hallway(String identifier, String address, double space){
        super(identifier, address, space);
    }

    public void addResponsibleOneFor(MaintenanceStaff maintenanceStaff, Location location){
        String staffType = maintenanceStaff.getClass().getSimpleName();

        if(staffType.equalsIgnoreCase(CareTaker.class.getSimpleName()) &&
                location.getClass().getSimpleName().equalsIgnoreCase(this.getClass().getSimpleName())){
            super.addResponsibleOneFor(maintenanceStaff, location);
        }else{
            System.out.println("Incorrect type of staff! (should be " + CareTaker.class.getSimpleName() + " type to give" +
                    "that responsibility!\n");
        }
    }
}
