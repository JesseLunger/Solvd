package transport;

import location.Department;
import location.Hospital;
import person.Person;

import java.util.ArrayList;

public class Ambulance implements ICanTransport{

    private final String licence;
    ArrayList<Hospital> hospitals = new ArrayList<>();
    private Person person;

    private Integer drivers = 0;

    public Ambulance(String licence, Person person) {
        this.licence = licence;
        this.person = person;
    }

    public boolean addDriver(){
        if (this.drivers < 2){
            this.drivers++;
            return true;
        }
        return false;
    }

    @Override
    public boolean canDrive(){
        return drivers == 2;
    }

    public String getLicence() {
        return this.licence;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean addHosptial(Hospital hospital) {
        return this.hospitals.add(hospital);
    }

    public boolean removeHosptial(Hospital hospital) {
        return this.hospitals.remove(hospital);
    }

    @Override
    public boolean findHospital(int month, int day, String timeSlot) {
        if (!this.canDrive()){
            return false;
        }
        for (Hospital hospital : this.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getName().equals("ER")) {
                    if (hospital.addPatient(this.person, department, month, day, timeSlot)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
