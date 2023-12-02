package location;

import exceptions.PatientNotInHospitalException;
import functionalinterfaces.ICompare;
import functionalinterfaces.IToArray;
import interfaces.IContainsPersonel;
import linkedlist.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Doctor;
import person.Nurse;
import person.Patient;
import enums.*;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Hospital extends Business implements IContainsPersonel {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final String name;
    private final String address;
    private final String contactInfo;

    private final BusinessType businessType;
    IToArray<LinkedList> toArrayLambda;
    ICompare<Date> compareAgeLambda;
    private final LinkedList<Department> departments = new LinkedList<>();
    private final Function<Department, Boolean> nullCheck = s -> (s == null);

    public Hospital(String name, String address, String contactInfo, BusinessType businessType) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.businessType = businessType;
        compareAgeLambda = (a, b) -> a.compareTo(b);
        toArrayLambda = linkedList -> linkedList.toArray();
    }

    public int getPersonnelCount() {
        int total = 0;
        for (Department department : this.departments.toArray()) {
            total += department.getPersonnelCount();
        }
        return total;
    }

    public Floor findPatientFloor(Patient patient) throws PatientNotInHospitalException {
        for (Department department : departments.toArray()) {
            for (Floor floor : department.getFloors()) {
                if (floor.getPatients().contains(patient)) {
                    return floor;
                }
            }
        }
        throw new PatientNotInHospitalException(patient + " not found");
    }

    public Floor findNurseFloor(Nurse nurse) {
        return nurse.getDepartment().getFloors().get(nurse.getDepartment().getNurseMap().get(nurse));
    }

    public ArrayList<Department> getDepartments() {
        return toArrayLambda.myApply(departments);
    }

    public boolean addDepartment(Department department) {
        return this.departments.addItem(department, nullCheck);
    }

    public boolean removeDepartment(Department department) {
        return this.departments.removeItem(department);
    }

    public boolean addDoctor(Doctor doctor) {
        return doctor.getDepartment().addDoctor(doctor);
    }

    public boolean addNurse(Nurse nurse) {
        Floor floor = nurse.getDepartment().nurseFindFloor();
        return nurse.getDepartment().getNurseMap().putIfAbsent(nurse, floor.getFloorNumber()) == null;
    }

    public boolean addPatient(Patient patient, Department department, Date date, String timeSlot) {
        if (!this.departments.contains(department) || department.atCapacity()) {
            return false;
        }
        return department.patientScheduleVisit(date, timeSlot, patient);
    }

    public boolean removeDoctor(Doctor doctor) {
        return doctor.getDepartment().removeDoctor(doctor);
    }

    public boolean removePatient(Patient patient) {
        Floor floor = null;
        try {
            floor = findPatientFloor(patient);
        } catch (PatientNotInHospitalException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (floor != null) {
                floor.removePatient(patient);
            }
        }
        return true;
    }

    public ArrayList<Doctor> getDoctors() {
        ArrayList<Doctor> allDocs = new ArrayList<>();
        for (Department department : this.departments.toArray()) {
            allDocs.addAll(department.getDoctors());
        }
        return allDocs;
    }

    public void logDoctors() {
        Consumer<Doctor> printName = doctor -> LOGGER.info(doctor);
        getDoctors().forEach(printName);
    }

    public Doctor getOldestDoctor() {
        Doctor start = null;
        for (Doctor doctor : getDoctors()) {
            if (start == null || compareAgeLambda.myApply(start.getDob(), doctor.getDob()) > 0) {
                start = doctor;
            }
        }
        return start;
    }

    public ArrayList<Nurse> getNurses() {
        ArrayList<Nurse> allNurses = new ArrayList<>();
        for (Department department : this.departments.toArray()) {
            allNurses.addAll(department.getNurseArray());
        }
        return allNurses;
    }

    public ArrayList<Patient> getPatients() {
        ArrayList<Patient> allPatients = new ArrayList<>();
        for (Department department : this.departments.toArray()) {
            allPatients.addAll(department.getPatients());
        }
        return allPatients;
    }

    @Override
    public String getContactInfo() {
        return this.contactInfo;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public boolean atCapacity() {
        Predicate<Department> predicateAtCapacity = department -> department.atCapacity();
        return departments.toArray().stream().allMatch(predicateAtCapacity);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
