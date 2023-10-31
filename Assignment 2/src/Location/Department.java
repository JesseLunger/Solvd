package Location;

import Person.Patient;
import Scheduling.DailySchedule;
import Location.Hospital;

import java.util.ArrayList;
import java.util.Arrays;
public class Department {

    private Hospital hospital;
    private String name;
    private Integer floors;
    private ArrayList<DailySchedule> staff;

    public String getName(){
        return this.name;
    }
    public Department(Hospital hospital, String name, Integer floors){
        this.hospital = hospital;
        this.name = name;
        this.floors = floors;
    }

    public ArrayList<Patient> getPatients(){
        ArrayList<Patient> patients = new ArrayList<>();
        for (Patient patient: hospital.getPatients()){
            if (patient.getDepartment().getName().equals(this.name)){
                patients.add(patient);
            }
        }
        return patients;
    }

    public ArrayList<Patient> getPatientsByFloor(Integer floor) {

        if (floor < 0 || floor > this.floors){
            return null;
        }
        ArrayList<Patient> patients = this.getPatients();
        ArrayList<Patient> patientsOnFloor = new ArrayList<>();

        for (Patient patient : patients) {
            // Assuming there's a getFloor() method in the Patient class
            if (patient.getFloorNumber().equals(floor)) {
                patientsOnFloor.add(patient);
            }
        }
        return patientsOnFloor;
    }

    public Hospital getHospital(){
        return this.hospital;
    }

    public Integer getFloors(){
        return this.floors;
    }


}
