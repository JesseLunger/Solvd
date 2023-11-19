import exceptions.AppointmentListEmptyException;
import exceptions.AppointmentNotInListException;
import interfaces.IScheduler;
import linkedlist.LinkedList;
import location.Department;
import location.Floor;
import location.Hospital;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class Main {
    private static final Logger LOGGER = LogManager.getLogger("file logger");

    public static void main(String[] args) {










        Hospital saintJudeHospital = new Hospital("saint jude", "12345 main st", "email: 12345@12345.com");

        saintJudeHospital.addDepartment(new Department("ER", 5));
        saintJudeHospital.addDepartment(new Department("ICU", 3));
        saintJudeHospital.addDepartment(new Department("NICU", 2));
        saintJudeHospital.addDepartment(new Department("OR", 4));

        String[] timeSlots = new String[3];
        IScheduler.timeSlots.toArray(timeSlots);

        Date date = new java.sql.Date(System.currentTimeMillis());

        for (int i = 0; i < 30; i++) {
            Doctor doctor = new Doctor("Doctor", "" + i, date, 'f', saintJudeHospital.getDepartments().get(i % 4));
            saintJudeHospital.addDoctor(doctor);
        }
        LOGGER.info("\nVerifying doctors populated");
        LOGGER.info(saintJudeHospital.getDoctors());
        LOGGER.info("Total nurses: " + saintJudeHospital.getDoctors().size());
        LOGGER.info("Doctors in " + saintJudeHospital.getDepartments().get(2) + ": " + saintJudeHospital.getDepartments().get(2).getDoctors().size());


        for (int i = 0; i < 50; i++) {
            Nurse nurse = new Nurse("Nurse", "" + i, date, 'm', saintJudeHospital.getDepartments().get(i % 4));
            saintJudeHospital.addNurse(nurse);
        }
        LOGGER.info("\nVerifying nurses populated");
        LOGGER.info(saintJudeHospital.getNurses());
        LOGGER.info("Total nurses: " + saintJudeHospital.getNurses().size());
        LOGGER.info("Nurses in " + saintJudeHospital.getDepartments().get(2) + ": " + saintJudeHospital.getDepartments().get(2).getNurseArray().size());


        for (int i = 0; i < 100; i++) {
            int day = (i % 30);
            int month = (i % 12);
            int year = 2023;
            Calendar calendar = new GregorianCalendar(year, month, day);
            Date tmpDate = new java.sql.Date(calendar.getTimeInMillis());
            Patient patient = new Patient("Patient", "" + i, date, 'm', "headache");
            saintJudeHospital.addPatient(patient, saintJudeHospital.getDepartments().get(i % 4), tmpDate, timeSlots[i % timeSlots.length]);
        }

        LOGGER.info("\nVerifying patients populated");
        LOGGER.info(saintJudeHospital.getPatients());
        LOGGER.info("Total patients: " + saintJudeHospital.getPatients().size());
        LOGGER.info("Patients in " + saintJudeHospital.getDepartments().get(2) + ": " + saintJudeHospital.getDepartments().get(2).getPatients().size());


        Doctor tmpDoc = saintJudeHospital.getDoctors().get(0);

        LOGGER.info("\nVerifying removeDoc: " + tmpDoc);
        saintJudeHospital.removeDoctor(tmpDoc);
        LOGGER.info("Hospital contains doctor after removal: " + saintJudeHospital.getDoctors().contains(tmpDoc));
        saintJudeHospital.addDoctor(tmpDoc);
        LOGGER.info("Hospital contains doctor after adding back: " + saintJudeHospital.getDoctors().contains(tmpDoc));


        LOGGER.info("\nVerifying pts & nurse distrubtion on floors)");
        for (Floor floor : saintJudeHospital.getDepartments().get(0).getFloors()) {
            LOGGER.info("num pts: " + floor.getPatientCount() + ", num nurses: " + saintJudeHospital.getDepartments().get(0).getNursesByFloorCount(floor.getFLOOR_NUMBER()));
        }


        Person driver1 = new Person("driver", "1", date, 'f');
        Person driver2 = new Person("driver", "2", date, 'f');

        Patient tmpPatient = new Patient("ride", "orDie", date, 'm', "sleepy");
        Ambulance ambulance = new Ambulance("333bbb");
        ambulance.addDriver(driver1);
        ambulance.addDriver(driver2);
        ambulance.setPatient(tmpPatient);
        ArrayList<Hospital> hospitals = new ArrayList<>();
        hospitals.add(saintJudeHospital);

        LOGGER.info("\nVerifying Ambulance can find hospital: amb");
        ambulance.findHospital(hospitals, date, "night");
        LOGGER.info(tmpPatient.getAppointment().getAppointmentInformation());


        Calendar calendar = new GregorianCalendar(2023, 11, 5);
        Date tmpDate = new java.sql.Date(calendar.getTimeInMillis());
        tmpPatient.getAppointment().reschedule(tmpDate, timeSlots[0]);

        LOGGER.info("\nVerifying reschedule works");
        LOGGER.info(tmpPatient.getAppointment().getAppointmentInformation());


        LOGGER.info("\nVerifying exception handling plus custom exception");
        Calendar fakeCalendar = new GregorianCalendar(1996, 24, 8);
        Date fakeDate = new java.sql.Date(calendar.getTimeInMillis());
        Appointment fakeAppointment = new Appointment(fakeDate, "night", tmpPatient, tmpDoc, "this is fake app");
        tmpDoc.reschedule(fakeAppointment, fakeDate, "night");
        try {
            tmpDoc.removeAppointment(fakeAppointment); // should cause exception
        } catch (AppointmentNotInListException | AppointmentListEmptyException e) {

            //do Nothing already handled
        }

        try {
            tmpDoc.removeAppointment(tmpDoc.getAPPOINTMENTS().get(0)); // should not cause exception
        } catch (AppointmentNotInListException | AppointmentListEmptyException e) {
            LOGGER.info(e.getMessage());
        }

        saintJudeHospital.removePatient(tmpPatient); // should not cause exception
        saintJudeHospital.removePatient(tmpPatient); // should cause exception

        /*while this seems work in main, when I incorporate this to my Floor class
         * It makes mutliple instances of the linked list with different memory addresses
         * Even though I declare it once as a class variable*/
        LinkedList<Patient> patientLinkedList = new LinkedList<>();
        ArrayList<Patient> mypatients = saintJudeHospital.getPatients();
        for (int i = 0; i < 10; i++) {
            patientLinkedList.addItem(mypatients.get(i));
        }
        System.out.println(patientLinkedList.toArray());
    }
}

