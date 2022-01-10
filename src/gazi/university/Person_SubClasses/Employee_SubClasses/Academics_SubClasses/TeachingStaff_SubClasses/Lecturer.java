package gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.TeachingStaff_SubClasses;

import gazi.university.CourseData;
import gazi.university.CourseData_SubClasses.Undergrad_CourseData;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.TeachingStaff;

import java.util.Set;

public class Lecturer extends TeachingStaff {

    public Lecturer(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }

    public boolean earnedHisSalary(){
        Set<CourseData> assignments = super.getAssignmentOf(this);
        int totalCredit = assignments.stream().mapToInt(CourseData::getCredit).sum();
        return totalCredit >= 20;
    }

    public void addAssignmentFor(Academics academics, CourseData courseAssignment){
        String courseType = courseAssignment.getClass().getSimpleName();
        if(courseType.equalsIgnoreCase(Undergrad_CourseData.class.getSimpleName())){
            super.addAssignmentFor(academics, courseAssignment);
        }else{
            System.out.println("You can't assign " + courseAssignment.getName() + " to " + this.getClass().getSimpleName()
            + " because lecturers can only take undergraduate courses!\n");
        }
    }
}
