package gazi.university.Person_SubClasses.Student_SubClasses;
import gazi.university.CourseData;
import gazi.university.CourseData_SubClasses.Grad_CourseData;
import gazi.university.CourseTypeAndStudentTypeInconsistencyException;
import gazi.university.Person_SubClasses.Student;

import java.time.Year;
import java.util.Set;

public class Grad_Student  extends Student {
    private static final double gradeCriteria = 2.0;
    private static final int totalPassedCredits = 120;

    public Grad_Student(String student_number, Year enrollment_year) {
        super(student_number, enrollment_year);
        super.setGradeCriteria(gradeCriteria);
        super.setTotalPassedCreditsCriteria(totalPassedCredits);
    }

    public Grad_Student(String student_number, Year enrollmentYear, int semester, Set<CourseData> pastCourses) {
        super(student_number, enrollmentYear, semester, pastCourses);
        super.setGradeCriteria(gradeCriteria);
        super.setTotalPassedCreditsCriteria(totalPassedCredits);
    }

    @Override
    public boolean checkGraduation(){
        return super.checkGraduation();
    }

    @Override
    public void addCourse(CourseData courseData)throws CourseTypeAndStudentTypeInconsistencyException {
        String courseType = courseData.getClass().getSimpleName();
        if(!courseType.equals(Grad_CourseData.class.getSimpleName())){ //if a course data is not type of Graduate then throw exception
            throw new CourseTypeAndStudentTypeInconsistencyException
                    (CourseTypeAndStudentTypeInconsistencyException.class.getSimpleName());
        }else {
            super.addCourse(courseData);
        }
    }
    @Override
    public int getPassedCredits(){
        return super.getPassedCredits();
    }
}

