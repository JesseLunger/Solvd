package Location;

import Person.Doctor;
import Person.Nurse;
import Person.Patient;

import java.util.ArrayList;
public class Department {
    /*Decription: Department class that represents the departments within a hospital
    *
    * Args: hosptial: Hospital class the department resides in
    *       name: name of department
    *       floors: number of floors within the department
    * */
    private Hospital hospital;
    private String name;
    private Floors floors;

    public Department(Hospital hospital, String name, Integer floors){
        this.hospital = hospital;
        this.name = name;
        this.floors = new Floors(floors, 4);
    }



    @Override
    public String toString(){
        return this.getName();
    }

    public ArrayList<Patient> getPatients(){
        /*Decription: returns arrayList of all patients with department class
        *
        * Args: none
        * */
        ArrayList<Patient> patients = new ArrayList<>();
        for (Patient patient: hospital.getPatients()){
            if ((patient != null) && (patient.getDepartment().getName().equals(this.name))){
                patients.add(patient);
            }
        }
        return patients;
    }

    public ArrayList<Patient> getPatientsByFloor(Integer floor) {
        /*Decription: returns an arrayList of patients within the department on a given floor
        *
         * Args: floor: integer for desired floor
        * */
        ArrayList<Patient> patientsOnFloor = new ArrayList<>();

        if (floor < 0 || floor > this.floors.getFloors()){
            return patientsOnFloor;
        }
        ArrayList<Patient> patients = this.getPatients();

        for (Patient patient : patients) {
            // Assuming there's a getFloor() method in the Patient class
            if (patient.getFloorNumber().equals(floor)) {
                patientsOnFloor.add(patient);
            }
        }
        return patientsOnFloor;
    }



    public Integer numNurses(){
        /*Description: returns the number of nurses within department class
        *
        * Args: none
        * */
        return this.floors.numNurses();
    }

    public Integer numPatients(){
        /*Decription: returns the number of patients within department class
        *
        * Args: none
        * */
        return this.floors.numPatients();
    }


    public Boolean atCapacity(){
        /*Description: Each nurse can only take care of maximum 4 pts, need to check to see
        * if deparment has reached patient capacity
        *
        * Args: None
        * */
        return (this.floors.atCapacity());
    }

    public ArrayList<Nurse> getNurses(){
        /**/
        ArrayList<Nurse> nurses = this.hospital.getNurses();
        ArrayList<Nurse> depNurses = new ArrayList<>();
        for (Nurse nurse: nurses){
            if (nurse.getDepartment() == this){
                depNurses.add(nurse);
            }
        }
        return depNurses;
    }

    public ArrayList<Nurse> getNursesByFloor(Integer floor){
        /*Description: returns a list of nurses on a floor in a department
        *
        * Args: floor: integer for desired floor
        * */
        ArrayList<Nurse> depNurses = new ArrayList<>();

        if (floor < 0 || floor > this.getNumFloors()){
            return depNurses;
        }
        ArrayList<Nurse> nurses = this.getNurses();

        for (Nurse nurse: nurses){
            if (nurse.getDepartmentFloor() == floor){
                depNurses.add(nurse);
            }
        }
        return depNurses;
    }

    public  ArrayList<Doctor> getDoctors(){
        /*Description: returns Arraylist of Doctors within the department
        *
        * Args: None
        * */
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
        /*Decription: returns avaiable doctors. List changes if patient is critical
        *
        *Args:  patient: see Person.Patient
        *       month: integer from 1 - 12
        *       day: Integer from 1-30
        * */
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
    public String getName(){
        return this.name;
    }

    public Hospital getHospital(){
        return this.hospital;
    }

    public Floors getFloors(){
        return this.floors;
    }

    public Integer getNumFloors(){
        return this.floors.getFloors();
    }




}
