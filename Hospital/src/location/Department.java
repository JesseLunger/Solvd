package location;

import interfaces.IContainsPersonel;
import person.Doctor;
import person.Nurse;
import person.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Date;


public class Department implements IContainsPersonel {
    private final String name;
    private final ArrayList<Floor> floors = new ArrayList<>();
    private final ArrayList<Doctor> doctors = new ArrayList<>();

    public Department(String name, int numFloors) {
        this.name = name;
        for (int i = 0; i < numFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Doctor> getDoctors() {
        return this.doctors;
    }

    public ArrayList<Nurse> getNurses() {
        ArrayList<Nurse> allDepNurses = new ArrayList<>();
        for (Floor floor : this.floors) {
            allDepNurses.addAll(floor.getNurses());
        }
        return allDepNurses;
    }

    public ArrayList<Patient> getPatients() {
        ArrayList<Patient> allDepartmentPatients = new ArrayList<>();
        for (Floor floor : this.getFloors()) {
            allDepartmentPatients.addAll(floor.getPatients());
        }
        return allDepartmentPatients;
    }

    public int getNumDoctors() {
        return this.doctors.size();
    }

    public int getPersonelCount() {
        int total = 0;
        for (Floor floor : this.floors) {
            total += floor.getPersonelCount();
        }
        return total + this.doctors.size();
    }

    public int getFloorCount() {
        return this.floors.size();
    }

    public boolean atCapacity() {
        for (Floor floor : this.floors) {
            if (!floor.atCapacity()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Floor> getFloors() {
        return this.floors;
    }

    public Floor nurseFindFloor() {
        return Collections.min(this.floors, Comparator.comparingInt(Floor::getNurseCount));
    }

    public Floor patientFindFloor() {
        if (atCapacity()){
            return null;
        }
        return Collections.min(this.floors, Comparator.comparingDouble(Floor::getPatientToNurseRatio));
    }

    public Boolean patientScheduleVisit(Date date, String timeSlot, Patient patient) {
        for (Doctor doctor : this.doctors) {
            if (doctor.addAppointment(date, timeSlot, patient)) {
                return true;
            }
        }
        return false;
    }

    public boolean addFloor(Floor floor) {
        return this.floors.add(floor);
    }

    public boolean removeFloor(Floor floor) {
        return this.floors.remove(floor);
    }

    public boolean addDoctor(Doctor doctor) {
        return this.doctors.add(doctor);
    }

    public boolean removeDoctor(Doctor doctor) {
        return this.doctors.remove(doctor);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
