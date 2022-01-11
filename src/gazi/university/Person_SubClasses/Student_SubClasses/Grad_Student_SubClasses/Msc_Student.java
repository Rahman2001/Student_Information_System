package gazi.university.Person_SubClasses.Student_SubClasses.Grad_Student_SubClasses;

import gazi.university.CourseData;
import gazi.university.UMS.Student_Affairs_Exception.CourseTypeAndStudentTypeInconsistencyException;
import gazi.university.Person_SubClasses.Student_SubClasses.Grad_Student;

import java.time.Year;
import java.util.Collection;
import java.util.Set;

public class Msc_Student extends Grad_Student {
    private static final double gradeCriteria = 2.5;
    private static final int totalPassedCreditsCriteria = 40;

    public Msc_Student(String student_number, int enrollment_year) {
        super(student_number, enrollment_year);
        super.setGradeCriteria(gradeCriteria);
        super.setTotalPassedCreditsCriteria(totalPassedCreditsCriteria);
    }

    public Msc_Student(String student_number, Year enrollmentYear, int semester, Set<CourseData> pastCourses) {
        super(student_number, enrollmentYear, semester, pastCourses);
        super.setGradeCriteria(gradeCriteria);
        super.setTotalPassedCreditsCriteria(totalPassedCreditsCriteria);
    }
    @Override
    public void addCourse(CourseData courseData) throws CourseTypeAndStudentTypeInconsistencyException {
        super.addCourse(courseData);
    }

    @Override
    public boolean checkGraduation() {
        boolean isTrue = super.checkGraduation();
        String thesisCourse = super.getCurrentCourses().stream()
                .filter(x -> x.getName().equalsIgnoreCase("MSCThesis") && x.getCode()
                        .equalsIgnoreCase("T600") && x.getGradeForList() >= gradeCriteria).findAny().toString();

        if (thesisCourse == null && isTrue) { // if that course data doesn't exist in current courses then it looks for in transcript
            Collection<Set<CourseData>> transcript = super.getTranscript().values();

            for (Set<CourseData> courseData : transcript) { // goes through all the course data to find the particular one

                thesisCourse = courseData.stream().filter(x -> x.getName()
                        .equalsIgnoreCase("MSCThesis") && x.getCode()
                        .equalsIgnoreCase("T600") &&
                        x.getGradeForList() >= gradeCriteria).findAny().toString(); // finds that course data we need

                return thesisCourse != null; // if such course exists with passed grade then return true
            }
        }
        return isTrue; // if thesis course is not null at all then return boolean of isTrue
    }

    @Override
    public int getPassedCredits(){
        return super.getPassedCredits();
    }
}