package location;

import person.Nurse;
import person.Patient;

import java.util.ArrayList;

public class Floor {
    /*Description: floor class that contains data for patients and nurses within floor
     *
     * Args: floorNum: floor number used for the name of floor
     * */
    private final Integer floorNum;

    private final ArrayList<Patient> patients = new ArrayList<>();
    private final ArrayList<Nurse> nurses = new ArrayList<>();

    public Floor(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public int getFloorNum() {
        return this.floorNum;
    }

    @Override
    public String toString() {
        return "" + this.floorNum;
    }

    public boolean addPatient(Patient patient) {
        if (this.atCapacity()) {
            return false;
        }
        return this.patients.add(patient);

    }

    public boolean removePatient(Patient patient) {
        return this.patients.remove(patient);
    }

    public Integer numPatients() {
        return this.patients.size();
    }

    public Integer numNurses() {
        return this.nurses.size();
    }

    public Integer size() {
        return this.numNurses() + this.numPatients();
    }

    public boolean addNurse(Nurse nurse) {
        return this.nurses.add(nurse);
    }

    public boolean removeNures(Nurse nurse) {
        return this.nurses.remove(nurse);
    }

    public ArrayList<Nurse> getNurses() {
        return this.nurses;
    }

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    public int getCapacity() {
        int total = 0;
        for (Nurse nurse : this.nurses) {
            total += nurse.getNurseToPatient();
        }
        return total;
    }

    public float loadFactor() {
        float capacity = (float) this.getCapacity();
        float numpts = (float) this.numPatients();
        return numpts / capacity;
    }

    public boolean atCapacity() {
        return this.getCapacity() < this.numPatients();
    }


}
