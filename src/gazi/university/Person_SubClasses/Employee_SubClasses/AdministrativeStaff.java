package gazi.university.Person_SubClasses.Employee_SubClasses;

import gazi.university.Person_SubClasses.Employee;

import java.util.ArrayList;
import java.util.List;

public class AdministrativeStaff extends Employee {
    private static final int minAmountOperation = 10;
    private static final int maxAmountError = (minAmountOperation * 10) / 100;
    private static final List<String> performedOperations = new ArrayList<>();
    private static final List<String> error = new ArrayList<>();

    public AdministrativeStaff(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }

    public List<String> getPerformedOperations() {
        return performedOperations;
    }

    public List<String> getErrorList() {
        return error;
    }

    public void addPerformedOperation(String operationDescription){
        performedOperations.add(operationDescription);
    }
    public void addError(String errorDescription){
        error.add(errorDescription);
    }
    public boolean earnedHisSalary(){
        int errorRate = (error.size() * 100) / performedOperations.size();
        return performedOperations.size() >= 10 && errorRate < maxAmountError;
    }
}
