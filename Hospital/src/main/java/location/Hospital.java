package location;

import enums.BusinessType;
import exceptions.NoAvailableFloorException;
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

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Hospital extends Business implements IContainsPersonel {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final String name;
    private final String address;
    private final String contactInfo;

    private final BusinessType businessType;
    private final LinkedList<Department> departments = new LinkedList<>();
    private final Function<Department, Boolean> nullCheck = s -> (s == null);
    IToArray<LinkedList> toArrayLambda;
    ICompare<Date> compareAgeLambda;

    public Hospital(String name, String address, String contactInfo, BusinessType businessType) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.businessType = businessType;
        compareAgeLambda = (a, b) -> a.compareTo(b);
        toArrayLambda = linkedList -> linkedList.toArray();
    }

    public int getPersonnelCount() {
        return getDepartments().stream()
                .mapToInt(department -> department.getPersonnelCount())
                .sum();
    }

    private Floor findPatientFloor(Patient patient) throws PatientNotInHospitalException {
        getDepartments().stream()
                .flatMap(department -> department.getFloors().stream())
                .filter(floor -> getPatients().contains(patient))
                .findFirst();
        throw new PatientNotInHospitalException(patient + " not found");
    }

    public Floor findNurseFloor(Nurse nurse) {
        return nurse.getDepartment().getFloors().get(nurse.getDepartment().getNurseMap().get(nurse));
    }

    public ArrayList<Department> getDepartments() {
        return toArrayLambda.toArray(departments);
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
        try {
            return nurse.getDepartment().getNurseMap().put(nurse, nurse.getDepartment().nurseFindFloor().getFloorNumber()) == null;
        } catch (NoAvailableFloorException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
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
        return getDepartments().stream()
                .flatMap(department -> department.getDoctors().stream())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void logDoctors() {
        Consumer<Doctor> printName = doctor -> LOGGER.info(doctor);
        getDoctors().forEach(printName);
    }

    public Doctor getOldestDoctor() {
        ArrayList<Doctor> doctors = getDoctors();
        Doctor oldestDoctor = doctors.stream()
                .reduce((first, current) -> compareAgeLambda.compare(first.getDob(), current.getDob()) > 0 ? current : first)
                .orElse(null);
        return oldestDoctor;
    }

    public ArrayList<Nurse> getNurses() {
        return getDepartments().stream()
                .flatMap(department -> department.getNurseArray().stream())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Patient> getPatients() {
        return getDepartments().stream()
                .flatMap(department -> department.getPatients().stream())
                .collect(Collectors.toCollection(ArrayList::new));
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
