import location.Department;
import location.Floor;
import location.Hospital;
import person.Doctor;
import person.Nurse;
import person.Patient;
import person.Person;
import transport.Ambulance;

import java.util.ArrayList;


public class Main {
    /*Description: Class used to test Assignment 2 class methods.
     *
     * */
    public static void main(String[] args) {
        /*Description: Collection of simple test functions that help verify that class methods are
         * working at intended
         *
         * Args: None
         * */

        Hospital hos = new Hospital("saint jude", "12345 main st", "email: 12345@12345.com");

        Department[] departments = new Department[4];
        departments[0] = new Department("ER", 5);
        departments[1] = new Department("ICU", 3);
        departments[2] = new Department("NICU", 2);
        departments[3] = new Department("OR", 4);
        String[] timeSlots = {"morning", "evening", "night"};

        for (Department department : departments) {
            hos.addDepartment(department);
        }
        ArrayList<Person> persons = new ArrayList<>();

        for (int i = 0; i < 300; i++) {
            Person person = new Person("person", "" + i, i, 'f');
            persons.add(person);
        }
        System.out.println(persons);
        int i;
        for (i = 0; i < 30; i++) {
            persons.get(i).setfName("doctor");
            hos.addDoctor(persons.get(i), departments[i % 4]);
        }
        ArrayList<Doctor> doctors = hos.getDoctors();
        System.out.println("\nVerifying doctors populated");
        System.out.println(doctors);
        System.out.println(doctors.size());

        for (i = i; i < 80; i++) {
            persons.get(i).setfName("nurse");
            hos.addNurse(persons.get(i), departments[i % 4], i);
        }
        ArrayList<Nurse> nurses = hos.getNurses();
        System.out.println("\nVerifying nurses populated");
        System.out.println(nurses);
        System.out.println(hos.size());

        for (i = i; i < 280; i++) {
            persons.get(i).setfName("patient");
            hos.addPatient(persons.get(i), departments[i % 4], ((i % 12) + 1), ((i % 30) + 1), timeSlots[i % timeSlots.length]);
        }
        ArrayList<Patient> patients = hos.getPatients();
        System.out.println("\nVerifying patients populated");
        System.out.println(patients);
        System.out.println(patients.size());

        System.out.println("\nVerifying removeDoc");
        Doctor tmpDoc = doctors.get(0);
        hos.removeDoctor(tmpDoc);
        System.out.println("num docs: " + hos.getDoctors().size());
        Person comeBackDoc = new Person(tmpDoc.getFName(), tmpDoc.getLname(), tmpDoc.getAge(), tmpDoc.getSex());
        hos.addDoctor(comeBackDoc, tmpDoc.getDepartment());
        System.out.println("num docs: " + hos.getDoctors().size());

        System.out.println("\nVerifying pts & nurse distrubtion on floors)");
        for (Floor floor : departments[0].getFloors()) {
            System.out.println("num pts: " + floor.numPatients() + ", num nurses: " + floor.numNurses());
        }

        Person tmpPerson = persons.get(281);
        tmpPerson.setfName("rideOrDier");
        Ambulance ambulance = new Ambulance("333bbb", tmpPerson);
        ambulance.addDriver();
        ambulance.addDriver();
        ArrayList<Hospital> hospitals = new ArrayList<>();
        ambulance.addHosptial(hos);

        System.out.println("\nVerifying Ambulance can find hospital: amb");
        ambulance.findHospital(4, 3, "night");
        for (Patient pt : departments[0].getPatients()) {
            if (pt.getFName().equals(tmpPerson.getFName()) && pt.getLname().equals(tmpPerson.getLname())) {
                System.out.println(pt + ": department: " + pt.getDepartment() + ", floor: " + pt.getFloor() + ", doctor: " + pt.getDoctor());
            }
        }
    }
}

