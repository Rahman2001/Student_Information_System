package gazi.university.CourseData_SubClasses;

import gazi.university.CourseData;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.AssistingStaff;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.TeachingStaff;
import gazi.university.Person_SubClasses.Student;
import gazi.university.Person_SubClasses.Student_SubClasses.Undergrad_Student;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Undergrad_CourseData extends CourseData{
    private static final double gradeCriteria = 2.0;

    public Undergrad_CourseData(String code, String name, int credit) {
        super(code, name, credit);
    }
    public Undergrad_CourseData(String code, String name, int credit, TeachingStaff instructor){
       super(code, name, credit, instructor);
    }
    public Undergrad_CourseData(String code, String name, int credit, TeachingStaff instructor, AssistingStaff assistant){
        super(code, name, credit, instructor, assistant);
    }
    public Undergrad_CourseData(String code, String name, int credit, TeachingStaff instructor, List<AssistingStaff> assistants){
        super(code, name, credit, instructor, assistants);
    }
    public Undergrad_CourseData(){}

    @Override
    public void enrollStudent(Student student) {
        if(student.getClass().getSimpleName().equals(Undergrad_Student.class.getSimpleName())) {
            super.enrollStudent(student);
        }else{
            System.out.println("You cannot enroll this student to undergraduate course because " +
                    "the student must be of undergraduate degree!\n");
        }
    }
    @Override
    public Set<Student> getPassedStudents() {
        super.setGradeCriteria(gradeCriteria);
        return super.getPassedStudents();
    }
}
