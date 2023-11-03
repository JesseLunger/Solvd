package location;

import person.Doctor;
import person.Employee;
import person.Nurse;
import person.Patient;
import schedule.Appointment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Hospital {
    /*Description: Hospital class the stores the primary data all other classes
     *
     * Args: name: string for name of hospital
     * */
    String name;
    ArrayList<Employee> employees = new ArrayList<>();
    Stack<Integer> getAvailableEIds = new Stack<>();
    ArrayList<Patient> patients = new ArrayList<>();
    Stack<Integer> getAvailablePtIds = new Stack<>();
    Integer numPatients = 0;
    HashMap<Doctor, ArrayList<Appointment>> appByDoctors = new HashMap<>();
    Department[] departments;

    public Hospital(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean addPatient(Patient patient) {
        /*Description: Add employee using id's as the index into the employee array.
         *Also save potential ids not in use. Runtime O(1) Space O(2n)
         *
         * Args: employee: Child of person Class (see in Person Package)
         * */
        if (!patient.getDepartment().addPatient(patient)) {
            return false;
        }
        if (this.getAvailablePtIds.isEmpty()) {
            patient.setId(patients.size());
            this.patients.add(patient);
        } else {
            Integer next = this.getAvailablePtIds.pop();
            patient.setId(next);
            this.patients.set(next, patient);
        }
        return true;
    }

    public void removePatient(Patient patient) {
        /*Description: Remove employee while also saving their id/index for latter use
         * Runtime O(1)
         *
         * Args: employee: Child of person Class (see in Person Package)
         * */
        patient.getDepartment().removePatient(patient);
        if (patient.getAppointment() != null) {
            patient.getDoctor().removeAppointment(patient.getAppointment());
        }
        this.getAvailablePtIds.push(patient.getId());
        this.patients.set(patient.getId(), null);

    }

    public void addEmployee(Employee employee) {
        /*Description: Add employee using id's as the index into the employee array.
         *Also save potential ids not in use. Runtime O(1) Space O(2n)
         *
         * Args: employee: Child of person Class (see in Person Package)
         * */
        if (employee instanceof Doctor && !this.appByDoctors.containsKey(employee)) {
            Doctor doctor = (Doctor) employee;
            this.appByDoctors.put(doctor, doctor.getAppointments());
        }
        if (employee instanceof Nurse) {
            employee.getDepartment().addNurse((Nurse) employee);
        }
        if (this.getAvailableEIds.isEmpty()) {
            employee.setId(employees.size());
            this.employees.add(employee);
        } else {
            Integer next = this.getAvailableEIds.pop();
            employee.setId(next);
            this.employees.set(next, employee);
        }
    }

    public void removeEmployee(Employee employee) {
        /*Description: Remove employee while also saving their id/index for latter use
         * Runtime O(1)
         *
         * Args: employee: Child of person Class (see in Person Package)
         * */
        if (employee instanceof Doctor && this.appByDoctors.containsKey(employee)) {
            Doctor doctor = (Doctor) employee;
            this.appByDoctors.remove(doctor);
        }
        if (employee instanceof Nurse) {
            employee.getDepartment().removeNurse((Nurse) employee);
        }

        this.getAvailableEIds.push(employee.getId());
        this.employees.set(employee.getId(), null);
    }

    public Department[] getDepartments() {
        return this.departments;
    }

    public void setDepartments(Department[] depList) {
        this.departments = depList;
    }

    //Debugging functions
    public void prtEmployeeList() {
        ArrayList<String> tmp = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (employee != null) {
                tmp.add(employee.getName());
            }
        }
        System.out.println(tmp);
    }

    public void prtPatientList() {
        ArrayList<String> tmp = new ArrayList<>();
        for (Patient patient : this.patients) {
            if (patient != null) {
                tmp.add(patient.getName());
            }
        }
        System.out.println(tmp);
    }

    public void prtDepartments() {
        for (Department department : this.departments) {
            System.out.println(department.getName());
        }
    }

    //Getter/Setter functions
    public ArrayList<Nurse> getNurses() {
        /*Decription: returns arrayList of nurses within hospital
         *
         * Args: None
         * */
        ArrayList<Nurse> nursesArray = new ArrayList<>();
        int index = 0;
        for (Employee employee : this.employees) {
            if ((employee != null) && (employee instanceof Nurse)) {
                nursesArray.add((Nurse) employee);
            }
            index++;
        }
        return nursesArray;
    }

    public ArrayList<Doctor> getDoctors() {
        /*Description: returns ArrayList of doctors within hosptial
         *
         * Args: None
         * */
        ArrayList<Doctor> doctorsArray = new ArrayList<>();
        int index = 0;
        for (Doctor doctor : appByDoctors.keySet()) {
            if (doctor != null) {
                doctorsArray.add(doctor);
            }
            index++;
        }
        return doctorsArray;
    }

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
