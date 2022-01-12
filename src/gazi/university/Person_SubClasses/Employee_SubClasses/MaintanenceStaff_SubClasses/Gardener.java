package gazi.university.Person_SubClasses.Employee_SubClasses.MaintanenceStaff_SubClasses;

import gazi.university.Location;
import gazi.university.Person_SubClasses.Employee_SubClasses.MaintenanceStaff;

public class Gardener extends MaintenanceStaff {
    private static final int minAmountResponsibility = 2;
    private static final int minAmountSpace = 1000; //m^2 unit
    private int takenAmountResponsibility;
    private int takenAmountSpace;

    public Gardener(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }

    public boolean earnedHisSalary(){
        return this.takenAmountSpace >= minAmountSpace && this.takenAmountResponsibility >= minAmountResponsibility;
    }

    public void addResponsibility(MaintenanceStaff maintenanceStaff, Location location){
        String maintenanceType = maintenanceStaff.getClass().getSimpleName();

        if(maintenanceType.equalsIgnoreCase(this.getClass().getSimpleName())){
            this.takenAmountResponsibility++;
            this.takenAmountSpace += location.getSpace();

            if((this.takenAmountResponsibility > minAmountResponsibility || takenAmountSpace > minAmountSpace * 2) // Overtime payment criteria
                    && location.isProperlyMaintained()){
                super.overtimePayment();
            }
            super.addResponsibility(maintenanceStaff, location);
        }else{
            System.out.println("Incorrect type of maintenance! (should be " + this.getClass().getSimpleName() + ")\n");
        }
    }
}