package location;

import person.Patient;

import java.util.ArrayList;

public class Floor {
    private final int FLOOR_NUMBER;
    private final ArrayList<Patient> patients = new ArrayList<>();

    public Floor(int floorNum) {
        this.FLOOR_NUMBER = floorNum;
    }

    public boolean addPatient(Patient patient) {
        return patients.add(patient);
    }

    public boolean removePatient(Patient patient) {
        return patients.remove(patient);
    }

    public int getPatientCount() {
        return this.patients.size();
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public int getFloorNumber() {
        return this.FLOOR_NUMBER;
    }

    @Override
    public String toString() {
        return "" + this.FLOOR_NUMBER;
    }

}
