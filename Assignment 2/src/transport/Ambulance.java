package transport;

import location.Department;
import location.Hospital;
import person.Doctor;
import person.Patient;

import java.util.ArrayList;

public class Ambulance {
    /*Description: Ambulance is used to transport critical patients to the ER of hospitals
     *
     * Args: patient: patient class to be transported
     *       plateNumber: licence of ambulance
     * */
    private Patient patient;
    private String plateNum;


    //Getter/Setter functions
    public Ambulance(String plateNum) {
        this.plateNum = plateNum;
    }

    public String findHospital(ArrayList<Hospital> hospitals, Integer month, Integer day) {
        /*Description: Used to locate which hospital, department, floor and doctor a critical
         * patient can be transported to
         *
         * Args: hopitals: ArrayList of possible hospitals for pt to be transported
         *       month: current month
         *       day: current day
         * */
        this.patient.setCritical(true);
        for (Hospital hospital : hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getName().equals("ER")) {
                    for (Doctor doctor : department.getDoctors()) {
                        if (doctor.addAppointment(this.patient, month, day)) {
                            this.patient.setDepartment(doctor.getDepartment());
                            return "Hospital: " + patient.getDepartment().getHospital()
                                    + ", Department: " + patient.getDepartment()
                                    + ", Floor: " + patient.getFloorNumber()
                                    + ", Doctor: " + doctor;
                        }
                    }
                }
            }
        }
        return "No hosptial Available";
    }

    public String getPlateNum() {
        return this.plateNum;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
