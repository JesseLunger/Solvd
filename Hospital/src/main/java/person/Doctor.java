package person;

import enums.Month;
import enums.Sex;
import exceptions.AppointmentListEmptyException;
import exceptions.AppointmentNotInListException;
import exceptions.NoAvailableFloorException;
import interfaces.IHospitalLocation;
import interfaces.IScheduler;
import location.Department;
import location.Floor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import schedule.Appointment;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Predicate;

public final class Doctor extends Employee implements IScheduler, IHospitalLocation {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ArrayList<Appointment> appointments = new ArrayList<>();

    public Doctor(String firstName, String lastName, Date dateOfBirth, Sex sex, Department department) {
        super(firstName, lastName, dateOfBirth, sex, department);
    }

    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }

    public boolean removeAppointment(Appointment appointment) throws AppointmentNotInListException, AppointmentListEmptyException {
        if (!appointments.remove(appointment)) {
            throw new AppointmentNotInListException("Item not found: " + appointment);
        }
        if (appointments.isEmpty()) {
            throw new AppointmentListEmptyException(this + " has not appointments");
        }
        return true;
    }

    @Override
    public String getLocation() {
        return super.getLocation();
    }

    @Override
    public boolean addAppointment(Date date, String timeSlot, Patient patient) {
        Floor floor;
        try {
            floor = this.getDepartment().patientFindFloor();
        } catch (NoAvailableFloorException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
        Predicate<Month> isValidDate = month -> (month.getMonthNumber() == date.getMonth() + 1) && (date.getDay() > month.getNumberOfDays());
        Predicate<Appointment> isValidAppointment = appointment -> appointment.getDate().equals(date) && appointment.getTimeSlot().equals(timeSlot);

        if (date == null
                || timeSlot == null
                || floor == null
                || !(IScheduler.timeSlots.contains(timeSlot))
                || Arrays.stream(Month.values()).anyMatch(isValidDate)
                || getAppointments().stream().anyMatch(isValidAppointment)) {
            return false;
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
            for (Floor floor : this.getDepartment().getFloors()) {
                for (Patient patient : floor.getPatients()) {
                    if (patient.equals(appointment.getPatient())) {
                        floor.removePatient(patient);
                        break;
                    }
                }
            }
            return this.addAppointment(date, timeSlot, appointment.getPatient());
        } catch (AppointmentNotInListException | AppointmentListEmptyException e) {
            LOGGER.error("Caught " + e + "exception during reschedule: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "(Doctor) " + super.toString();
    }
}
