package Scheduling;

import Person.Doctor;
import Person.Patient;
import Location.Hospital;
import Location.Department;

public class Appointment {
    private Integer id;
    private Integer month;
    private Integer day;
    private Patient patient;
    private Hospital hospital ;

    public Appointment(Patient patient, Integer month, Integer day) {
        if (patient.getDoctor().checkSchedule(patient, month, day)) {
            this.month = month;
            this.day = day;
            this.patient = patient;
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getMonth() {
        return this.month;
    }

    public Integer getDay() {
        return this.day;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Hospital getHospital() {
        return this.hospital;
    }






}
