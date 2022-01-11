package gazi.university;

import gazi.university.CourseData_SubClasses.Undergrad_CourseData;
import gazi.university.Person_SubClasses.Student;
import gazi.university.Person_SubClasses.Student_SubClasses.Undergrad_Student;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.PersonIDLengthMismatchException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.Person_Name_Length_Mismatch_Exception.PersonNameTooLongException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.Person_Name_Length_Mismatch_Exception.PersonNameTooShortException;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws PersonIDLengthMismatchException, PersonNameTooShortException, PersonNameTooLongException {
	// write your code here
        Undergrad_Student Rahman = new Undergrad_Student("191180400", 2019);
        Rahman.setStudentName("Rahman");
        Undergrad_Student Rahym = new Undergrad_Student("1918024021", 2020);
        Rahym.setStudentName("Rahym");
        Undergrad_CourseData courseData1 = new Undergrad_CourseData("MATH213", "Diff.Eq.", 10);
        Undergrad_CourseData courseData2 = new Undergrad_CourseData("BM312", "Elektrik.Dev.", 5);

        courseData1.enrollStudent(Rahman);
        courseData1.enrollStudent(Rahym);
        courseData2.enrollStudent(Rahman);
        courseData2.enrollStudent(Rahym);

        courseData1.setGradeOfStudent(Rahym, 3);
        courseData2.setGradeOfStudent(Rahman, 4);

        System.out.println(courseData2.getPassedStudents().toString());


    }
}
