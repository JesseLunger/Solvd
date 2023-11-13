package location;

import person.Patient;

import java.util.ArrayList;
import linkedList.*;

public class Floor {
    private final Integer floorNumber;
    private final ArrayList<Patient> patients = new ArrayList<>();

    public Floor(int floorNum) {
        this.floorNumber = floorNum;
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
        return this.floorNumber;
    }

    @Override
    public String toString() {
        return "" + this.floorNumber;
    }

}
