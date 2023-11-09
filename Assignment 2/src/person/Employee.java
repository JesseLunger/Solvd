package person;

import location.Department;
import location.Hospital;

public abstract class Employee extends Person implements IHospitalLocation {
    /*Description: stores data of a hired personel at the hosptial. This includes the data of
     * its parent (Person) while also an assigned id and hospital/department.
     *
     * Args: id: unique id given to all staff
     *       hospital: Hospital of employee
     *       department: department of employee
     * */

    private Department department;

    private int id;

    public Employee(String fn, String ln, String dob, Character s, Integer id, Hospital hospital, Department department) {
        super(fn, ln, dob, s);
        this.id = id;
        this.department = department;
    }



    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public void setDepartment(Department department) {
        this.department = department;
    }


    @Override
    public Department getDepartment() {
        return this.department;
    }

    @Override
    public String getName() {
        return "(" + this.getId() + ") " + this.getFName() + " " + this.getLname();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        return obj.hashCode() == this.hashCode();
    }
}
