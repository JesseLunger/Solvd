
import Person.*;
import Location.*;
import Scheduling.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Hospital hos = new Hospital("saint jude");

        Patient[] patients = new Patient[100];

        Department[] departments = new Department[4];
        departments[0] = new Department(hos, "ER", 5);
        departments[1] = new Department(hos, "ICU", 3);
        departments[2] = new Department(hos, "NICU", 2);
        departments[3] = new Department(hos, "OR", 4);
        hos.setDepartments(departments);

        Doctor[] doctors = new Doctor[40];
        for (int i = 0; i < 40; i++) {
            doctors[i] = new Doctor("doc", "" + i, i, 'f', departments[i % 4]);
            hos.addEmployee(doctors[i]);
        }

        Nurse[] nurses = new Nurse[400];
        for (int i = 0; i < 400; i++) {
            nurses[i] = new Nurse("nurse", "" + i, i, 'f', departments[i % 4], i % departments[i % 4].getFloors());
            hos.addEmployee(nurses[i]);
        }

        Doctor[] availableDoctors = hos.getDoctors();

        for (int i = 0; i < 100; i++) {
            Patient tmp = new Patient("patient", "" + i, i, 'm', "symptoms: " + i, availableDoctors[i % availableDoctors.length], departments[i % 4], i % departments[i % 4].getFloors());
            hos.addPatient(tmp);
        }
        hos.prtPatientList();
        hos.prtEmployeeList();

        ArrayList<Patient> pats = hos.getPatients();

        System.out.println( pats.get(0).scheduleAppointment((1), (20)));
        System.out.println( pats.get(0).scheduleAppointment((1), (20)));


    }
}

