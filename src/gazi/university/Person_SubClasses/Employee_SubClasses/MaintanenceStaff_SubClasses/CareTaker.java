package gazi.university.Person_SubClasses.Employee_SubClasses.MaintanenceStaff_SubClasses;

import gazi.university.Person_SubClasses.Employee;

import java.util.ArrayList;
import java.util.List;

public class CareTaker extends Employee {
    private String careTaker_name = "NaN";
    private String registry_number = "NaN";
    private String identity_number;
    private int salary;

    public CareTaker(String identity_number, String registry_number, String careTaker_name, int salary){
        super(identity_number,careTaker_name,registry_number,salary);
        this.identity_number = identity_number;
        this.registry_number = registry_number;
        this.careTaker_name = careTaker_name;
        this.salary = salary;
    }
    public String getCareTaker_name() {
        return careTaker_name;
    }

    public void setCareTaker_name(String careTaker_name) {
        this.careTaker_name = careTaker_name;
    }


    public String getCareTakerRegistry_number() {
        return registry_number;
    }
    public void setCareTakerRegistry_number(String registry_number) {
        this.registry_number = registry_number;
    }

    public String getCareTakerIdentity_number() {
        return identity_number;
    }

    public void setCareTakerIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }


    public int getCareTakerSalary() {
        return salary;
    }

    public void setCareTakerSalary(int salary) {
        this.salary = salary;
    }

    /*public void AddClassroom(Lecture_Hall lecture_hall){
        try{
            this.lecture_halls.add(lecture_hall);
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Number of Halls are exceeded!");
        }

    }

    @Override
    public boolean EarnedHisSalary(){
        int area = this.lecture_halls.stream().mapToInt(Lecture_Hall :: getArea).sum();
        if (this.lecture_halls.size() == 3 && area >= 100){
            return true;
        }
        else{
            return false;
        }
    }*/


}
