package person;

import location.Department;
import location.Floor;
import location.Hospital;
import schedule.Appointment;

public class Patient extends Person implements IHospitalLocation {
    /*Description: represents a Person who has been admited into a hospital as long as
     * there was an available doctor to see them, and enough nurses on patients floor.
     *
     * args: hospital, department, floor: patient location in hospital
     * */

    private Doctor doctor;

    private final Hospital hospital;
    private final Department department;
    private final Floor floor;
    private int id;


    private Appointment appointment;

    public Patient(String fn, String ln, Integer age, Character s, Integer id, Hospital hospital, Department department, Floor floor, Doctor doc) {
        super(fn, ln, age, s);
        this.id = id;
        this.hospital = hospital;
        this.department = department;
        this.floor = floor;
        this.doctor = doc;
    }

    @Override
    public String getName() {
        return "(" + this.getId() + ") " + this.getFName() + " " + this.getLname();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Appointment app) {
        this.appointment = app;
    }

    public Hospital getHospital() {
        return this.hospital;
    }

    public Department getDepartment() {
        return this.department;
    }

    public Floor getFloor() {
        return this.floor;
    }
}
