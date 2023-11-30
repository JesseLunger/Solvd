package exceptions;

public class AppointmentListEmptyException extends Exception {
    public AppointmentListEmptyException(String error) {
        super(error);
    }

}
