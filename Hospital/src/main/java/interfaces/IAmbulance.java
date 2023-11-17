package interfaces;

import location.Hospital;

import java.sql.Date;
import java.util.ArrayList;

public interface IAmbulance {

    String emergencyRoom = "ER";
    String neonatalIntensiveCareUnit = "NICU";
    String intensiveCareUnit = "ICU";
    String operatingRoom = "OR";

    boolean isReadyToDrive();

    boolean findHospital(ArrayList<Hospital> hospitals, Date date, String timeSlot);

}
