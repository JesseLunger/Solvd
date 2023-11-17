package location;

import person.Patient;

import java.util.ArrayList;

public class Floor {
    private final Integer FLOOR_NUMBER;
    private final ArrayList<Patient> PATIENTS = new ArrayList<>();

    public Floor(int floorNum) {
        this.FLOOR_NUMBER = floorNum;
    }

    public boolean addPatient(Patient patient) {
        return PATIENTS.add(patient);
    }

    public boolean removePatient(Patient patient) {
        return PATIENTS.remove(patient);
    }

    public int getPatientCount() {
        return this.PATIENTS.size();
    }

    public ArrayList<Patient> getPATIENTS() {
        return PATIENTS;
    }

    public int getFLOOR_NUMBER() {
        return this.FLOOR_NUMBER;
    }

    @Override
    public String toString() {
        return "" + this.FLOOR_NUMBER;
    }

}
