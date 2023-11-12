package location;


import customExceptions.PatientNotInHosptial;
import interfaces.IContainsPersonel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Doctor;
import person.Nurse;
import person.Patient;

import java.util.ArrayList;
import java.sql.Date;

public class Hospital extends Business implements IContainsPersonel {
    private static final Logger logger = LogManager.getLogger("root");
    private final String name;
    private final String address;
    private final String contactInfo;
    private final ArrayList<Department> departments = new ArrayList<>();

    public Hospital(String name, String address, String contactInfo) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
    }

    public int getPersonelCount() {
        int total = 0;
        for (Department department : this.departments) {
            total += department.getPersonelCount();
        }
        return total;
    }

    /*Due to no upstream pointers we must search from the root*/
    public Floor findPatientFloor(Patient patient) throws PatientNotInHosptial {
        for (Department department: departments){
            for (Floor floor: department.getFloors()){
                if (floor.getPatients().contains(patient)){
                    return floor;
                }
            }
        }
        throw new PatientNotInHosptial(patient + " not found");
    }


    public Floor findNurseFloor(Nurse nurse){
        return nurse.getDepartment().getFloors().get(nurse.getDepartment().getNurseMap().get(nurse));
    }

    public ArrayList<Department> getDepartments() {
        return this.departments;
    }

    public boolean addDepartment(Department department) {
        return this.departments.add(department);
    }

    public boolean removeDepartment(Department department) {
        return this.departments.remove(department);
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
        return  department.patientScheduleVisit(date, timeSlot, patient);
    }

    public boolean removeDoctor(Doctor doctor){
        return doctor.getDepartment().removeDoctor(doctor);
    }



    public boolean removePatient(Patient patient) {
        Floor floor = null;
        try {
            floor = findPatientFloor(patient);
        } catch (PatientNotInHosptial e) {
            logger.error(e.getMessage());
        } finally {
            if (floor != null) {
                floor.removePatient(patient);
            }
        }

        return true;
    }


    public ArrayList<Doctor> getDoctors() {
        ArrayList<Doctor> allDocs = new ArrayList<>();
        for (Department department : this.departments) {
            allDocs.addAll(department.getDoctors());
        }
        return allDocs;
    }

    public ArrayList<Nurse> getNurses() {
        ArrayList<Nurse> allNurses = new ArrayList<>();
        for (Department department : this.departments) {
            allNurses.addAll(department.getNurseArray());
        }
        return allNurses;
    }

    public ArrayList<Patient> getPatients() {
        ArrayList<Patient> allPatients = new ArrayList<>();
        for (Department department : this.departments) {
            allPatients.addAll(department.getPatients());
        }
        return allPatients;
    }

    @Override
    public String toString() {
        return this.name;
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
        for (Department department : this.departments) {
            if (!department.atCapacity()) {
                return false;
            }
        }
        return true;
    }
}
