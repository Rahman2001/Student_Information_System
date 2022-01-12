package gazi.university.Person_SubClasses.Employee_SubClasses;

import gazi.university.CourseData;
import gazi.university.Person_SubClasses.Employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class Academics extends Employee {
    private static final HashMap<Academics, Set<CourseData>> assignmentByEmployee = new HashMap<>();

    public Academics(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }

    public void addAssignmentFor(Academics academics, CourseData courseAssignment){

        if(assignmentByEmployee.containsKey(academics) && assignmentByEmployee.get(academics) != null) {
            Set<CourseData> assignmentSet = assignmentByEmployee.get(academics);
            assignmentSet.add(courseAssignment);
            assignmentByEmployee.replace(academics, assignmentSet);
        }else{
            Set<CourseData> assignmentSet = new HashSet<>();
            assignmentSet.add(courseAssignment);
            assignmentByEmployee.put(academics, assignmentSet);
        }
    }
    protected Set<CourseData> getAssignmentOf(Academics academics){
        return assignmentByEmployee.get(academics);
    }
}
