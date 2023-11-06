package person;

import location.Department;

public class Nurse extends Employee {
    /*Description: child class of Employee found in package Person. This class is used for employee who
     * are nurses
     *
     * Args: same as Employee
     * */
    private int departmentFloor;

    public Nurse(String fn, String ln, Integer age, Character s, Department department, Integer id) {
        super(fn, ln, age, s, department, id); // Call the constructor of the superclass (Employee)
    }

    /*getter/setter methods*/
    @Override
    public String getName() {
        return "(nurse: " + super.getId() + ")" + super.getName();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public Integer getDepartmentFloor() {
        return this.departmentFloor;
    }

    public void setDepartmentFloor(Integer floor) {
        /*Description: Assigns nurse to floor within department, this separates nurse class from employee
         *
         * args: floor: desired floor for nurse to work
         * */
        this.departmentFloor = floor;
    }
}
