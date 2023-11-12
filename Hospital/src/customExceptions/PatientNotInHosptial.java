package customExceptions;


import person.Patient;

public class PatientNotInHosptial extends Exception{
    public PatientNotInHosptial(String e){
        super(e);
    }
    public PatientNotInHosptial(){super();}

}