package gazi.university.Person_SubClasses.Employee_SubClasses;

import gazi.university.Person_SubClasses.Employee;

import java.util.ArrayList;
import java.util.List;

public class AdministrativeStaff extends Employee {
    private static final int minAmountOperation = 10;
    private static final int maxAmountError = (minAmountOperation * 10) / 100;
    private List<String> performedOperations = new ArrayList<>();
    private List<String> error = new ArrayList<>();

    public AdministrativeStaff(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }

    public List<String> getPerformedOperations() {
        return performedOperations;
    }

    public void setPerformedOperations(List<String> performedOperations) {
        this.performedOperations = performedOperations;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public void addPerformedOperation(String operationDescription){
        this.performedOperations.add(operationDescription);
    }
    public void addError(String errorDescription){
        this.error.add(errorDescription);
    }
    public boolean earnedHisSalary(){
        int errorRate = (this.error.size() * 100) / this.performedOperations.size();
        return performedOperations.size() >= 10 && errorRate < maxAmountError;
    }
}
