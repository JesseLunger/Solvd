package interfaces;

import customExceptions.AppointmentListEmptyException;
import customExceptions.AppointmentNotInListException;
import person.Patient;
import schedule.Appointment;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public interface IScheduler {

    HashSet<String> timeSlots = new HashSet<>(Set.of("morning", "evening", "night"));

    boolean addAppointment(Date date, String timeSlot, Patient patient);

    boolean removeAppointment(Appointment appointment) throws AppointmentNotInListException, AppointmentListEmptyException;

    boolean reschedule(Appointment appointment, Date date, String timeSlot);

}
