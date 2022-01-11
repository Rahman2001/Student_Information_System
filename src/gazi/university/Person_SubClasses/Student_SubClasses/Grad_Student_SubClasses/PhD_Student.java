package gazi.university.Person_SubClasses.Student_SubClasses.Grad_Student_SubClasses;

import gazi.university.CourseData;
import gazi.university.UMS.Student_Affairs_Exception.CourseTypeAndStudentTypeInconsistencyException;
import gazi.university.Person_SubClasses.Student_SubClasses.Grad_Student;

import java.time.Year;
import java.util.Collection;
import java.util.Set;

class PhD_Student extends Grad_Student {
    private static final double gradeCriteria = 3.0;
    private static final int totalPassedCreditsCriteria = 120;

    public PhD_Student(String student_number, Year enrollment_year) {
        super(student_number, enrollment_year);
        super.setGradeCriteria(gradeCriteria);
        super.setTotalPassedCreditsCriteria(totalPassedCreditsCriteria);
    }

    public PhD_Student(String student_number, Year enrollmentYear, int semester, Set<CourseData> pastCourses) {
        super(student_number, enrollmentYear, semester, pastCourses);
        super.setGradeCriteria(gradeCriteria);
        super.setTotalPassedCreditsCriteria(totalPassedCreditsCriteria);
    }

    @Override
    public boolean checkGraduation() {
        boolean isTrue = super.checkGraduation();
        String thesisCourse = super.getCurrentCourses().stream().findAny()
                .filter(x -> x.getName().equalsIgnoreCase("MSCThesis") && x.getCode()
                        .equalsIgnoreCase("T600") && x.getGrade() >= gradeCriteria).get().toString();

        String proficiencyCourse = super.getCurrentCourses().stream().findAny().filter(y -> y.getName()
                .equalsIgnoreCase("Proficiency") && y.getCode()
                .equalsIgnoreCase("P700") && y.getGrade() >= gradeCriteria).get().toString();

        if (thesisCourse == null || proficiencyCourse == null && isTrue) {
            Collection<Set<CourseData>> transcript = super.getPastCoursesOfSemester().values();

            if (thesisCourse == null) {
                for (Set<CourseData> courseData : transcript) {

                    thesisCourse = courseData.stream().findAny().filter(x -> x.getName()
                            .equalsIgnoreCase("PHDThesis") && x.getCode()
                            .equalsIgnoreCase("T700") &&
                            x.getGrade() >= gradeCriteria).get().toString();
                    if (thesisCourse != null) {
                        break;
                    }
                }
            }
            if (proficiencyCourse == null) {
                for (Set<CourseData> courseData : transcript) {

                    proficiencyCourse = courseData.stream().findAny().filter(x -> x.getName()
                            .equalsIgnoreCase("Proficiency") && x.getCode()
                            .equalsIgnoreCase("P700") &&
                            x.getGrade() >= gradeCriteria).get().toString();
                    if (proficiencyCourse != null) {
                        break;
                    }
                }
            }
            if (thesisCourse != null && proficiencyCourse != null) {
                return true;
            }
        }
        return isTrue; // if thesis Course is not null, proficiency Course is not null and isTrue is true
    }

    @Override
    public int getPassedCredits() {
        return super.getPassedCredits();
    }

    @Override
    public void addCourse(CourseData courseData) throws CourseTypeAndStudentTypeInconsistencyException {
        String code = courseData.getCode();
        String courseType = courseData.getClass().getSimpleName();
        if (!courseType.equals(Grad_Student.class.getSimpleName())) {
            throw new CourseTypeAndStudentTypeInconsistencyException(
                    CourseTypeAndStudentTypeInconsistencyException.class.getSimpleName() +
                            "\nYou can't add " + courseType + " to " + this.getClass().getSimpleName() +
                            "because it is not correct course type!");
        }else {
            if (code.equalsIgnoreCase("T700")) {
                boolean isPassed = super.getCurrentCourses().stream().findAny().filter(x -> x.getName()
                        .equalsIgnoreCase("PhdThesis") && x.getCode()
                        .equalsIgnoreCase("P700") && x.getGrade() >= gradeCriteria).isPresent();

                if (!isPassed) {
                    Collection<Set<CourseData>> courseDataLists = super.getPastCoursesOfSemester().values();
                    for (Set<CourseData> temp : courseDataLists) {
                        isPassed = temp.stream().findAny().filter(y -> y.getName()
                                .equalsIgnoreCase("PhdThesis") && y.getCode()
                                .equalsIgnoreCase("P700") && y.getGrade() >= gradeCriteria).isPresent();
                        if (isPassed) {
                            super.addCourse(courseData);
                            break;
                        }
                    }
                } else {
                    super.addCourse(courseData);
                }
            } else {
                super.addCourse(courseData);
            }
        }
    }
}