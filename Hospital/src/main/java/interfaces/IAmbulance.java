package interfaces;

import location.Hospital;

import java.sql.Date;
import java.util.ArrayList;

public interface IAmbulance {

    boolean isReadyToDrive();

    boolean findHospital(ArrayList<Hospital> hospitals, Date date, String timeSlot);

}
