package gazi.university;

import gazi.university.CourseData_SubClasses.Grad_CourseData;
import gazi.university.CourseData_SubClasses.Undergrad_CourseData;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.AssistingStaff;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.TeachingStaff;
import gazi.university.Person_SubClasses.Employee_SubClasses.AdministrativeStaff;
import gazi.university.Person_SubClasses.Student;
import gazi.university.Person_SubClasses.Student_SubClasses.Undergrad_Student;
import gazi.university.UMS.Student_Affairs_Exception.MissingGradeException;

import java.util.*;

public abstract class CourseData {
    private static final AdministrativeStaff bashkan = new AdministrativeStaff
            ("12345678911", "Rahman Rejepov", "1234567891123");

    private String code = "NaN";
    private String name = "NaN";
    private int credit = 0;
    private double grade = 0;
    private double criteria = 2.0;
    private TeachingStaff instructor;
    private List<AssistingStaff> assistants = new ArrayList<>();
    private static final Set<Student> students = new HashSet<>();
    private static final HashMap<Student, Set<CourseData>> hashMap = new HashMap<>();

    public CourseData(String code, String name, int credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }
    public CourseData(String code, String name, int credit, TeachingStaff instructor ){
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.instructor = instructor;
    }
    public CourseData(String code, String name, int credit, TeachingStaff instructor, AssistingStaff assistant){
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.instructor = instructor;
        this.assistants.add(assistant);
    }
    public CourseData(String code, String name, int credit, TeachingStaff instructor, List<AssistingStaff> assistants){
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.instructor = instructor;
        this.assistants = assistants;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getGrade() throws MissingGradeException {
        if(this.grade == 0.0){
            throw new MissingGradeException(MissingGradeException.class.getSimpleName() +
                    "\n\nThere is a missing grade in " + this.getName() + " course!\n");
        }else{
            return this.grade;
        }
    }
    public double getGradeForList(){ // we just created this method so that it could function in streaming of set without any exception throwing
        if(this.grade == 0){
            return 0;
        }else{
            return this.grade;
        }
    }

    public void setGrade(double grade){ // we made this method private because we set a grade according to student value
        this.grade = grade;
    }

    public void setGradeOfStudent( Student student, double grade){
        String studentType = student.getStudentName();
        Set<CourseData> courseDataSet = hashMap.get(student);// gets list of course data of a particular student
        if(courseDataSet != null){
            Optional<CourseData> temp = courseDataSet.stream().filter(x -> x.getCode()
                    .equalsIgnoreCase(this.getCode()) && x.getName()
                    .equalsIgnoreCase(this.getName())).findAny();// gets particular course data
            if(temp.isPresent()) {
                CourseData temp2;
                if(studentType.startsWith(Undergrad_Student.class.getSimpleName())) { // if students type doesn't start with letter U... then it is Grad student
                    temp2 = new Undergrad_CourseData(this.getCode(), this.getName(), this.getCredit()); // creates undergrad course object
                }else{
                    temp2 = new Grad_CourseData(this.getCode(), this.getName(), this.getCredit()); // creates grad course object
                }
                courseDataSet.remove(temp.get()); //removes the old address of course data from the set
                temp2.setGrade(grade); // sets the grade to new address of course data
                courseDataSet.add(temp2); //adds new address of course data into the set
            }

            hashMap.replace(student, courseDataSet); // we update the hashmap by adding the updated list
        }
        else {
            System.out.println("\nThere is no course data in the list!\n");
        }
    }

    public int getCredit() {
        return this.credit;
    }

    public void setCredit(int credit){
        this.credit = credit;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public TeachingStaff getInstructor(){
        return this.instructor;
    }

    public void setInstructor(TeachingStaff instructor){
        this.instructor = instructor;
    }

    public List<AssistingStaff> getListOfAssistants(){
        return  this.assistants;
    }

    public void setListOfAssistants(List<AssistingStaff> assistants){
        this.assistants = assistants;
    }

    public void addAssistantToList(AssistingStaff assistant) {
        this.assistants.add(assistant);
    }

    public void enrollStudent(Student student){
        String studentType = student.getStudentName();
        if(hashMap.containsKey(student) && hashMap.get(student) != null){ // if there exists such a student then is also should be a course data list
            // gets course data set of that student
            Set<CourseData> tempSet = new HashSet<>(hashMap.get(student)); // we get new address of the same set of course data
            CourseData temp;
            if(studentType.startsWith(Undergrad_Student.class.getSimpleName())) { // if students type doesn't start with letter U... then it is Grad student
                temp = new Undergrad_CourseData(this.getCode(), this.getName(), this.getCredit()); // creates undergrad course object
            }else{
                temp = new Grad_CourseData(this.getCode(), this.getName(), this.getCredit()); // creates grad course object
            }
            tempSet.add(temp); // adds the course to the student's course data set
            hashMap.replace(student, tempSet); //replaces old course data with updated one
        }else {
            Set<CourseData> courseDataSet = new HashSet<>();
            CourseData temp;
            if(studentType.startsWith(Undergrad_Student.class.getSimpleName())) { // if students type doesn't start with letter U... then it is Grad student
                temp = new Undergrad_CourseData(this.getCode(), this.getName(), this.getCredit()); // creates undergrad course object
            }else{
                temp = new Grad_CourseData(this.getCode(), this.getName(), this.getCredit()); // creates grad course object
            }
            courseDataSet.add(temp); // adds to the set of course data
            hashMap.put(student, courseDataSet); // adds the new student to the hashmap with this course
            students.add(student); //adds to the set of students
        }
    }

    protected  void setGradeCriteria(double criteria2){
        this.criteria = criteria2;
    }

    protected double getGradeCriteria(){
        return this.criteria;
    }

    public Set<Student> getPassedStudents(){
        Set<Student> studentList = new HashSet<>();
        for (Student student : students) {
            Set<CourseData> courseDataSet = hashMap.get(student); // get list of course data of the student
            Optional<CourseData> temp = courseDataSet.stream().filter(x -> x.getCode().equalsIgnoreCase(this.getCode())
                    && x.getName().equalsIgnoreCase(this.getName())).findAny(); //tries to get particular course data
                if(temp.isPresent()){ // if such course exists then assign it to the courseData object next
                    try {
                        double grade = temp.get().getGrade();
                        boolean isPassed = grade >= this.getGradeCriteria();
                        if (isPassed) {
                            studentList.add(student);

                            bashkan.addPerformedOperation("'getPassedStudents' operation of number: "
                                    + hashCode() + " is successfully performed."); //adds successful operations into the list of operations
                        }
                    } catch (MissingGradeException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        return studentList;
    }

    public Set<CourseData> getCourseOfStudent(Student student){
        if(hashMap.containsKey(student) && !hashMap.get(student).isEmpty()){
            return new HashSet<>(hashMap.get(student)); // we cannot let a user make any changes to course data set by return static value
        }else{
            return null;
        }
    }

    @Override
    public String toString(){
        return this.getName() + " " + this.getCode() + " " + this.getCredit();
    }
}