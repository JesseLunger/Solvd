package exceptions;

public class AmbulancesContainsNoPatientException extends Exception {

    public AmbulancesContainsNoPatientException(String e) {
        super(e);
    }

    public AmbulancesContainsNoPatientException() {
        super();
    }
}