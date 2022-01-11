package gazi.university;

import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.AssistingStaff;
import gazi.university.Person_SubClasses.Employee_SubClasses.Academics_SubClasses.TeachingStaff;
import gazi.university.Person_SubClasses.Student;
import gazi.university.UMS.Student_Affairs_Exception.MissingGradeException;

import java.util.*;

public class CourseData {
    private String code = "NaN";
    private String name = "NaN";
    private int credit = 0;
    private double grade;
    private double criteria;
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

    public CourseData(){}

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getGrade() throws MissingGradeException {
        if(this.grade == 0){
            throw new MissingGradeException(MissingGradeException.class.getSimpleName() +
                    "\nThere is a missing grade in this course!\n");
        }else{
            return this.grade;
        }
    }

    private void setGrade(double grade){ // we made this method private because we set a grade according to student value
        this.grade = grade;
    }

    public void setGradeOfStudent( Student student, float grade){
        Set<CourseData> courseDataSet = hashMap.get(student);// gets list of course data of a particular student
        if(courseDataSet != null){
            CourseData temp = courseDataSet.stream().findAny().filter(x -> x.getCode()
                    .equalsIgnoreCase(this.getCode()) && x.getName()
                    .equalsIgnoreCase(this.getName())).get(); // gets particular course data
            courseDataSet.remove(temp); //removes that course data from the set
            temp.setGrade(grade);
            courseDataSet.add(temp); //adds updated course data into the set
            hashMap.replace(student, courseDataSet); // we update the hashmap by adding the updated list
        }
        System.out.println("There is no course data in the list!\n");
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
        if(hashMap.containsKey(student) && hashMap.get(student) != null){ // if there exists such a student then is also should be a course data list
            Set<CourseData> tempSet = hashMap.get(student); // gets course data set of that student
            tempSet.add(this); // adds the course to the student's course data set
            hashMap.replace(student, tempSet); //replaces old course data with updated one
        }else {
            Set<CourseData> courseDataSet = new HashSet<>();
            courseDataSet.add(this);
            hashMap.put(student, courseDataSet); // adds the new student to the hashmap with this course
            students.add(student); //adds to the set of students
        }
    }

    protected  void setGradeCriteria(double criteria2){
        criteria = criteria2;
    }

    protected double getGradeCriteria(){
        return criteria;
    }

    public Set<Student> getPassedStudents(){
        Set<Student> studentList = new HashSet<>();
        for (Student student : students) {
            Set<CourseData> courseDataSet = hashMap.get(student); // get list of course data of the student

            boolean isPassed = courseDataSet.stream().findAny().filter(x -> {
                try {
                    return x.getCode().equals(this.getCode()) &&
                            x.getName().equals(this.getName()) && x.getGrade() >= this.getGradeCriteria();
                } catch (MissingGradeException e) {
                    e.printStackTrace();
                }
                return false;
            }).isPresent();

            if (isPassed) {
                studentList.add(student);
            }
        }
        return studentList;
    }

    @Override
    public String toString(){
        return this.getName() + " " + this.getCode() + " " + this.getCredit();
    }
}