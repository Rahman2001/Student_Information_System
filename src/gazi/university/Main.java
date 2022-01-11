package gazi.university;

import gazi.university.CourseData_SubClasses.Grad_CourseData;
import gazi.university.Person_SubClasses.Student_SubClasses.Grad_Student;

import gazi.university.Person_SubClasses.Student_SubClasses.Grad_Student_SubClasses.Msc_Student;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.PersonIDLengthMismatchException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.Person_Name_Length_Mismatch_Exception.PersonNameTooLongException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.Person_Name_Length_Mismatch_Exception.PersonNameTooShortException;
import gazi.university.UMS.Student_Affairs_Exception.MissingGradeException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws PersonIDLengthMismatchException, PersonNameTooShortException, PersonNameTooLongException, MissingGradeException {
	// write your code here
        Grad_Student Rahman = new Msc_Student("191180400", 2019);
        Rahman.setStudentName("Rahman");
        Grad_Student Rahym = new Msc_Student("1918024021", 2020);
        Rahym.setStudentName("Rahym");
        Grad_CourseData courseData1 = new Grad_CourseData("MATH213", "Diff.Eq.", 10);
        Grad_CourseData courseData2 = new Grad_CourseData("BM312", "Elektrik.Dev.", 5);

        courseData1.enrollStudent(Rahman);
        courseData1.enrollStudent(Rahym);
        courseData2.enrollStudent(Rahman);
        courseData2.enrollStudent(Rahym);

        courseData1.setGradeOfStudent(Rahym, 2);
        courseData2.setGradeOfStudent(Rahman, 2);

        System.out.println(courseData2.getPassedStudents().toString());

        //Testing student class by setting transcript for the student---------------------
        Set<CourseData> gradCourseSet = courseData2.getCourseOfStudent(Rahman);
        Rahman.setTranscriptOfSemester(1,gradCourseSet); //set first semester's course data into student's transcript
        //-------------------------------------------------------------------------------------

        //Testing2 by inserting second semester's transcript------------------------------------------------
        CourseData courseData3 = new Grad_CourseData("ENG211", "Academic English", 4);
        Set<CourseData> gradCourseSet2 = courseData3.getCourseOfStudent(Rahman);

        Rahman.setTranscriptOfSemester(2, gradCourseSet2);
        //-----------------------------------------------------------------------------------------------

        //Printing all data in transcript of a student----------------------------------------
        HashMap<Integer, Set<CourseData>> transcript = Rahman.getTranscript();
        for(Map.Entry map : transcript.entrySet()){
            System.out.println(map.getKey() + " " + map.getValue().toString());
        }
        //--------------------------------------------------------------------------------------
        System.out.println("Passed Elektrik.Dev. course: " + Rahman.checkPassedCourseByName("Elektrik.Dev."));
        System.out.println("Passed credits: " + Rahman.getPassedCredits());
    }
}
