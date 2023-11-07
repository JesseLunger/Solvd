package location;

import person.*;

import java.util.Stack;
import java.util.ArrayList;

public class  Hospital extends Building{
    private final String name;
    private final String address;
    private final String contactInfo;

    private  static Stack<Integer> availableIds = new Stack<>();

    private ArrayList<Department> departments = new ArrayList<>();

    public Hospital(String name, String address, String contactInfo){
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
    }

    public int size(){
        int total = 0;
        for (Department department: this.departments){
            total += department.size();
        }
        return total;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public String getContactInfo(){
        return this.contactInfo;
    }
    public String getAddress(){
        return this.address;
    }

    public boolean atCapacity(){
        for (Department department: this.departments){
            if ( !department.atCapacity()){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Department> getDepartments(){
        return this.departments;
    }

    public boolean addDepartment(Department department){
        return this.departments.add(department);
    }
    public boolean removeDepartment(Department department){
        return this.departments.remove(department);
    }

    public int findId(){
        if (this.availableIds.isEmpty()){
            return this.size();
        }
        return this.availableIds.pop();
    }

    public boolean addDoctor(Person person, Department department){
        int id = this.findId();
        Doctor newDoctor = new Doctor(person.getFName(), person.getLname(),
                person.getAge(), person.getSex(), id, this, department);
        return department.addDoctor(newDoctor);
    }

    public boolean addNurse(Person person, Department department, int nurseToPatient){
        Floor floor = department.nurseFindFloor();
        if (floor == null){
            return false;
        }
        Nurse newNurse = new Nurse(person.getFName(), person.getLname(),
                person.getAge(), person.getSex(), this.findId(), this,
                department, floor);
        return floor.addNurse(newNurse);
    }

    public boolean addPatient(Person person, Department department, int month, int day, String timeSlot){
        if (!this.departments.contains(department) || department.atCapacity()){
            return false;
        }
        Doctor doctor = department.patientFindDoctor(month, day, timeSlot);
        Floor floor = department.patientFindFloor();

        if (doctor == null || floor == null){
            return false;
        }

        Patient newPatient = new Patient(person.getFName(), person.getLname(), person.getAge(),
                person.getSex(), this.findId(), this, department, floor, doctor
                );

        floor.addPatient(newPatient);
        return true;
    }

    public boolean removePatient(Patient patient){
        this.availableIds.push(patient.getId());
        return patient.getFloor().removePatient(patient);
    }

    public boolean removeDoctor(Doctor doctor){
        this.availableIds.push(doctor.getId());
        return doctor.getDepartment().removeDoctor(doctor);
    }

    public boolean removeNurse(Nurse nurse){
        this.availableIds.push(nurse.getId());
        return nurse.getFloor().removeNures(nurse);
    }

    public ArrayList<Doctor> getDoctors(){
        ArrayList<Doctor> allDocs = new ArrayList<>();
        for (Department department: this.departments){
            allDocs.addAll(department.getDoctors());
        }
        return allDocs;
    }

    public ArrayList<Nurse> getNurses(){
        ArrayList<Nurse> allNurs = new ArrayList<>();
        for (Department department: this.departments){
            allNurs.addAll(department.getNurses());
        }
        return allNurs;
    }

    public ArrayList<Patient> getPatients(){
        ArrayList<Patient> allPats = new ArrayList<>();
        for (Department department: this.departments){
            allPats.addAll(department.getPatients());
        }
        return allPats;
    }
}
