package Transport;

import Person.*;
import Location.*;
import Scheduling.*;

import java.security.PublicKey;
import java.util.ArrayList;

public class Ambulance {
    private Patient patient;
    private String plateNum;

    public Ambulance(String plateNum){
        this.plateNum = plateNum;
    }

    public String getPlateNum(){
        return this.plateNum;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public Patient getPatient(){
        return this.patient;
    }

    public String findHospital(ArrayList<Hospital> hospitals, Integer month, Integer day){
        this.patient.setCritical(true);
        for (Hospital hospital: hospitals){
            for (Department department: hospital.getDepartments()){
                if (department.getName().equals("ER")){
                    for (Doctor doctor: department.getDoctors()){
                        if (doctor.addAppointment(this.patient, month, day)){
                            this.patient.setDepartment(doctor.getDepartment());
                            return  "Hospital: " + patient.getDepartment().getHospital()
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
}
