package gazi.university;

import gazi.university.Person_SubClasses.Student;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.PersonIDLengthMismatchException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.Person_Name_Length_Mismatch_Exception.PersonNameTooLongException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.Person_Name_Length_Mismatch_Exception.PersonNameTooShortException;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws PersonIDLengthMismatchException, PersonNameTooShortException, PersonNameTooLongException {
	// write your code here
        Student Rahman = new Student("191180400", 2019);
        Student Rahym = new Student("1918024021", 2020);
        CourseData courseData1 = new CourseData("BM221", "Elektrik.Dev.", 4);
        CourseData courseData2 = new CourseData("MATH213", "Diff.Eq.", 10);

        courseData1.enrollStudent(Rahman);
        courseData1.enrollStudent(Rahym);
        courseData2.enrollStudent(Rahman);
        courseData2.enrollStudent(Rahym);

        courseData1.setGradeOfStudent(Rahman, 3);
        courseData1.setGradeOfStudent(Rahym, 2);
        courseData2.setGradeOfStudent(Rahman, 2);
        courseData2.setGradeOfStudent(Rahym, 3);

        Set<Student> studentSet = courseData1.getPassedStudents();
        System.out.println(studentSet.toString());

    }
}
