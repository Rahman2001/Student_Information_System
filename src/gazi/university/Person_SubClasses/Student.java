package gazi.university.Person_SubClasses;
import gazi.university.CourseData;
import gazi.university.UMS.Student_Affairs_Exception.CourseTypeAndStudentTypeInconsistencyException;
import gazi.university.UMS.Parameter_Mismatch_Exception.Date_Exception.EnrollmentYearException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.StudentIDLengthMismatchException;

import java.time.Year;
import java.util.*;

public class Student {
    private String studentNumber = "NaN";
    private String studentName = "NaN";
    private Year enrollmentYear;
    private static double gradeCriteria;
    private static int totalPassedCreditsCriteria;
    private List<Integer> pastSemesters = new ArrayList<>();
    private final HashMap<Integer, Set<CourseData>> transcript = new HashMap<>();
    private Set<CourseData> currentCourses = new HashSet<>();

    public Student(String student_number, int enrollment_year) {
        super();
        this.studentNumber = student_number;
        this.enrollmentYear = Year.of(enrollment_year);
    }

    public Student(String student_number, Year enrollmentYear, int semester, Set<CourseData> pastCourses) {
        this.studentNumber = student_number;
        this.enrollmentYear = enrollmentYear;
        this.pastSemesters.add(semester);
        this.transcript.put(semester, pastCourses);
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public String getNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(String number) throws StudentIDLengthMismatchException {
        if (number.length() != 9) {
            throw new StudentIDLengthMismatchException(StudentIDLengthMismatchException.class.getSimpleName());
        } else {
            this.studentNumber = number;
        }
    }

    public Year getEnrollmentYear() {
        return this.enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear2) throws EnrollmentYearException {
        Year enrollmentYear = Year.of(enrollmentYear2); //Converts the input into a year data type
        if (enrollmentYear.isBefore(Year.of(2000)) || enrollmentYear.isAfter(Year.now())) {
            throw new EnrollmentYearException(EnrollmentYearException.class.getSimpleName());
        } else {
            this.enrollmentYear = enrollmentYear;
        }
    }

    protected double getGradeCriteria() {
        return gradeCriteria;
    }

    protected void setGradeCriteria(double gradeCriteria2) {
        gradeCriteria = gradeCriteria2;
    }

    protected int getTotalPassedCreditsCriteria() {
        return totalPassedCreditsCriteria;
    }

    protected void setTotalPassedCreditsCriteria(int totalPassedCreditsCriteria2) {
        totalPassedCreditsCriteria = totalPassedCreditsCriteria2;
    }

    public Set<CourseData> getCurrentCourses() { //gives course data to a user
        return this.currentCourses;
    }

    public void setCurrentCourses(Set<CourseData> currentCourses) {
        this.currentCourses = currentCourses;
    }

    public HashMap<Integer, Set<CourseData>> getTranscript() {
        return this.transcript;
    }

    public void setTranscriptOfSemester(int semester, Set<CourseData> pastCourseList) {
        pastSemesters.add(semester);
        transcript.put(semester, pastCourseList);
    }

    public int getTotalCredit() {
        int totalCredit = 0;
        for (int pastSem : this.pastSemesters) {
            Set<CourseData> pastCourses = this.transcript.get(pastSem); // gets list of past courses of each semester
            totalCredit += pastCourses.stream().mapToInt(CourseData::getCredit).sum(); // sums all credits of particular semester
        }
        totalCredit += this.currentCourses.stream().mapToInt(CourseData::getCredit).sum(); // sums all credits of current courses
        return totalCredit;
    }

    public int getPastCredits() {
        int totalPast = 0;
        for (int pastSem : this.pastSemesters) {
            Set<CourseData> pastCourses = this.transcript.get(pastSem); // gets list of past courses of each semester
            totalPast += pastCourses.stream().mapToInt(CourseData::getCredit).sum(); // sums all credits of particular semester
        }
        return totalPast;
    }

    public int getPassedCredits() {
        int totalPassed = 0;
        for (int pastSem : this.pastSemesters) {
            Set<CourseData> courseData = this.transcript.get(pastSem); // gets list of past courses from transcript
            totalPassed += courseData.stream().filter(x -> x.getGradeForList() >= this.getGradeCriteria())
                    .mapToInt(CourseData::getCredit).sum(); //sums up all credits matching the criteria
        }
        return totalPassed;
    }

    public int getCurrentCredit() {
        return this.currentCourses.stream().mapToInt(CourseData::getCredit).sum(); //return total credit of current courses
    }

    public boolean checkGraduation() {
        int lastSem = this.pastSemesters.get(this.pastSemesters.size() - 1);
        Set<CourseData> courseDataOfPrevSem = this.transcript.get(lastSem);
        double[] prevGradesArray = courseDataOfPrevSem.stream().mapToDouble(CourseData::getGradeForList).toArray();
        double[] currentGradesArray = this.currentCourses.stream().mapToDouble(CourseData::getGradeForList).toArray();
        int[] prevCreditsArray = courseDataOfPrevSem.stream().mapToInt(CourseData::getCredit).toArray();
        int[] currentCreditsArray = this.currentCourses.stream().mapToInt(CourseData::getCredit).toArray();
        double firstSemGPA = 0;
        double secondSemGPA = 0;
        for (int i = 0; i < prevGradesArray.length; i++) {
            firstSemGPA += prevGradesArray[i] * prevCreditsArray[i];
        }
        for (int j = 0; j < currentCreditsArray.length; j++) {
            secondSemGPA += currentCreditsArray[j] * currentGradesArray[j];
        }
        firstSemGPA = firstSemGPA / (Arrays.stream(prevCreditsArray).sum()); // first semester's GPA
        secondSemGPA = secondSemGPA / (Arrays.stream(currentCreditsArray).sum()); // second semester's GPA
        double CGPA = (firstSemGPA + secondSemGPA) / 2; // CGPA of a year
        int totalPassedCredits = this.getPassedCredits();
        return (CGPA >= this.getGradeCriteria() && totalPassedCredits > this.getTotalPassedCreditsCriteria());
    }

    public void addCourse(CourseData courseData) throws CourseTypeAndStudentTypeInconsistencyException { //adds a course to the List
        this.currentCourses.add(courseData);
    }

    public boolean checkCourseByName(String name) {
        String courseNameFromCurrent = this.currentCourses.stream().findFirst().
                filter(x -> x.getName().equalsIgnoreCase(name)).get().getName();
        String courseNameFromPrev;
        if (courseNameFromCurrent == null) { // if the searched course name is not in the list of current courses then check previous ones
            for (int prevSem : this.pastSemesters) {
                courseNameFromPrev = this.transcript.get(prevSem).stream()
                        .findFirst().filter(x -> x.getName().equalsIgnoreCase(name)).get().getName();
                if (courseNameFromPrev != null) {
                    return true;
                }
            }
        }
        return courseNameFromCurrent != null;
    }

    public boolean checkCourseByCode(String code) {
        String courseCodeFromCurrent = this.currentCourses.stream().filter(x -> x.getCode()
                .equalsIgnoreCase(code)).findAny().get().getCode();
        String courseCodeFromPrev;
        if (courseCodeFromCurrent == null) {
            for (int prevSem : this.pastSemesters) {
                courseCodeFromPrev = this.transcript.get(prevSem).stream().filter(x -> x.getCode()
                        .equalsIgnoreCase(code)).findAny().get().getCode();
                if (courseCodeFromPrev != null) {
                    return true;
                }
            }
        }
        return courseCodeFromCurrent != null;
    }

    public boolean checkPassedCourseByName(String name) { // checks if a student passed a particular course or not by searching its name
        double gradeOfCourse = this.currentCourses.stream().filter(x -> x.getName()
                .equalsIgnoreCase(name)).findAny().get().getGradeForList(); // gets the grade of that course from list of current courses
        boolean satisfiedCriteria = gradeOfCourse >= this.getGradeCriteria(); // criteria to return true
        if (gradeOfCourse == 0 && !satisfiedCriteria) {
            for (int prevSem : this.pastSemesters) {
                gradeOfCourse = this.transcript.get(prevSem).stream().filter(x -> x.getName()
                        .equalsIgnoreCase(name)).findAny().get().getGradeForList(); // searches that course in transcript and if found gets the grade
                satisfiedCriteria = gradeOfCourse >= this.getGradeCriteria();
                if (gradeOfCourse != 0 && satisfiedCriteria) {
                    return true;
                }
            }
        }
        return satisfiedCriteria;
    }

    public boolean checkPassedCourseByCode(String code) { // checks if the student passed particular course or not by searching its code
        double gradeOfCourse = this.currentCourses.stream().filter(x -> x.getCode()
                .equalsIgnoreCase(code)).findAny().get().getGradeForList(); // gets the grade of that course from list of current courses
        boolean satisfiedCriteria = gradeOfCourse >= this.getGradeCriteria(); // criteria to return true
        if (gradeOfCourse == 0 && !satisfiedCriteria) {
            for (int prevSem : this.pastSemesters) {
                gradeOfCourse = this.transcript.get(prevSem).stream().filter(x -> x.getCode()
                        .equalsIgnoreCase(code)).findAny().get().getGradeForList(); // searches that course in transcript and if found gets the grade
                satisfiedCriteria = gradeOfCourse >= this.getGradeCriteria();
                if (gradeOfCourse != 0 && satisfiedCriteria) {
                    return true;
                }
            }
        }
        return satisfiedCriteria;
    }
    public String toString(){
        return this.getStudentName() + " " + this.getNumber() + " " + this.getEnrollmentYear();
    }
}