package exceptions;


public class PatientNotInHospitalException extends Exception {

    public PatientNotInHospitalException(String error) {
        super(error);
    }
}