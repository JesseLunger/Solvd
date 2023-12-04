package person;

import enums.Sex;
import interfaces.IHospitalLocation;
import location.Department;

import java.sql.Date;

public final class Nurse extends Employee implements IHospitalLocation {

    private final static int NURSE_TO_PATIENT;

    static {
        NURSE_TO_PATIENT = 4;
    }

    public Nurse(String firstName, String lastName, Date dateOfBirth, Sex sex, Department department) {
        super(firstName, lastName, dateOfBirth, sex, department);
    }

    public int getNurseToPatient() {
        return NURSE_TO_PATIENT;
    }

    public String getLocation() {
        return "Deparment: " + this.getDepartment() + ", Floor: " + this.getDepartment().getNurseMap().get(this);
    }

    @Override
    public String toString() {
        return "(Nurse) " + super.toString();
    }
}


