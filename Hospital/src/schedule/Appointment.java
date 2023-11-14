package schedule;

import person.Doctor;
import person.Patient;

import java.sql.Date;

public final class Appointment {
    private final String timeSlot;
    private final Patient patient;
    private final Doctor doctor;
    private final String appointmentInformation;
    private final Date date;

    public Appointment(Date date, String timeSlot, Patient patient, Doctor doctor, String appointmentInformation) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentInformation = appointmentInformation;

    }

    public Date getDate() {
        return this.date;
    }

    public String getTimeSlot() {
        return this.timeSlot;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public boolean reschedule(Date date, String timeSlot) {
        return doctor.reschedule(this, date, timeSlot);
    }

    public String getAppointmentInformation() {
        return appointmentInformation;
    }

    @Override
    public String toString() {
        return "Appointment for: " + patient;
    }
}
