package location;

import person.Doctor;
import person.Nurse;
import person.Patient;

import java.util.ArrayList;

public class Department {
    /*Decription: Department class that represents the departments within a hospital
     *
     * Args: hosptial: Hospital class the department resides in
     *       name: name of department
     *       floors: number of floors within the department
     * */


    private Hospital hospital;
    private String name;
    /*patientToNurse: the maximum amount of patients a nurse can take care of, I use 4*/
    private Floor[] floors;
    private int patientToNurse = 4;

    // Constructor
    public Department(Hospital hospital, String name, int floors) {
        this.hospital = hospital;
        this.name = name;
        this.floors = new Floor[floors];
        for (int i = 0; i < floors; i++) {
            this.floors[i] = new Floor();
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public ArrayList<Patient> getPatients() {
        /*Decription: returns arrayList of all patients with department class
         *
         * Args: none
         * */
        ArrayList<Patient> patients = new ArrayList<>();
        for (Patient patient : hospital.getPatients()) {
            if ((patient != null) && (patient.getDepartment().getName().equals(this.name))) {
                patients.add(patient);
            }
        }
        return patients;
    }

    public ArrayList<Patient> getPatientsByFloor(Integer floor) {
        /*Decription: returns an arrayList of patients within the department on a given floor
         *
         * Args: floor: integer for desired floor
         * */
        ArrayList<Patient> patientsOnFloor = new ArrayList<>();

        if (floor < 0 || floor > this.floors.length) {
            return patientsOnFloor;
        }
        ArrayList<Patient> patients = this.getPatients();

        for (Patient patient : patients) {
            // Assuming there's a getFloor() method in the Patient class
            if (patient.getFloorNumber().equals(floor)) {
                patientsOnFloor.add(patient);
            }
        }
        return patientsOnFloor;
    }

    public Integer numNurses() {
        /*Description: returns the number of nurses total on all floors
         *
         * Args: none
         * */
        Integer total = 0;
        for (Floor floor : this.floors) {
            total += floor.getNumNurses();
        }
        return total;
    }

    public Integer numPatients() {
        /*Decription: returns the number of patients on all floors
         *
         * Args: none
         * */
        Integer total = 0;
        for (Floor floor : this.floors) {
            total += floor.getNumPatients();
        }
        return total;
    }

    public boolean atCapacity() {
        /*Description: checks to see if the maximum nurse to patient ratio has been reached
         *
         * Args: None
         * */
        return (this.numPatients() > (this.numNurses() * this.patientToNurse));
    }

    public ArrayList<Doctor> getDoctors() {
        /*Description: returns Arraylist of Doctors within the department
         *
         * Args: None
         * */
        ArrayList<Doctor> depDoctors = new ArrayList<>();
        ArrayList<Doctor> doctors = this.getHospital().getDoctors();
        for (Doctor doctor : doctors) {
            if (doctor.getDepartment().equals(this)) {
                depDoctors.add(doctor);
            }
        }
        return depDoctors;
    }

    public ArrayList<Doctor> getFreeDoctors(Patient patient, Integer month, Integer day) {
        /*Decription: returns avaiable doctors. List changes if patient is critical
         *
         *Args:  patient: see Person.Patient
         *       month: integer from 1 - 12
         *       day: Integer from 1-30
         * */
        ArrayList<Doctor> depDoctors = this.getDoctors();
        ArrayList<Doctor> freeDoctors = new ArrayList<>();

        for (Doctor doctor : depDoctors) {
            if (doctor.checkSchedule(patient, month, day)) {
                freeDoctors.add(doctor);
            }
        }
        return freeDoctors;
    }

    public ArrayList<Nurse> getNurses() {
        /**/
        ArrayList<Nurse> nurses = this.hospital.getNurses();
        ArrayList<Nurse> depNurses = new ArrayList<>();
        for (Nurse nurse : nurses) {
            if (nurse.getDepartment() == this) {
                depNurses.add(nurse);
            }
        }
        return depNurses;
    }

    public ArrayList<Nurse> getNursesByFloor(Integer floor) {
        /*Description: returns a list of nurses on a floor in a department
         *
         * Args: floor: integer for desired floor
         * */
        ArrayList<Nurse> depNurses = new ArrayList<>();

        if (floor < 0 || floor > this.getNumFloors()) {
            return depNurses;
        }
        ArrayList<Nurse> nurses = this.getNurses();

        for (Nurse nurse : nurses) {
            if (nurse.getDepartmentFloor() == floor) {
                depNurses.add(nurse);
            }
        }
        return depNurses;
    }

    private Integer patientFindFloor() {
        /*Decription: Much like a hashtable this uses load factor in the form of tmp to distribute patients
         * evenly depending on nurse density by floor
         *
         * Args: None
         * */
        if (this.atCapacity()) {
            return -1;
        }
        Float minimum = 999999f;
        Integer index = 0;
        for (int i = 0; i < this.getNumFloors(); i++) {
            float tmp = (((float) this.floors[i].getNumPatients()) / (float) (this.floors[i].getNumNurses()));
            if (tmp < minimum) {
                minimum = tmp;
                index = i;
            }
        }
        return index;
    }

    public Integer nurseFindFloor() {
        /*Description: find floor with least amount of nurses
         *
         * Args: none
         * */
        Integer index = 0;
        Integer minimum = 99999;
        Integer i = 0;
        for (Floor floor : this.floors) {
            if (floor.getNumNurses() < minimum) {
                minimum = floor.getNumNurses();
                index = i;
            }
            i++;
        }
        return index;
    }

    public boolean addPatient(Patient patient) {
        /*Decription: increments patients within class dataStructures
         *
         * Args: patient: see Person.Patient
         * */
        Integer floor = this.patientFindFloor();
        if (floor == -1) {
            return false;
        }
        this.floors[floor].addPatient();
        patient.setFloorNumber(floor);
        return true;
    }

    public void removePatient(Patient patient) {
        this.floors[patient.getFloorNumber()].removePatient();
    }

    public void addNurse(Nurse nurse) {
        /*Description: increments nurses within class dataStructure
         *
         * Args: nurse: see package Person.Nurse nurse to be added
         * */
        Integer floor = this.nurseFindFloor();
        this.floors[floor].addNurse();
        nurse.setDepartmentFloor(floor);
    }

    public void removeNurse(Nurse nurse) {
        /*Description: decrements nurses within class datastruture, redivides patients to maintain nurst to patient ratio
         *
         * Args: nurse: see package Patient.Nurse nurse to be removed
         *
         * */
        this.floors[nurse.getDepartmentFloor()].removeNurse();
        Integer difference = this.floors[nurse.getDepartmentFloor()].getNumPatients() - (this.floors[nurse.getDepartmentFloor()].getNumNurses() * 4);
        ArrayList<Patient> ptOnFloor = nurse.getDepartment().getPatientsByFloor(nurse.getDepartmentFloor());
        for (int i = 0; i < difference; i++) {
            Patient tmpPatient = ptOnFloor.get(i);
            nurse.getDepartment().getHospital().removePatient(tmpPatient);
            nurse.getDepartment().getHospital().addPatient(tmpPatient);
        }
        ArrayList<Patient> patients = nurse.getDepartment().getPatientsByFloor(nurse.getDepartmentFloor());
    }

    public Hospital getHospital() {
        return this.hospital;
    }

    public Integer getNumFloors() {
        return this.floors.length;
    }

    public Floor[] getFloors() {
        return this.floors;
    }
}

