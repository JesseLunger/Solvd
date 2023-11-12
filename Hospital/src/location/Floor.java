package location;

import interfaces.IContainsPersonel;
import person.Nurse;
import person.Patient;

import java.util.ArrayList;

public class Floor  {
    private final Integer floorNumber;
    private final ArrayList<Patient> patients = new ArrayList<>();

    public Floor(int floorNum) {
        this.floorNumber = floorNum;
    }

    public boolean addPatient(Patient patient){
        return patients.add(patient);
    }

    public boolean removePatient(Patient patient) {
        return patients.remove(patient);
    }

    public int getPatientCount() {
        return this.patients.size();
    }

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    @Override
    public String toString() {
        return "" + this.floorNumber;
    }

    public int getPersonelCount() {return this.getPatientCount();
    }
}
