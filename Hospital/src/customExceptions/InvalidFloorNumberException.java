package customExceptions;

public class InvalidFloorNumberException extends Exception {
    public InvalidFloorNumberException(String error) {
        super(error);
    }

    public InvalidFloorNumberException() {
        super();
    }
}
