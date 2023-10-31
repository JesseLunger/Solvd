package Location;

import Person.Doctor;
import Person.Patient;
import Scheduling.Appointment;
import Scheduling.DailySchedule;
import Location.Hospital;

import java.util.ArrayList;
public class Department {
    private Hospital hospital;
    private String name;
    private Integer floors;

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

    public  ArrayList<Doctor> getDoctors(){
        ArrayList<Doctor> depDoctors = new ArrayList<>();
        ArrayList<Doctor> doctors = this.getHospital().getDoctors();
        for (Doctor doctor: doctors){
            if (doctor.getDepartment().equals(this)){
                depDoctors.add(doctor);
            }
        }
        return depDoctors;
    }

    public ArrayList<Doctor> getFreeDoctors(Patient patient, Integer month, Integer day) {
        ArrayList<Doctor> depDoctors = this.getDoctors();
        ArrayList<Doctor> freeDoctors = new ArrayList<>();

        for (Doctor doctor : depDoctors) {
            if (doctor.checkSchedule(patient, month, day)) {
                freeDoctors.add(doctor);
            }
        }
        return freeDoctors;
    }


    /*getter/setter methods*/
    public Hospital getHospital(){
        return this.hospital;
    }

    public Integer getFloors(){
        return this.floors;
    }

    public String getName(){
        return this.name;
    }


}
