package Person;


import Location.Department;
import Location.Hospital;
import Scheduling.Appointment;
import java.security.PublicKey;

public class Patient extends Person {
    private Integer id;
    private String symptoms;
    private Doctor doctor;
    private Department department;
    private Integer floorNumber;
    private boolean critical = false;
    public Patient(String fn, String ln, Integer age,
                   Character s,
                   Doctor doctor, Department department, Integer floor) {
        /*Description: Child of Person Class. This class is used for most lookups to determine doctor and nurse
        *
        * */
        super(fn, ln, age, s);
        this.doctor = doctor;
        this.department = department;
        this.floorNumber = floor;

    }





    @Override
    public String getName() {
        return "(patient)" + super.getName();
    }

    @Override
    public String toString(){return this.getName();}

    public void setSymptoms(String symptoms){
        this.symptoms = symptoms;
    }
    public String getSymptoms(){
        return this.symptoms;
    }

    public void setCritical(boolean critical){
        this.critical = critical;
    }
    public boolean getCritical(){
        return this.critical;
    }

    /*getter/setter functions*/
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public Doctor getDoctor(){
        return this.doctor;
    }

    public Hospital getHospital(){
        return this.department.getHospital();
    }

    public Department getDepartment(){
        return this.department;
    }

    public Integer getFloorNumber(){
        return this.floorNumber;
    }


}
