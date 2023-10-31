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
    public Patient(String fn, String ln, Integer age,
                   Character s, String symptoms,
                   Doctor doctor, Department department, Integer floor) {
        super(fn, ln, age, s);
        this.symptoms = symptoms;
        this.doctor = doctor;
        this.department = department;
        this.floorNumber = floor;

    }


    @Override
    public String getName() {
        return "(patient)" + super.getName();
    }

    public Boolean scheduleAppointment(Integer month, Integer day){
        Appointment app = new Appointment(this, month, day);
        if (app.getMonth() != null){
            this.getDoctor().addAppointment(app);
            return true;
        }
        return  false;
    }

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
