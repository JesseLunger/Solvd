package Person;

import Location.Department;
public class Nurse extends Employee{
    private int departmentFloor;


    @Override
    public String getName() {
        return "(nurse)" + super.getName();
    }

    public Nurse(String fn, String ln, Integer age, Character s, Department department, int departmentFloor) {
        super(fn, ln, age, s, department); // Call the constructor of the superclass (Employee)
        this.departmentFloor = departmentFloor;
    }

    public void setDepartmentFloor(Integer floor){
        this.departmentFloor = floor;
    }

    public Integer getDepartmentFloor(){
        return this.departmentFloor;
    }
}
