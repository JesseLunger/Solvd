package customExceptions;

public class AppointmentListEmptyException extends Exception {
    public AppointmentListEmptyException(String error) {
        super(error);
    }

    public AppointmentListEmptyException() {
        super();
    }

}
