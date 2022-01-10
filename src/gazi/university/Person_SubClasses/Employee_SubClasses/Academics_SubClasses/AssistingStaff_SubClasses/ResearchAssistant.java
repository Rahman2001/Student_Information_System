package gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.AssistingStaff_SubClasses;

import gazi.university.CourseData;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.AssistingStaff;

import java.util.Set;

public class ResearchAssistant extends AssistingStaff {
    private boolean hasResearch = false;

    public ResearchAssistant(String identity_no, String name, String registry_number){
        super(identity_no, name, registry_number);
    }
    public boolean earnedHisSalary(){
        Set<CourseData> courseDataSet = super.getAssignmentOf(this);
        int totalCredit = courseDataSet.stream().mapToInt(CourseData::getCredit).sum();
        return totalCredit >= 8 && this.hasResearch;
    }
    public void addAssignmentFor(Academics academics, CourseData courseData){
        super.addAssignmentFor(academics, courseData);
    }
    public void addResearch(){
        this.hasResearch = true;
    }
}
