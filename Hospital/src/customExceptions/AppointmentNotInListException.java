package customExceptions;

public class AppointmentNotInListException extends Exception {
    public AppointmentNotInListException(String error) {
        super(error);
    }

    public AppointmentNotInListException() {
        super();
    }
}
