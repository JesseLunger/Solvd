package person;

import interfaces.IHospitalLocation;
import location.Department;

import java.sql.Date;

public final class Nurse extends Employee implements IHospitalLocation {

    private final static int nurseToPatient;

    static {
        nurseToPatient = 4;
    }

    public Nurse(String firstName, String lastName, Date dateOfBirth, Character sex, Department department) {
        super(firstName, lastName, dateOfBirth, sex, department);
    }

    public int getNurseToPatient() {
        return nurseToPatient;
    }

    public String getLocation(){
        return "Deparment: " + this.getDepartment() + ", Floor: " + this.getDepartment().getNurseMap().get(this);
    }

    @Override
    public String toString() {
        return "(Nurse) " + super.toString();
    }
}


