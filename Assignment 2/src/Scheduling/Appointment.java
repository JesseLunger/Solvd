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
        if (scheduleAppointment(patient, month, day)) {
            this.month = month;
            this.day = day;
            this.patient = patient;
        } else {
            System.out.println("Appointment creation failed due to a time conflict.");

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

    private boolean scheduleAppointment(Patient patient, Integer month, Integer day) {
        /*Description: Schedule appointment with doctor, we assume every month is 30 days and
        *a doctor can only have one appointment per day.
        *
        *
        * Args: patient: see package Person.Patient
        *
        * */
        if ((month < 0 || month > 13) || (day < 0 || day > 30) ){
            return false;
        }
        Doctor doctor = patient.getDoctor();
        for (Appointment appointment : doctor.getAppointments()) {
            if (appointment.getDay().equals(day) && appointment.getMonth().equals(month)) {
                if (appointment.getPatient().getDoctor().equals(doctor)) {
                    return false;
                }
            }
        }
        return true;
    }
}
