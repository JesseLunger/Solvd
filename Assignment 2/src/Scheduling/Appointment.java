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
        /*Description: appointment class that in stored in Doctor class datascrutures, used for
        *carrying appointment time data and patient associated
        *
        * Args: patient: Patient class attempting to schedule appointment
        *       month: integer between 1-12
        *       day: integer between 1-30 (we assume all months have 30 days)
        * */
        if (patient.getDoctor().checkSchedule(patient, month, day)) {
            this.month = month;
            this.day = day;
            this.patient = patient;
        }
    }

    public void setId(Integer id) {
        /*Decription: Ids are used in other dataStructures to help organize and reduce runtimes
        *
        * Args: id, assigned by other classes.
        * */
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
