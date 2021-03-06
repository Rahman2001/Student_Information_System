package gazi.university.CourseData_SubClasses;

import gazi.university.CourseData;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.AssistingStaff;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.TeachingStaff;
import gazi.university.Person_SubClasses.Student;
import gazi.university.Person_SubClasses.Student_SubClasses.Grad_Student;
import gazi.university.Person_SubClasses.Student_SubClasses.Grad_Student_SubClasses.Msc_Student;

import java.util.List;
import java.util.Set;

public class Grad_CourseData extends CourseData {
    private static final double gradeCriteria = 2.0;

    public Grad_CourseData(String code, String name, int credit) {
        super(code, name, credit);
    }

    public Grad_CourseData(String code, String name, int credit, TeachingStaff instructor) {
        super(code, name, credit, instructor);
    }

    public Grad_CourseData(String code, String name, int credit, TeachingStaff instructor, AssistingStaff assistant) {
        super(code, name, credit, instructor, assistant);
    }

    public Grad_CourseData(String code, String name, int credit, TeachingStaff instructor, List<AssistingStaff> assistants) {
        super(code, name, credit, instructor, assistants);
    }

    @Override
    public void enrollStudent(Student student) {
        if (student.getClass().getSimpleName().equals(Grad_Student.class.getSimpleName())
                || student.getClass().getPackageName().equalsIgnoreCase(Msc_Student.class.getPackageName())) {
            super.enrollStudent(student);
        } else {
            System.out.println("You cannot enroll this student to graduate course because " +
                    "student must be of graduate degree!\n");
        }
    }

    @Override
    public Set<Student> getPassedStudents() {
        super.setGradeCriteria(gradeCriteria);
        return super.getPassedStudents();
    }
}