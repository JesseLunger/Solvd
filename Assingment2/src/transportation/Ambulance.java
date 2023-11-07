package transportation;

import location.Department;
import person.Patient;
import person.Person;

import java.security.PublicKey;

import location.Hospital;
import java.util.ArrayList;
import java.util.Optional;

public class Ambulance {

    private final String licence;
    private Person person;

    ArrayList<Hospital> hospitals = new ArrayList<>();

    public Ambulance(String licence, Person person){
        this.licence = licence;
        this.person = person;
    }

    public String getLicence(){
        return this.licence;
    }

    public Person getPerson(){
        return this.person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public boolean addHosptial(Hospital hospital){
        return this.hospitals.add(hospital);
    }

    public boolean removeHosptial(Hospital hospital){
        return this.removeHosptial(hospital);
    }

    public boolean findHospital(int month, int day, String timeSlot){
        for (Hospital hospital: this.hospitals){
            for (Department department: hospital.getDepartments()){
                if (department.getName().equals("ER")){
                    if (hospital.addPatient(this.person, department, month, day, timeSlot)){
                        return true;
                    }
                }
            }
        }
        return false;
    }



}
