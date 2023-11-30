package transport;

import exceptions.PatientNotInHospitalException;
import interfaces.IAmbulance;
import location.Department;
import location.Hospital;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Patient;
import person.Person;

import java.lang.invoke.MethodHandles;
import java.sql.Date;
import java.util.ArrayList;

public class Ambulance implements IAmbulance {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final String licence;
    private final ArrayList<Person> drivers = new ArrayList<>();
    private final Model model;
    private Patient patient;

    public Ambulance(String licence, Model model) {

        this.licence = licence;
        this.model = model;
    }

    public Person getPatient() throws PatientNotInHospitalException {
        if (patient == null) {
            throw new PatientNotInHospitalException("Ambulance: " + getLicence() + ", has no patient");
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

    public String getModel() {
        return this.model.getModelName();
    }

    @Override
    public boolean isReadyToDrive() {
        try {
            getPatient();
        } catch (PatientNotInHospitalException e) {
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
                if (HospitalDepartments.ER.getRoomName().equals(department.getName())) {
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
