package interfaces;

import customExceptions.AppointmentNotInList;
import customExceptions.AppointmentListEmpty;
import person.Patient;
import schedule.Appointment;

import java.sql.Date;

import java.util.HashSet;
import java.util.Set;

public interface IScheduler {

    HashSet<String> timeSlots = new HashSet<>(Set.of("morning", "evening", "night"));

    public boolean addAppointment(Date date, String timeSlot, Patient patient);

    public boolean removeAppointment(Appointment appointment) throws AppointmentNotInList, AppointmentListEmpty;
    public boolean reschedule(Appointment appointment, Date date, String timeSlot);
}
