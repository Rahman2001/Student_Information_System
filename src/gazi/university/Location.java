package gazi.university;

import gazi.university.Person_SubClasses.Employee_SubClasses.MaintenanceStaff;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class Location {
    private String identifier;
    private String address;
    private double space;
    private static final HashMap<Location, Set<MaintenanceStaff>> responsibleOnes = new HashMap<>();

    public Location(String identifier, String address, double space){
        this.identifier = identifier;
        this.address = address;
        this.space = space;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identityNumber) {
        this.identifier = identityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSpace() {
        return space;
    }

    public void setSpace(double space) {
        this.space = space;
    }

    public Set<MaintenanceStaff> getResponsibleOnesOf(Location location) {
        return responsibleOnes.get(location);
    }

    public void addResponsibleOneFor(MaintenanceStaff responsible, Location location) {
        Set<MaintenanceStaff> responsibleSet;
        if(responsibleOnes.containsKey(location) && !responsibleOnes.get(location).isEmpty()){
             responsibleSet = responsibleOnes.get(location);
            responsibleSet.add(responsible);
            responsibleOnes.replace(location, responsibleSet);
        }else{
            responsibleSet = new HashSet<>();
            responsibleSet.add(responsible);
            responsibleOnes.put(location, responsibleSet);
        }
    }

    public boolean isProperlyMaintained(){
        return true;
    }
}
