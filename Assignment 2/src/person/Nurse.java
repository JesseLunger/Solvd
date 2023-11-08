package person;

import location.Department;
import location.Floor;
import location.Hospital;

public final class Nurse extends Employee {
    /*Description: Nurse Class that works within a designated floor within a department.
     * The number of nurses on a floor dictates the maximum number of patients that can be
     * put on the floor.
     *
     * Args: floor: the floor the nurse works on.
     * */
    private final static int nurseToPatient;
    private Floor floor;

    public Nurse(String fn, String ln, Integer age, Character s, Integer id, Hospital hospital, Department department, Floor floor) {
        super(fn, ln, age, s, id, hospital, department);
        this.floor = floor;
    }
    static {
        nurseToPatient = 4;
    }

    public static int getNurseToPatient() {
        return nurseToPatient;
    }

    public Floor getFloor() {
        return this.floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

}


