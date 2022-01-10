package gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.TeachingStaff_SubClasses;

import gazi.university.CourseData;
import gazi.university.CourseData_SubClasses.Grad_CourseData;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.TeachingStaff;

import java.util.Set;

public class Professor extends TeachingStaff {
    private boolean hasResearch = false;

    public Professor(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }
    public boolean earnedHisSalary(){
        Set<CourseData> courseDataSet = super.getAssignmentOf(this);
        int totalCredit = courseDataSet.stream().mapToInt(CourseData::getCredit).sum();
        return totalCredit >= 8 && this.hasResearch;
    }
    public void addAssignmentFor(Academics academics, CourseData courseData){
        String courseType = courseData.getClass().getSimpleName();
        if(courseType.equalsIgnoreCase(Grad_CourseData.class.getSimpleName())) {
            super.addAssignmentFor(academics, courseData);
        } else {
            System.out.println("You can't assign " + courseData.getName() + " course to " +
                    this.getClass().getSimpleName() + " because the professor gets only graduate courses!\n");
        }
    }
    public void addResearch(){
        this.hasResearch = true;
    }
}
