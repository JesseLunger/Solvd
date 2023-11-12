package person;

import location.Department;
import location.Floor;
import location.Hospital;
import schedule.Appointment;

import javax.print.Doc;
import java.sql.Date;

public final class Patient extends Person {
    private String symptoms;
    private Appointment appointment;


    public Patient(String firstName, String lastName, Date dateOfBirth, Character sex, String symptoms) {
        super(firstName, lastName, dateOfBirth, sex);
        this.symptoms = symptoms;
    }

    public Appointment getAppointment(){
        return this.appointment;
    }
    public void setAppointment(Appointment appointment){
        this.appointment = appointment;
    }

    public String getSymptoms(){
        return this.symptoms;
    }
    public void setSymptoms(String symptoms){
        this.symptoms = symptoms;
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
        return (this.getId() + this.getName()).hashCode();
    }
}
