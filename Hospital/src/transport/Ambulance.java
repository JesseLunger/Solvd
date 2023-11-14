package transport;

import customExceptions.PatientNotInHosptialException;
import interfaces.IAmbulance;
import location.Department;
import location.Hospital;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Patient;
import person.Person;

import java.sql.Date;
import java.util.ArrayList;

public class Ambulance implements IAmbulance {

    private static final Logger LOGGER = LogManager.getLogger("file logger");

    private final String licence;
    private final ArrayList<Person> drivers = new ArrayList<>();
    private Patient patient;

    public Ambulance(String licence) {
        this.licence = licence;
    }

    public Person getPatient() throws PatientNotInHosptialException {
        if (patient == null) {
            throw new PatientNotInHosptialException("Ambulance: " + getLicence() + ", has no patient");
        }
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean addDriver(Person person) {
        if (this.drivers.size() < 2) {
            return this.drivers.add(person);
        }
        return false;
    }

    public boolean removeDriver(Person person) {
        return this.removeDriver(person);
    }

    public String getLicence() {
        return this.licence;
    }

    @Override
    public boolean isReadyToDrive() {
        try {
            getPatient();
        } catch (PatientNotInHosptialException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return drivers.size() == 2;
    }

    @Override
    public boolean findHospital(ArrayList<Hospital> hospitals, Date date, String timeSlot) {
        if (!this.isReadyToDrive()) {
            return false;
        }
        for (Hospital hospital : hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (IAmbulance.emergencyRoom.equals(department.getName())) {
                    if (hospital.addPatient(this.patient, department, date, timeSlot)) {
                        return true;
                    }
                }
            }
        }
        setPatient(null);
        return false;
    }
}
