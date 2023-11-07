package location;

import person.Doctor;
import person.Nurse;
import person.Patient;

import java.util.ArrayList;

public class Department {
    /*Description: class that contains data for doctors and floors within the department;
     *
     * Args: name: {"ER", "ICU", "NICU", "OR"}
     *       floors: num of floors
     * */
    private final String name;
    private final ArrayList<Floor> floors = new ArrayList<>();
    private final ArrayList<Doctor> doctors = new ArrayList<>();

    public Department(String name, int numFloors) {
        this.name = name;
        for (int i = 0; i < numFloors; i++) {
            Floor tmpFloor = new Floor(i);
            floors.add(tmpFloor);
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public ArrayList<Doctor> getDoctors() {
        return this.doctors;
    }

    public ArrayList<Nurse> getNurses() {
        ArrayList<Nurse> allNurs = new ArrayList<>();
        for (Floor floor : this.floors) {
            allNurs.addAll(floor.getNurses());
        }
        return allNurs;
    }

    public ArrayList<Patient> getPatients() {
        ArrayList<Patient> allPats = new ArrayList<>();
        for (Floor floor : this.getFloors()) {
            allPats.addAll(floor.getPatients());
        }
        return allPats;
    }

    public int numDoctors() {
        return this.doctors.size();
    }

    public int size() {
        int total = 0;
        for (Floor floor : this.floors) {
            total += floor.size();
        }
        return total + this.doctors.size();
    }

    public int numFloors() {
        return this.floors.size();
    }

    public ArrayList<Floor> getFloors() {
        return this.floors;
    }

    public boolean atCapacity() {
        for (Floor floor : this.floors) {
            if (!floor.atCapacity()) {
                return false;
            }
        }
        return true;
    }

    public Floor nurseFindFloor() {
        int minimum = 999999;
        Floor minFloor = null;
        for (Floor floor : this.floors) {
            if (floor.numNurses() < minimum) {
                minimum = floor.numNurses();
                minFloor = floor;
            }
        }
        return minFloor;
    }

    public Floor patientFindFloor() {
        if (this.atCapacity()) {
            return null;
        }
        float minimum = 999999f;
        Floor lowestLF = null;
        for (Floor floor : this.floors) {
            if (floor.loadFactor() < minimum) {
                minimum = floor.loadFactor();
                lowestLF = floor;
            }
        }
        return lowestLF;
    }

    public Doctor patientFindDoctor(int month, int day, String timeSlot) {
        for (Doctor doctor : this.doctors) {
            if (doctor.addApointment(month, day, timeSlot)) {
                return doctor;
            }
        }
        return null;
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
}
