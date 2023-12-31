package person;

import enums.Sex;
import interfaces.IHospitalLocation;
import schedule.Appointment;

import java.util.Date;

public final class Patient extends Person implements IHospitalLocation {

    private String symptoms;
    private Appointment appointment;
    private IHospitalLocation hospitalLocation = () -> getAppointment().getAppointmentInformation();

    public Patient(String firstName, String lastName, Date dateOfBirth, Sex sex, String symptoms) {
        super(firstName, lastName, dateOfBirth, sex);
        this.symptoms = symptoms;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getSymptoms() {
        return this.symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getLocation() {
        return hospitalLocation.getLocation();
    }

    @Override
    public String getName() {
        return "(Patient) " + this.getFName() + " " + this.getLname();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        return (this.getID() + this.getName()).hashCode();
    }
}
