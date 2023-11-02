package Location;

import Person.Patient;
import Person.Nurse;

import java.util.ArrayList;
import java.util.spi.AbstractResourceBundleProvider;

public class Floors {
    private Integer floors;
    private Integer patientToNurse;
    private Integer[] numNursesArray;
    private Integer[] numPatientsArray;


    public Floors(Integer numFloors, Integer patientToNurse){
        this.floors = numFloors;
        this.patientToNurse = patientToNurse;
        this.numNursesArray = new Integer[numFloors];
        this.numPatientsArray = new Integer[numFloors];
        for (int i = 0; i < numFloors; i++){
            this.numPatientsArray[i] = 0;
            this.numNursesArray[i] = 0;
        }
    }

    public Integer numNurses(){
        Integer total = 0;
        for (Integer nurses: this.numNursesArray){
            total += nurses;
        }
        return total;
    }

    public Integer numPatients(){
        Integer total = 0;
        for (Integer patients: this.numPatientsArray){
            total += patients;
        }
        return total;
    }

    public boolean atCapacity(){
        return ( this.numPatients() > (this.numNurses() * this.patientToNurse) );
    }

    public boolean addPatient(Patient patient){
        Integer floor = this.patientFindFloor();
        if (floor == -1){
            return false;
        }
        this.numPatientsArray[floor] += 1;
        patient.setFloorNumber(floor);
        return true;
    }
    public void removePatient(Patient patient){
        this.numPatientsArray[patient.getFloorNumber()] -= 1;
    }

    public void addNurse(Nurse nurse){
        Integer floor = this.nurseFindFloor();
        this.numNursesArray[floor] += 1;
        nurse.setDepartmentFloor(floor);
    }
    public void removeNurse(Nurse nurse){
        this.numNursesArray[nurse.getDepartmentFloor()] -= 1;
        Integer difference = this.numPatientsArray[nurse.getDepartmentFloor()] - (this.numNursesArray[nurse.getDepartmentFloor()] * 4 );
        ArrayList<Patient> ptOnFloor = nurse.getDepartment().getPatientsByFloor(nurse.getDepartmentFloor());
        for (int i = 0; i < difference; i++){
            Patient tmpPatient = ptOnFloor.get(i);
            nurse.getDepartment().getHospital().removePatient(tmpPatient);
            nurse.getDepartment().getHospital().addPatient(tmpPatient);
        }
        ArrayList<Patient> patients = nurse.getDepartment().getPatientsByFloor(nurse.getDepartmentFloor());
    }

    private Integer patientFindFloor(){
        if (this.atCapacity()){
            return -1;
        }
        Float minimum = 999999f;
        Integer index = 0;
        for (int i = 0; i < this.floors; i++){


            float tmp = ( ((float)this.numPatientsArray[i]) / (float) (this.numNursesArray[i]));
            if ( tmp < minimum ){
                minimum = tmp;
                index = i;
            }
        }
        return index;
    }

    public Integer nurseFindFloor(){
        Integer index = 0;
        Integer minimum = 99999;
        Integer i = 0;
        for (Integer nurses: this.numNursesArray){
            if (nurses < minimum){
                minimum = nurses;
                index = i;
            }
            i++;
        }
        return index;
    }

    public Integer getFloors() {
        return floors;
    }
    public void setFloors(Integer floors){
        this.floors = floors;
    }
}
