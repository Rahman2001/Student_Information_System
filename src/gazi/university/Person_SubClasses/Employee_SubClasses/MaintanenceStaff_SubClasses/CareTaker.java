package gazi.university.Person_SubClasses.Employee_SubClasses.MaintanenceStaff_SubClasses;

import gazi.university.Location;
import gazi.university.Person_SubClasses.Employee_SubClasses.MaintenanceStaff;

public class CareTaker extends MaintenanceStaff{
    private static final int minAmountResponsibility = 3;
    private int takenAmountResponsibility;
    private int takenAmountSpace;
    private static final int minIndoorSpace = 250; // m^2 unit

    public CareTaker(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }

    public boolean earnedHisSalary(){
        return this.takenAmountSpace >= minIndoorSpace && this.takenAmountResponsibility >= minAmountResponsibility;
    }

    public void addResponsibility(MaintenanceStaff maintenanceStaff, Location location){
        String maintenanceType = maintenanceStaff.getClass().getSimpleName();

        if(maintenanceType.equalsIgnoreCase(this.getClass().getSimpleName())){
            this.takenAmountResponsibility++;
            this.takenAmountSpace += location.getSpace();
            if((this.takenAmountResponsibility > 5 || this.takenAmountSpace > minIndoorSpace * 2) //Overtime payment criteria
                    && location.isProperlyMaintained()){
                super.overtimePayment(true);
            }
            super.addResponsibility(maintenanceStaff, location);
        }else{
            System.out.println("Incorrect type of maintenance! (should be : " + this.getClass().getSimpleName() + ")\n");
        }
    }

}
