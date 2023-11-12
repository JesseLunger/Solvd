package customExceptions;

public class AppointmentListEmpty extends Exception {
    public AppointmentListEmpty(String error){
        super(error);
    }
    public AppointmentListEmpty(){super();}

}
