package Location;

import Person.Doctor;
import Person.Nurse;
import Person.Patient;
import Scheduling.Appointment;
import Scheduling.DailySchedule;
import Location.Hospital;

import java.util.ArrayList;
public class Department {
    private Hospital hospital;
    private String name;
    private Integer floors;

    private Integer[] numNursesArray;


    private Integer[] numPatientsArray;

    public Department(Hospital hospital, String name, Integer floors){
        this.hospital = hospital;
        this.name = name;
        this.floors = floors;
        this.numNursesArray = new Integer[floors];
        this.numPatientsArray = new Integer[floors];
        for (int i = 0; i < floors; i++){
            this.numPatientsArray[i] = 0;
            this.numNursesArray[i] = 0;
        }
    }

    public Boolean addPatient(Patient patient){
        Integer avaialableFloor = this.findFloor();
        if (avaialableFloor == -1){
            return false;
        }
        patient.setFloorNumber(avaialableFloor);
        this.numPatientsArray[avaialableFloor] += 1;
        return true;
    }
    public void removePatient(Patient patient){
        this.numPatientsArray[patient.getFloorNumber()] -= 1;
    }

    public void addNurse(Nurse nurse){
        this.numNursesArray[nurse.getDepartmentFloor()] += 1;
    }

    public void removeNurse(Nurse nurse){
        this.numNursesArray[nurse.getDepartmentFloor()] -= 1;
    }

    public Integer findFloor(){
        if (this.atCapacity()){
            return -1;
        }
        for (int floor = 0; floor < this.floors; floor++){
            if (this.numNursesArray[floor] * 4 >= this.numPatientsArray[floor] + 1){
                return floor;
            }
        }
        return -1;
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
        ArrayList<Patient> patientsOnFloor = new ArrayList<>();

        if (floor < 0 || floor > this.floors){
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

    public ArrayList<Nurse> getNurses(){
        ArrayList<Nurse> nurses = this.hospital.getNurses();
        ArrayList<Nurse> depNurses = new ArrayList<>();
        for (Nurse nurse: nurses){
            if (nurse.getDepartment() == this){
                depNurses.add(nurse);
            }
        }
        return depNurses;
    }

    public Integer numNurses(){
        Integer total = 0;
        for (Integer num: this.numNursesArray){
            total += num;
        }
        return total;
    }

    public Integer numPatients(){
        Integer total = 0;
        for (Integer num: this.numPatientsArray){
            total += num;
        }
        return total;
    }


    public Boolean atCapacity(){
        /*Description: Each nurse can only take care of maximum 4 pts, need to check to see
        * if deparment has reached patient capacity
        *
        * Args: None
        * */
        return ( (this.numNurses() * 4)  == this.numPatients() );
    }

    public ArrayList<Nurse> getNursesByFloor(Integer floor){
        ArrayList<Nurse> depNurses = new ArrayList<>();

        if (floor < 0 || floor > this.getFloors()){
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
