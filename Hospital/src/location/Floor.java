package location;

import interfaces.IContainsPersonel;
import person.Nurse;
import person.Patient;

import java.util.ArrayList;

public class Floor implements IContainsPersonel {
    private final Integer floorNumber;
    private final ArrayList<Patient> patients = new ArrayList<>();


    /*Commented will be deleted after PR
    * I cannot justify moving my nurse array to departments as it will decrease readabilty,
    * increase complexity, and decrease runtime. By keeping nurses within floors the process
    * of searching nurses by floor, calculating loadfactor and finding floors with minimum
    * nurses is much easier. If they are moved to department it requires a search of all nurses
    * in the department to find which ones work on a particular floor*/
    private final ArrayList<Nurse> nurses = new ArrayList<>();

    public Floor(int floorNum) {
        this.floorNumber = floorNum;
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

    public int getPatientCount() {
        return this.patients.size();
    }

    public int getNurseCount() {
        return this.nurses.size();
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
            total += Nurse.getNurseToPatient();
        }
        return total;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }


    public double getPatientToNurseRatio() {
        return (double) this.getPatientCount() / (double) this.getCapacity();
    }

    public boolean atCapacity() {
        return this.getCapacity() < this.getPatientCount();
    }

    @Override
    public String toString() {
        return "" + this.floorNumber;
    }

    @Override
    public int getPersonelCount() {
        return this.getNurseCount() + this.getPatientCount();
    }

}
