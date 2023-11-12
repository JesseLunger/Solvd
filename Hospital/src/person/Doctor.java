package person;

import interfaces.*;
import location.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import schedule.*;
import customExceptions.*;

import java.util.ArrayList;
import java.sql.Date;

public final class Doctor extends Employee implements IScheduler, IHospitalLocation {

    private static final Logger logger = LogManager.getLogger("file logger");

    private ArrayList<Appointment> appointments = new ArrayList<>();
    public Doctor(String firstName, String lastName, Date dateOfBirth, Character sex, Department department) {
        super(firstName, lastName, dateOfBirth, sex, department);
    }

    public ArrayList<Appointment> getAppointments(){
        return this.appointments;
    }

    public boolean removeAppointment(Appointment appointment) throws AppointmentNotInList, AppointmentListEmpty {
        if (!appointments.remove(appointment)) {
            throw new AppointmentNotInList("Item not found: " + appointment);
        }
        if (appointments.isEmpty()){
            throw new AppointmentListEmpty(this + " has not appointments");
        }
        return true;
    }


    @Override
    public String getLocation() {
        return "" + this.getDepartment();
    }

    @Override
    public boolean addAppointment(Date date, String timeSlot, Patient patient) {
        if (date ==null || timeSlot == null){
            return false;
        }
        if (!(IScheduler.timeSlots.contains(timeSlot))) {
            return false;
        }
        Floor floor = this.getDepartment().patientFindFloor();
        if (floor == null){
            return false;
        }
        for (Appointment appointment : this.appointments) {
            if (appointment.getDate().equals(date) && appointment.getTimeSlot().equals(timeSlot) ) {
                return false;
            }
        }
        String appointmentInformation = "Patient: " + patient + ", Department: " + getDepartment() +
                                    ", Floor: " + floor.getFloorNumber() + ", Doctor: " + this
                                    + ", Date: " + date + ", TimeSlot: " + timeSlot;
        Appointment newAppointment = new Appointment(date, timeSlot, patient, this, appointmentInformation);
        appointments.add(newAppointment);
        patient.setAppointment(newAppointment);
        floor.addPatient(patient);
        return true;
    }


    @Override
    public boolean reschedule(Appointment appointment, Date date, String timeSlot) {
        try {
            removeAppointment(appointment);
        } catch (AppointmentNotInList | AppointmentListEmpty e) {
            logger.error("Caught" + e + "exception during reschedule: " + e.getMessage());
        }
        /*This is necessary because we cannot use any upstream pointers, only floor has access to patient data*/
        for (Floor floor: this.getDepartment().getFloors()){
            for (Patient patient: floor.getPatients()){
                if (patient.equals(appointment.getPatient())){
                    floor.removePatient(patient);
                    break;
                }
            }
        }

        return this.addAppointment(date, timeSlot, appointment.getPatient());
    }

    @Override
    public String toString(){
        return "(Doctor) " + super.toString();
    }
}
