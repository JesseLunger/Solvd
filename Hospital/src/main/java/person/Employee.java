package person;

import interfaces.IHospitalLocation;
import location.Department;

import java.util.Date;

public abstract class Employee extends Person implements IHospitalLocation {
    private Department department;

    public Employee(String firstName, String lastName, Date dateOfBirth, Character sex, Department department) {
        super(firstName, lastName, dateOfBirth, sex);
        this.department = department;
    }

    public String getLocation() {
        IHospitalLocation hospitalLocation = () -> "" + getDepartment();
        return hospitalLocation.getLocation();
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String getName() {
        return this.getFName() + " " + this.getLname();
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
