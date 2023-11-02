package Person;

import Location.Department;
public class Nurse extends Employee{
    private int departmentFloor;

    public Nurse(String fn, String ln, Integer age, Character s, Department department) {
        super(fn, ln, age, s, department); // Call the constructor of the superclass (Employee)
    }

    /*getter/setter methods*/
    @Override
    public String getName() {

        return "(nurse: " +super.getId() + ")"  + super.getName();
    }

    @Override
    public String toString(){return this.getName();}

    public void setDepartmentFloor(Integer floor){
        this.departmentFloor = floor;
    }

    public Integer getDepartmentFloor(){
        return this.departmentFloor;
    }
}
