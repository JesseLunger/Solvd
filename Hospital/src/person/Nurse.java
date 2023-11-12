package person;

import location.Department;
import location.Floor;
import location.Hospital;
import java.sql.Date;
public final class Nurse extends Employee {
    private final static int nurseToPatient;
    public Nurse(String firstName, String lastName, Date dateOfBirth, Character sex, Department department) {
            super(firstName, lastName, dateOfBirth, sex, department);
        }

    static {
        nurseToPatient = 4;
    }

    public int getNurseToPatient() {
        return nurseToPatient;
    }


    @Override
    public String toString(){
        return "(Nurse) " + super.toString();
    }
}


