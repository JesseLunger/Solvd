package Location;

import Person.Patient;
import Person.Nurse;

import java.util.ArrayList;
import java.util.spi.AbstractResourceBundleProvider;

public class Floors {
    /*Decription: Floor class that represents the floors in a department. The purpose
    * of the class if for staff evenly distribute patients to nurses on given floors
    *
    * Args: floors: number of floors
    *       patientToNurse: the maximum amount of patients a nurse can take care of, I use 4
    *       numNursesArray: int array that keeps track of the number of nurses at each floor
    *       numPatientArray: int array that keeps track of the number of patients at each floor
    * */
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
        /*Description: returns the number of nurses total on all floors
        *
        * Args: none
        * */
        Integer total = 0;
        for (Integer nurses: this.numNursesArray){
            total += nurses;
        }
        return total;
    }

    public Integer numPatients(){
        /*Decription: returns the number of patients on all floors
        *
        * Args: none
        * */
        Integer total = 0;
        for (Integer patients: this.numPatientsArray){
            total += patients;
        }
        return total;
    }

    public boolean atCapacity(){
        /*Description: checks to see if the maximum nurse to patient ratio has been reached
        *
        * Args: None
        * */
        return ( this.numPatients() > (this.numNurses() * this.patientToNurse) );
    }

    public boolean addPatient(Patient patient){
        /*Decription: increments patients within class dataStructures
        *
        * Args: patient: see Person.Patient
        * */
        Integer floor = this.patientFindFloor();
        if (floor == -1){
            return false;
        }
        this.numPatientsArray[floor] += 1;
        patient.setFloorNumber(floor);
        return true;
    }
    public void removePatient(Patient patient){
        /*Decription: decrements patients within class dataStructures
        *
        * Args: None
        * */
        this.numPatientsArray[patient.getFloorNumber()] -= 1;
    }

    public void addNurse(Nurse nurse){
        /*Description: increments nurses within class dataStructure
        *
        * Args: nurse: see package Person.Nurse nurse to be added
        * */
        Integer floor = this.nurseFindFloor();
        this.numNursesArray[floor] += 1;
        nurse.setDepartmentFloor(floor);
    }
    public void removeNurse(Nurse nurse){
        /*Description: decrements nurses within class datastruture, redivides patients to maintain nurst to patient ratio
        *
        * Args: nurse: see package Patient.Nurse nurse to be removed
        *
        * */
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
        /*Decription: Much like a hashtable this uses load factor in the form of tmp to distribute patients
        * evenly depending on nurse density by floor
        *
        * Args: None
        * */
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
        /*Description: find floor with least amount of nurses
        *
        * Args: none
        * */
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

    //Getter/Setter functions
    public Integer getFloors() {
        return floors;
    }
    public void setFloors(Integer floors){
        this.floors = floors;
    }
}
