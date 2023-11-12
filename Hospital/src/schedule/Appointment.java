package schedule;

import person.*;


import java.sql.Date;

public final class Appointment  {
    private Date date;
    private final String timeSlot;
    private final Patient patient;

    private final Doctor doctor;
    private final String appointmentInformation;


    public Appointment(Date date, String timeSlot, Patient patient, Doctor doctor, String appointmentInformation) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentInformation = appointmentInformation;

    }

    public final Date getDate() {
        return this.date;
    }

    public final String getTimeSlot() {
        return this.timeSlot;
    }

    public final Patient getPatient(){
        return this.patient;
    }

    public final boolean reschedule(Date date, String timeSlot){
        return doctor.reschedule(this, date, timeSlot);
    }
    public final String getAppointmentInformation(){return appointmentInformation;}
    @Override
    public String toString(){
        return "Appointment for: " + patient;
    }

}
