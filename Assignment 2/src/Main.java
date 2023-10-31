
import Person.*;
import Location.*;
import Scheduling.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Hospital hos = new Hospital("saint jude");

        Department[] departments = new Department[4];
        departments[0] = new Department(hos, "ER", 5);
        departments[1] = new Department(hos, "ICU", 3);
        departments[2] = new Department(hos, "NICU", 2);
        departments[3] = new Department(hos, "OR", 4);
        hos.setDepartments(departments);

        for (int i = 0; i < 10; i++) {
            Doctor doc = new Doctor("doc", "" + i, i, 'f', departments[i % 4]);
            hos.addEmployee(doc);
        }

        Nurse[] nurses = new Nurse[400];
        for (int i = 0; i < 30; i++) {
            nurses[i] = new Nurse("nurse", "" + i, i, 'f', departments[i % 4], i % departments[i % 4].getFloors());
            hos.addEmployee(nurses[i]);
        }

        ArrayList<Doctor> availableDoctors = hos.getDoctors();

        for (int i = 0; i < 100; i++) {
            Patient tmp = new Patient("patient", "" + i, i, 'm', availableDoctors.get(i % availableDoctors.size()), departments[i % 4], i % departments[i % 4].getFloors());
            hos.addPatient(tmp);
        }
        System.out.println("\nVerifying patients populated");
        hos.prtPatientList();

        System.out.println("\nVerifying employee populated");
        hos.prtEmployeeList();

        ArrayList<Patient> pats = hos.getPatients();

        //Testing double schedule
        System.out.println("\nVerifying cannot double schedule");
        System.out.println("results of inital schedule: "  + pats.get(0).getDoctor().addAppointment(pats.get(0), (1), (20)));
        System.out.println("results of 2ndary schedule: "  + pats.get(0).getDoctor().addAppointment(pats.get(0), (1), (20)));

        System.out.println("\nVerifying Doctor for deparment: dep");
        System.out.println("doctors in ER: " + departments[0].getDoctors());

        //Testing add remove
        System.out.println("\nTesting add/remove employee(Doctors): hos");
        int tmpLength = hos.getDoctors().size();
        System.out.println("size before Removal: " + Integer.toString(tmpLength));
        hos.removeEmployee(availableDoctors.get(0));
        tmpLength = hos.getDoctors().size();
        System.out.println("size after Removal: " + Integer.toString(tmpLength));
        hos.addEmployee(availableDoctors.get(0));

        System.out.println("\nVerifying getPatientsByFloor: dep");
        ArrayList<Patient> ptsByFloor = departments[0].getPatientsByFloor(0);
        System.out.println("patients in ER: " + ptsByFloor.toString());

        System.out.println("\nVerifying freeDoctors: dep");
        Patient tmpPatient = ptsByFloor.get(0);
        System.out.println("freeDoc for Non-critical: " + tmpPatient.getDepartment().getFreeDoctors(tmpPatient, 1, 30));
        tmpPatient.setCritical(true);
        System.out.println("freeDoc for Critical: " + tmpPatient.getDepartment().getFreeDoctors(tmpPatient, 1, 30));

        System.out.println("\nVerifying freeDoctors after scheduling: dep");
        tmpPatient.setCritical(false);
        System.out.println("Scheduling pt for 1/30 for pcp: "  + pats.get(0).getDoctor().addAppointment(pats.get(0), (1), (30)));
        System.out.println("freeDoc for Non-critical (1/30, pcp already scheduled): " + tmpPatient.getDepartment().getFreeDoctors(tmpPatient, 1, 30));
        tmpPatient.setCritical(true);
        System.out.println("freeDoc for Critical: " + tmpPatient.getDepartment().getFreeDoctors(tmpPatient, 1, 30));
    }
}

