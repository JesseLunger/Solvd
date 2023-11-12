package transport;

import interfaces.ITransportable;
import location.Department;
import location.Hospital;
import person.Patient;
import person.Person;

import java.sql.Date;
import java.util.ArrayList;

public class Ambulance implements ITransportable {

    private final String licence;
    ArrayList<Person> drivers = new ArrayList<>();
    private Patient patient;

    public Ambulance(String licence) {
        this.licence = licence;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }
    public Person getPatient() {
        return this.patient;
    }



    public boolean addDriver(Person person){
        if (this.drivers.size() < 2){
            return this.drivers.add(person);
        }
        return false;
    }

    public boolean removeDriver(Person person){
        return this.removeDriver(person);
    }

    public String getLicence() {
        return this.licence;
    }


    public void setPerson(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean canDrive(){
        return drivers.size() == 2;
    }

    @Override
    public boolean findHospital(ArrayList<Hospital> hospitals, Date date, String timeSlot) {
        if (!this.canDrive()){
            return false;
        }
        for (Hospital hospital : hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (ITransportable.emergencyRoom.equals(department.getName())) {
                    if (hospital.addPatient(this.patient, department, date, timeSlot)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
