package exceptions;

public class AppointmentNotInListException extends Exception {
    public AppointmentNotInListException(String error) {
        super(error);
    }
}
