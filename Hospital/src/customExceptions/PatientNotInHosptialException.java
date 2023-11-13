package customExceptions;


public class PatientNotInHosptialException extends Exception {
    public PatientNotInHosptialException(String e) {
        super(e);
    }

    public PatientNotInHosptialException() {
        super();
    }

}