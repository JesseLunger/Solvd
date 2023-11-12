package customExceptions;

public class AppointmentNotInList extends Exception {
    public AppointmentNotInList(String error){
        super(error);
    }
    public AppointmentNotInList(){super();}
}
