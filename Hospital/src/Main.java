import customExceptions.AppointmentNotInList;
import customExceptions.AppointmentListEmpty;
import location.Department;
import location.Floor;
import location.Hospital;
import person.Doctor;
import person.Nurse;
import person.Patient;
import person.Person;
import schedule.Appointment;
import transport.Ambulance;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
//        logger.info("This is an info message.");
//        logger.warn("This is a warning message.");
//        logger.error("This is an error message.", new RuntimeException("Sample Exception"));
//        logger.info("hello");

        Hospital hos = new Hospital("saint jude", "12345 main st", "email: 12345@12345.com");

        hos.addDepartment(new Department("ER", 5));
        hos.addDepartment(new Department("ICU", 3));
        hos.addDepartment(new Department("NICU", 2));
        hos.addDepartment(new Department("OR", 4));

        String[] timeSlots = {"morning", "evening", "night"};


        Date date = new java.sql.Date(System.currentTimeMillis());

        for (int i = 0; i < 30; i++) {
            Doctor doctor = new Doctor("Doctor", "" + i, date, 'f', hos.getDepartments().get(i %4));
            hos.addDoctor(doctor);
        }
        System.out.println("\nVerifying doctors populated");
        System.out.println(hos.getDoctors());
        System.out.println("Total nurses: " + hos.getDoctors().size());
        System.out.println("Doctors in " + hos.getDepartments().get(2) + ": " + hos.getDepartments().get(2).getDoctors().size());


        for (int i = 0; i < 50; i++) {
            Nurse nurse = new Nurse("Nurse", "" + i, date, 'm', hos.getDepartments().get(i % 4));
            hos.addNurse(nurse);
        }
        System.out.println("\nVerifying nurses populated");
        System.out.println(hos.getNurses());
        System.out.println("Total nurses: " + hos.getNurses().size());
        System.out.println("Nurses in " + hos.getDepartments().get(2) + ": " + hos.getDepartments().get(2).getNurseArray().size());


        for (int i = 0; i < 100; i++) {
            int day = (i % 30);
            int month = (i % 12);
            int year = 2023;
            Calendar calendar = new GregorianCalendar(year, month, day);
            Date tmpDate = new java.sql.Date(calendar.getTimeInMillis());
            Patient patient = new Patient("Patient", "" + i, date, 'm', "headache");
            hos.addPatient(patient, hos.getDepartments().get(i % 4), tmpDate, timeSlots[i % timeSlots.length]);
        }

        System.out.println("\nVerifying patients populated");
        System.out.println(hos.getPatients());
        System.out.println("Total patients: " + hos.getPatients().size());
        System.out.println("Patients in " + hos.getDepartments().get(2) + ": " + hos.getDepartments().get(2).getPatients().size());


        Doctor tmpDoc = hos.getDoctors().get(0);

        System.out.println("\nVerifying removeDoc: " + tmpDoc);
        hos.removeDoctor(tmpDoc);
        System.out.println("Hospital contains doctor after removal: " +hos.getDoctors().contains(tmpDoc));
        hos.addDoctor(tmpDoc);
        System.out.println("Hospital contains doctor after adding back: " +hos.getDoctors().contains(tmpDoc));


        System.out.println("\nVerifying pts & nurse distrubtion on floors)");
        for (Floor floor : hos.getDepartments().get(0).getFloors()) {
            System.out.println("num pts: " + floor.getPatientCount() + ", num nurses: " + hos.getDepartments().get(0).getNursesByFloorCount(floor.getFloorNumber()));
        }

        System.out.println("\n nurse map" + hos.getDepartments().get(0).getNurseMap());

        Person driver1 = new Person("driver", "1", date, 'f');
        Person driver2 = new Person("driver", "2", date, 'f');

        Patient tmpPatient = new Patient("ride", "orDie", date, 'm', "sleepy");
        Ambulance ambulance = new Ambulance("333bbb");
        ambulance.addDriver(driver1);
        ambulance.addDriver(driver2);
        ambulance.setPatient(tmpPatient);
        ArrayList<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hos);

        System.out.println("\nVerifying Ambulance can find hospital: amb");
        ambulance.findHospital(hospitals, date, "night");
        System.out.println(tmpPatient.getAppointment().getAppointmentInformation());


        Calendar calendar = new GregorianCalendar(2023, 11, 5);
        Date tmpDate = new java.sql.Date(calendar.getTimeInMillis());
        tmpPatient.getAppointment().reschedule(tmpDate, timeSlots[0]);

        System.out.println("\nVerifying reschedule works");
        System.out.println(tmpPatient.getAppointment().getAppointmentInformation());


        System.out.println("\nVerifying exception handling plus custom exception");
        Calendar fakeCalendar = new GregorianCalendar(1996, 24, 8);
        Date fakeDate = new java.sql.Date(calendar.getTimeInMillis());
        Appointment fakeAppointment = new Appointment(fakeDate, "night", tmpPatient, tmpDoc, "this is fake app");
        tmpDoc.reschedule(fakeAppointment, fakeDate, "night");
        try {
            tmpDoc.removeAppointment(fakeAppointment);
        } catch (AppointmentNotInList | AppointmentListEmpty e){
            System.out.println(e.getMessage());
        }

        try{
            tmpDoc.removeAppointment(tmpDoc.getAppointments().get(0));
        } catch (AppointmentNotInList | AppointmentListEmpty e){
            System.out.println(e.getMessage());
        }


        hos.removePatient(tmpPatient);
        hos.removePatient(tmpPatient);




    }
}

