package location;

import customExceptions.InvalidFloorNumberException;
import interfaces.IContainsPersonel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Doctor;
import person.Nurse;
import person.Patient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class Department implements IContainsPersonel {

    private static final Logger logger = LogManager.getLogger("console logger");
    private final String name;
    private final ArrayList<Floor> floors = new ArrayList<>();
    private final ArrayList<Doctor> doctors = new ArrayList<>();
    private final HashMap<Nurse, Integer> nurses = new HashMap<>();

    public Department(String name, int numFloors) {
        this.name = name;
        for (int i = 0; i < numFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    public HashMap<Nurse, Integer> getNurseMap() {
        return this.nurses;
    }

    public ArrayList<Nurse> getNurseArray() {
        return new ArrayList<>(this.nurses.keySet());
    }

    public double getFloorLoadFactor(int floor) {
        int capacity;
        try {
            capacity = this.getFloorCapacity(floor);
            if (capacity == 0) {
                return 1; // loadfactor of max capacity is 1
            }
        } catch (InvalidFloorNumberException e) {
            logger.error(e.getMessage());
            return 1;
        }
        int patientCount = floors.get(floor).getPatientCount();
        return (double) patientCount / (double) capacity;

    }

    public Floor nurseFindFloor() {
        return floors.stream()
                .min(Comparator.comparingInt(floor -> {
                    try {
                        return getFloorCapacity(floor.getFloorNumber());
                    } catch (InvalidFloorNumberException e) {
                        logger.error(e.getMessage());
                        return Integer.MAX_VALUE;
                    }
                }))
                .orElse(null);
    }

    public Floor patientFindFloor() {
        if (atCapacity()) {
            return null;
        }
        return floors.stream().min(Comparator.comparingDouble(
                        floor -> getFloorLoadFactor(floors.indexOf(floor))))
                .orElse(null);
    }

    public int getCapacity() {
        int capacity = 0;
        for (Nurse nurse : this.getNurseArray()) {
            capacity += nurse.getNurseToPatient();
        }
        return capacity;
    }

    public int getNursesByFloorCount(int floorNumber) {
        int count = 0;
        for (Map.Entry<Nurse, Integer> entry : this.nurses.entrySet()) {
            if (entry.getValue() == floorNumber) {
                count += 1;
            }
        }
        return count;
    }

    public int getFloorCapacity(int floorNumber) throws InvalidFloorNumberException {
        if (floorNumber < 0 || floorNumber >= this.getFloors().size()) {
            throw new InvalidFloorNumberException("Department->getFloorCapacity: Parameter out of range");
        }
        int capacity = 0;
        for (Map.Entry<Nurse, Integer> entry : this.nurses.entrySet()) {
            if (entry.getValue() == floorNumber) {
                capacity += entry.getKey().getNurseToPatient();
            }
        }
        return capacity;
    }

    public boolean floorAtCapacity(int floor) {
        try {
            int result = getFloorCapacity(floor);
            return true;
        } catch (InvalidFloorNumberException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public double getPatientToNurseRatio() {
        return (double) this.getPatientCount() / (double) this.getCapacity();
    }

    public int getPatientCount() {
        int totalPatients = 0;
        for (Floor floor : this.floors) {
            totalPatients += floor.getPatientCount();
        }
        return totalPatients;
    }

    public boolean addPatient(Patient patient) {
        if (atCapacity()) {
            return false;
        }
        Floor floor = patientFindFloor();
        if (floor != null) {
            return floor.getPatients().add(patient);
        }
        return false;
    }

    public boolean atCapacity() {
        return this.getCapacity() < this.getPatientCount();
    }

    public boolean removeNures(Nurse nurse) {
        return this.nurses.remove(nurse, -1);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Doctor> getDoctors() {
        return this.doctors;
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
            total += floor.getPatientCount();
        }
        return total + this.doctors.size();
    }

    public int getFloorCount() {
        return this.floors.size();
    }

    public ArrayList<Floor> getFloors() {
        return this.floors;
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
