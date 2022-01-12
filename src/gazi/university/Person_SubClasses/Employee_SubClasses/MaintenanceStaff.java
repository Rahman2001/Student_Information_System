package gazi.university.Person_SubClasses.Employee_SubClasses;

import gazi.university.Location;
import gazi.university.Person_SubClasses.Employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class MaintenanceStaff extends Employee {
    private static final HashMap<MaintenanceStaff, Set<Location>> responsibility = new HashMap<>();

    public MaintenanceStaff(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }
    public void addResponsibility(MaintenanceStaff maintenanceStaff, Location location){
        Set<Location> locationSet;
        if(responsibility.containsKey(maintenanceStaff) && responsibility.get(maintenanceStaff) != null){
            locationSet = responsibility.get(maintenanceStaff);
            locationSet.add(location);
            responsibility.replace(maintenanceStaff, locationSet);
        }else{
            locationSet = new HashSet<>();
            locationSet.add(location);
            responsibility.put(maintenanceStaff, locationSet);
        }
    }
}
