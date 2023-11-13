package interfaces;

import location.Hospital;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface IAmbulance {

    String emergencyRoom = "ER";
    String neonatalIntensiveCareUnit = "NICU";
    String intensiveCareUnit = "ICU";
    String operatingRoom = "OR";
    boolean isReadyToDrive();
    boolean findHospital(ArrayList<Hospital> hospitals, Date date, String timeSlot);

}
