package gazi.university.Person_SubClasses.Student_SubClasses;
import gazi.university.CourseData;
import gazi.university.CourseData_SubClasses.Undergrad_CourseData;
import gazi.university.CourseTypeAndStudentTypeInconsistencyException;
import gazi.university.Person_SubClasses.Student;

import java.time.Year;
import java.util.Set;

public class Undergrad_Student extends Student {
    private static final double gradeCriteria = 2.0;
    private static final int totalPassedCreditsCriteria = 100;

    public Undergrad_Student(String student_number, Year enrollment_year) {
        super(student_number, enrollment_year);
        super.setGradeCriteria(gradeCriteria);
        super.setTotalPassedCreditsCriteria(totalPassedCreditsCriteria);
    }

    public Undergrad_Student(String student_number, Year enrollmentYear, int semester, Set<CourseData> pastCourses) {
        super(student_number, enrollmentYear, semester, pastCourses);
        super.setGradeCriteria(gradeCriteria);
        super.setTotalPassedCreditsCriteria(totalPassedCreditsCriteria);
    }

    @Override
    public boolean checkGraduation(){
        return super.checkGraduation();
    }

    @Override
    public void addCourse(CourseData courseData) throws CourseTypeAndStudentTypeInconsistencyException {
        String courseType = courseData.getClass().getSimpleName();
        if(courseType.equals(Undergrad_CourseData.class.getSimpleName())){
            super.addCourse(courseData);
        }else{
            throw new CourseTypeAndStudentTypeInconsistencyException(
                    CourseTypeAndStudentTypeInconsistencyException.class.getSimpleName()
                    + "\nYou can't add " + courseType + " to " + this.getClass().getSimpleName() +
                    " since it is not correct type of course data!\n");
        }
    }

    @Override
    public int getPassedCredits(){
        return super.getPassedCredits();
    }
}