package person;

import location.Department;
import location.Floor;
import location.Hospital;

public final class Nurse extends Employee{

    private static int nurseToPatient = 4;
    private Floor floor;
    public Nurse(String fn, String ln, Integer age, Character s, Integer id, Hospital hospital, Department department, Floor floor) {
        super(fn, ln, age, s, id, hospital, department);
        this.floor = floor;
    }

    public int getNurseToPatient(){
        return this.nurseToPatient;
    }

    public Floor getFloor(){
        return this.floor;
    }


}


