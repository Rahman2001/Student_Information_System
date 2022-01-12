package gazi.university.Person_SubClasses;
import gazi.university.CourseData;
import gazi.university.Person;
import gazi.university.Person_SubClasses.Employee_SubClasses.AdministrativeStaff;
import gazi.university.UMS.Student_Affairs_Exception.CourseTypeAndStudentTypeInconsistencyException;
import gazi.university.UMS.Parameter_Mismatch_Exception.Date_Exception.EnrollmentYearException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.StudentIDLengthMismatchException;
import gazi.university.UMS.Student_Affairs_Exception.MissingGradeException;

import java.lang.reflect.Method;
import java.time.Year;
import java.util.*;

public abstract class Student extends Person {
    private static final AdministrativeStaff bashkan = new AdministrativeStaff
            ("12345678911", "Rahman Rejepov", "1234567891123");

    private String studentNumber = "NaN";
    private String studentName = "NaN";
    private Year enrollmentYear;
    private static double gradeCriteria; // grade criteria needed to pass particular course
    private static int totalPassedCreditsCriteria; // total credits needed to be graduated
    private final List<Integer> pastSemesters = new ArrayList<>();
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

            bashkan.addPerformedOperation("'setStudentNumber' operation of number: "
                    + hashCode() + " is successfully performed."); //adds successful operations into the list of operations
        }
    }

    public int getEnrollmentYear() {
        return this.enrollmentYear.getValue();
    }

    public void setEnrollmentYear(int enrollmentYear2) throws EnrollmentYearException {
        Year enrollmentYear = Year.of(enrollmentYear2); //Converts the input into a year data type
        if (enrollmentYear.isBefore(Year.of(2000)) || enrollmentYear.isAfter(Year.now())) {
            throw new EnrollmentYearException(EnrollmentYearException.class.getSimpleName());
        } else {
            this.enrollmentYear = enrollmentYear;

            bashkan.addPerformedOperation("'setEnrollmentYear' operation of number: "
                    + hashCode() + " is successfully performed."); //adds successful operations into the list of operations
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
        return new HashMap<>(this.transcript);
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
        int lastSem = this.pastSemesters.get(this.pastSemesters.size() - 1); // gets the value of the last index which is the last semester
        Set<CourseData> courseDataOfPrevSem = this.transcript.get(lastSem); // gets course data set of the last semester
        double[] prevGradesArray = courseDataOfPrevSem.stream().mapToDouble(CourseData::getGradeForList).toArray(); //gets grades of last semester courses
        int[] prevCreditsArray = courseDataOfPrevSem.stream().mapToInt(CourseData::getCredit).toArray(); //gets credits of courses of last semester courses
        double[] currentGradesArray = this.currentCourses.stream().mapToDouble(CourseData::getGradeForList).toArray(); // gets grades of current semester courses
        int[] currentCreditsArray = this.currentCourses.stream().mapToInt(CourseData::getCredit).toArray(); // gets credits of current semester courses
        double firstSemGPA = 0; // first semester GPA
        double secondSemGPA = 0; // second semester GPA
        for (int i = 0; i < prevGradesArray.length; i++) {
            firstSemGPA += prevGradesArray[i] * prevCreditsArray[i]; // multiplies each grade to each credit and add them to each other
        }
        for (int j = 0; j < currentCreditsArray.length; j++) {
            secondSemGPA += currentCreditsArray[j] * currentGradesArray[j]; // multiplies each grade to each credit and add them to each other
        }
        firstSemGPA = firstSemGPA / (Arrays.stream(prevCreditsArray).sum()); // first semester's GPA
        secondSemGPA = secondSemGPA / (Arrays.stream(currentCreditsArray).sum()); // second semester's GPA
        double CGPA = (firstSemGPA + secondSemGPA) / 2; // CGPA of a year
        int totalPassedCredits = this.getPassedCredits();

        bashkan.addPerformedOperation("'checkGraduation' operation of number: "
                + hashCode() + " is successfully performed."); //adds successful operations into the list of operations

        return (CGPA >= this.getGradeCriteria() && totalPassedCredits > this.getTotalPassedCreditsCriteria());
    }

    public void addCourse(CourseData courseData) throws CourseTypeAndStudentTypeInconsistencyException { //adds a course to the List
        this.currentCourses.add(courseData);
    }

    public boolean checkCourseByName(String name) {
        Optional<CourseData> courseData = this.currentCourses.stream().filter(x -> x.getCode()
                .equalsIgnoreCase(name)).findAny();
        String courseNameFromCurrent = null;
        if(courseData.isPresent()){
            courseNameFromCurrent = courseData.get().getCode();
        }

        String courseNameFromPrev = null;
        if (courseNameFromCurrent == null) {
            for (int prevSem : this.pastSemesters) {
                courseData = this.transcript.get(prevSem).stream().filter(x -> x.getCode()
                        .equalsIgnoreCase(name)).findAny();
                if (courseData.isPresent()) {
                    courseNameFromPrev = courseData.get().getCode();
                }
                if (courseNameFromPrev != null) {
                    return true;
                }
            }
        }
        return courseNameFromCurrent != null;
    }

    public boolean checkCourseByCode(String code) {
        Optional<CourseData> courseData = this.currentCourses.stream().filter(x -> x.getCode()
                .equalsIgnoreCase(code)).findAny();
        String courseCodeFromCurrent = null;
        if(courseData.isPresent()){
            courseCodeFromCurrent = courseData.get().getCode();
        }

        String courseCodeFromPrev = null;
        if (courseCodeFromCurrent == null) {
            for (int prevSem : this.pastSemesters) {
                courseData = this.transcript.get(prevSem).stream().filter(x -> x.getCode()
                        .equalsIgnoreCase(code)).findAny();
                if(courseData.isPresent()) {
                    courseCodeFromPrev = courseData.get().getCode();
                }
                if (courseCodeFromPrev != null) {
                    return true;
                }
            }
        }
        return courseCodeFromCurrent != null;
    }

    public boolean checkPassedCourseByName(String name) throws MissingGradeException { // checks if a student passed a particular course or not by searching its name
        Optional<CourseData> courseData = this.currentCourses.stream().filter(x -> x.getName()
                .equalsIgnoreCase(name)).findAny(); // tries to find that course from list of current courses

        double gradeOfCourse = 0 ;
        if(courseData.isPresent()){
            try {
                gradeOfCourse = courseData.get().getGrade();
            }catch (MissingGradeException ignored){ // we ignore this exception in order to check the transcript
            }
        }
        boolean satisfiedCriteria = gradeOfCourse >= this.getGradeCriteria(); // criteria to return true

        if (gradeOfCourse == 0 || !satisfiedCriteria) { //there is no grade in current course and not satisfactory

            for (int prevSem : this.pastSemesters) {
                courseData = this.transcript.get(prevSem).stream().filter(x -> x.getName()
                        .equalsIgnoreCase(name)).findAny(); // searches that course in transcript

                if(courseData.isPresent()){
                    try {
                        gradeOfCourse = courseData.get().getGrade();
                        satisfiedCriteria = gradeOfCourse >= this.getGradeCriteria();

                        bashkan.addPerformedOperation("'checkPassedCourseByName' operation of number: "
                                + hashCode() + " is successfully performed."); //adds successful operations into the list of operations

                        return satisfiedCriteria;
                    }catch (MissingGradeException mge){
                        System.out.println("\nBoth list of current courses and transcript are checked, but no success!\n");
                        return false;
                    }
                }
            }
        }
        return satisfiedCriteria;
    }

    public boolean checkPassedCourseByCode(String code) throws MissingGradeException { // checks if the student passed particular course or not by searching its code
        Optional<CourseData> courseData = this.currentCourses.stream().filter(x -> x.getCode().equalsIgnoreCase(code)).findAny(); // gets the grade of that course from list of current courses
        double gradeOfCourse = 0;
        if (courseData.isPresent()) {
            gradeOfCourse = courseData.get().getGrade();
        }
        boolean satisfiedCriteria = gradeOfCourse >= this.getGradeCriteria(); // criteria to return true
        if (gradeOfCourse == 0 && !satisfiedCriteria) { // if there is no grade or the grade is not set to check then...
            for (int prevSem : this.pastSemesters) {
                courseData = this.transcript.get(prevSem).stream().filter(x -> x.getCode()
                        .equalsIgnoreCase(code)).findAny(); // searches that course in transcript
                if (courseData.isPresent()) { // if that course is found
                    try {
                        gradeOfCourse = courseData.get().getGrade(); //try to get the grade
                        satisfiedCriteria = gradeOfCourse >= this.getGradeCriteria();

                        bashkan.addPerformedOperation("'checkPassedCourseByCode' operation of number: "
                                + hashCode() + " is successfully performed."); //adds successful operations into the list of operations

                        return satisfiedCriteria;
                    }catch(MissingGradeException mge){ // if there is not any grade, then catch exception
                        mge.printStackTrace();
                        return false;
                    }
                }
            }
        }
        return satisfiedCriteria;
    }
    public String toString(){
        return this.getStudentName() + " " + this.getNumber() + " " + this.getEnrollmentYear();
    }
}