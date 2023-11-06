import location.Department;
import location.Floor;
import location.Hospital;
import person.Doctor;
import person.Nurse;
import person.Patient;
import transport.Ambulance;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        /*Description: Collection of simple test functions that help verify that class methods are
         * working at intended
         *
         * Args: None
         * */

        Hospital hos = new Hospital("saint jude", "12345 main st", "email: 12345@12345.com");

        Department[] departments = new Department[4];
        departments[0] = new Department(hos, "ER", 5);
        departments[1] = new Department(hos, "ICU", 3);
        departments[2] = new Department(hos, "NICU", 2);
        departments[3] = new Department(hos, "OR", 4);
        hos.setDepartments(departments);

        for (int i = 0; i < 10; i++) {
            Doctor doc = new Doctor("doc", "" + i, i, 'f', departments[i % 4], hos.getNextIdEmp());
            hos.addEmployee(doc);
        }

        Nurse[] nurses = new Nurse[400];
        for (int i = 0; i < 30; i++) {
            nurses[i] = new Nurse("nurse", "" + i, i, 'f', departments[i % 4], hos.getNextIdEmp());
            hos.addEmployee(nurses[i]);
        }

        ArrayList<Doctor> availableDoctors = hos.getDoctors();

        for (int i = 0; i < 70; i++) {
            Patient tmp = new Patient("patient", "" + i, i, 'm', availableDoctors.get(i % availableDoctors.size()), hos.getNextIdPt());
            hos.addPatient(tmp);
        }
        System.out.println("\nVerifying patients populated");
        hos.prtPatientList();
        System.out.println("patient length: " + hos.getPatients().size());

        System.out.println("\nVerifying employee populated");
        hos.prtEmployeeList();
        System.out.println("length doctors: " + hos.getDoctors().size());
        System.out.println("length nurses: " + hos.getNurses().size());

        //Testing add remove
        System.out.println("\nTesting add/remove employee(Doctors): hos");
        int tmpLength = hos.getDoctors().size();
        System.out.println("size before Removal: " + Integer.toString(tmpLength));
        hos.removeEmployee(availableDoctors.get(0));
        tmpLength = hos.getDoctors().size();
        System.out.println("size after Removal: " + Integer.toString(tmpLength));
        hos.addEmployee(availableDoctors.get(0));


        ArrayList<Patient> pats = hos.getPatients();
        //Testing double schedule
        System.out.println("\nVerifying cannot double schedule: doc");
        System.out.println("results of inital schedule: " + pats.get(0).getDoctor().addAppointment(pats.get(0), (1), (20)));
        System.out.println("results of 2ndary schedule: " + pats.get(0).getDoctor().addAppointment(pats.get(0), (1), (20)));

        System.out.println("\nVerifying getDoctors for deparment: dep");
        System.out.println("doctors in ER: " + departments[0].getDoctors());

        System.out.println("\nVerifying getNurses for department: dep");
        System.out.println("nurese in ER: " + departments[0].getNurses());

        System.out.println("\nVerifying getPatients for department: dep");
        System.out.println("patients in ER: " + departments[0].getPatients());

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
        System.out.println("Scheduling pt for 1/30 for pcp: " + tmpPatient.getDoctor().addAppointment(pats.get(0), (1), (30)));
        System.out.println("freeDoc for Non-critical (1/30, pcp already scheduled): " + tmpPatient.getDepartment().getFreeDoctors(tmpPatient, 1, 30));
        tmpPatient.setCritical(true);
        System.out.println("freeDoc for Critical: " + tmpPatient.getDepartment().getFreeDoctors(tmpPatient, 1, 30));


        Department er = departments[0];
        System.out.println("\nVerifying getNurses: dep");
        System.out.println(er.getName() + ": " + er.getNurses());

        System.out.println("\nVerifying getNursesByFloor: dep and Patients do not exceed capacity by floor (1n to 4p)");
        for (int i = 0; i < er.getNumFloors(); i++) {
            System.out.println(er.getName() + ": patients floor" + i + ": Patients:" + er.getPatientsByFloor(i).size() + ", Nurses :" + er.getNursesByFloor(i).size());
        }
        
        hos.removePatient(tmpPatient);
        System.out.println("\nVerifying getNursesByFloor after pt removal: floor");
        for (int i = 0; i < er.getNumFloors(); i++) {
            System.out.println(er.getName() + ": patients floor" + i + ": Patients:" + er.getPatientsByFloor(i).size() + ", Nurses :" + er.getNursesByFloor(i).size());
        }

        hos.removeEmployee(er.getNurses().get(3));
        System.out.println("\nVerifying remove nurse(floor 3) + pt redistribution: floor");
        for (int i = 0; i < er.getNumFloors(); i++) {
            System.out.println(er.getName() + ": patients floor" + i + ": Patients:" + er.getPatientsByFloor(i).size() + ", Nurses :" + er.getNursesByFloor(i).size());
        }

        Ambulance ambulance = new Ambulance("333bbb");
        Patient ambPat = new Patient("amb", "bulance", 22, 'f', hos.getDoctors().get(0), hos.getNextIdPt());
        ambulance.setPatient(ambPat);
        ArrayList<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hos);

        System.out.println("\nVerifying Ambulance can find hospital: amb");
        System.out.println(ambulance.findHospital(hospitals, 4, 20));
        for (int i = 0; i < er.getNumFloors(); i++) {
            System.out.println(er.getName() + ": patients floor" + i + ": Patients:" + er.getPatientsByFloor(i).size() + ", Nurses :" + er.getNursesByFloor(i).size());
        }
    }
}

