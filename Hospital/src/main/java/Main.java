import exceptions.AppointmentListEmptyException;
import exceptions.AppointmentNotInListException;
import interfaces.IAmbulance;
import interfaces.IScheduler;
import linkedlist.LinkedList;
import location.Business;
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
import uniquewordfilereader.MyFileReader;
import enums.*;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.Date;
import java.util.ArrayList;
import java.util.function.Function;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        Hospital saintJudeHospital = new Hospital("saint jude", "12345 main st", "email: 12345@12345.com", BusinessType.partnership);

        saintJudeHospital.addDepartment(new Department(HospitalDepartments.ER, 5));
        saintJudeHospital.addDepartment(new Department(HospitalDepartments.ICU, 3));
        saintJudeHospital.addDepartment(new Department(HospitalDepartments.NICU, 2));
        saintJudeHospital.addDepartment(new Department(HospitalDepartments.OR, 4));

        String[] timeSlots = new String[3];
        IScheduler.timeSlots.toArray(timeSlots);
        Date date = new Date(90, 5, 5);
        System.out.println(date);
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
            Date tmpDate = new Date(year, month, day);
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
            LOGGER.info("num pts: " + floor.getPatientCount() + ", num nurses: " + saintJudeHospital.getDepartments().get(0).getNursesByFloorCount(floor.getFloorNumber()));
        }

        Person driver1 = new Person("driver", "1", date, 'f');
        Person driver2 = new Person("driver", "2", date, 'f');

        Patient tmpPatient = new Patient("ride", "orDie", date, 'm', "sleepy");
        System.out.println(tmpPatient.getFullinformation());
        Ambulance ambulance = new Ambulance("333bbb", AmbulancesModel.Ford);
        ambulance.addDriver(driver1);
        ambulance.addDriver(driver2);
        ambulance.setPatient(tmpPatient);
        ArrayList<Hospital> hospitals = new ArrayList<>();
        hospitals.add(saintJudeHospital);

        LOGGER.info("\nVerifying Ambulance can find hospital: amb");
        ambulance.findHospital(hospitals, date, "night");
        LOGGER.info(tmpPatient.getAppointment().getAppointmentInformation());


        Date tmpDate = new Date(2023, 11, 5);
        tmpPatient.getAppointment().reschedule(tmpDate, timeSlots[0]);

        LOGGER.info("\nVerifying reschedule works");
        LOGGER.info(tmpPatient.getAppointment().getAppointmentInformation());

        LOGGER.info("\nVerifying exception handling plus custom exception");
        Date fakeDate = new Date(1996, 24, 8);
        Appointment fakeAppointment = new Appointment(fakeDate, "night", tmpPatient, tmpDoc, "this is fake app");
        tmpDoc.reschedule(fakeAppointment, fakeDate, "night");
        try {
            tmpDoc.removeAppointment(fakeAppointment); // should cause exception
        } catch (AppointmentNotInListException | AppointmentListEmptyException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            tmpDoc.removeAppointment(tmpDoc.getAppointments().get(0)); // should not cause exception
        } catch (AppointmentNotInListException | AppointmentListEmptyException e) {
            LOGGER.info(e.getMessage());
        }

        saintJudeHospital.removePatient(tmpPatient); // should not cause exception
        saintJudeHospital.removePatient(tmpPatient); // should cause exception

        LOGGER.info("Vetting LinkedList");
        LinkedList<Patient> patientLinkedList = new LinkedList<>();
        ArrayList<Patient> mypatients = saintJudeHospital.getPatients();
        Function<Patient, Boolean> nullCheck = s -> (s == null);
        for (int i = 0; i < 10; i++) {
            patientLinkedList.addItem(mypatients.get(i), nullCheck);
        }
        LOGGER.info("getSize test result: " + ((patientLinkedList.toArray().size() == patientLinkedList.getSize()) ? "pass" : "fail"));
        int previousSize = patientLinkedList.toArray().size();
        patientLinkedList.removeIndex(0);
        LOGGER.info("removeIndex test result: " + ((patientLinkedList.toArray().size() + 1 == previousSize) ? "pass" : "fail"));
        Patient tmpPt = patientLinkedList.getItemAtIndex(0);
        patientLinkedList.removeItem(tmpPt);
        LOGGER.info("removeItem test result: " + (!patientLinkedList.contains(tmpPt) ? "pass" : "fail"));
        LOGGER.info("getLast test result: " + ((patientLinkedList.getLast() == patientLinkedList.toArray().get(patientLinkedList.toArray().size() - 1)) ? "pass" : "fail"));
        patientLinkedList.addAtIndex(7, tmpPt, nullCheck);
        LOGGER.info("addAtIndex test result: " + ((patientLinkedList.getItemAtIndex(7) == tmpPt) ? "pass" : "fail"));
        while (patientLinkedList.getSize() > 0) {
            patientLinkedList.removeIndex(0);
        }
        LOGGER.info("emptying List result: " + ((patientLinkedList.toArray().size() == 0) ? "pass" : "fail"));
        File myFile;
        try {
            MyFileReader fileReader = new MyFileReader("uniqueWordTestFile.txt");
            fileReader.writeUniqueWordsToFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        hospitals.get(0).logDoctors();
        Doctor oldest = new Doctor("Doctor", "oldest", new java.util.Date(80, 5, 5), 'f', saintJudeHospital.getDepartments().get(0));
        saintJudeHospital.addDoctor(oldest);
        System.out.println(hospitals.get(0).getOldestDoctor());
    }
}

