package exceptions;

public class AmbulancesContainsNoPatientException extends Exception {

    public AmbulancesContainsNoPatientException(String error) {
        super(error);
    }
}