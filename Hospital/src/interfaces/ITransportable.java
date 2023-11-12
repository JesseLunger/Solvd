package interfaces;

import location.Hospital;

import java.sql.Date;
import java.util.ArrayList;

public interface ITransportable {
    String emergencyRoom = "ER";
    String neonatalIntensiveCareUnit = "NICU";
    String intensiveCareUnit = "ICU";
    String operatingRoom = "OR";
    public boolean canDrive();
    public boolean findHospital(ArrayList<Hospital> hospitals, Date date, String timeSlot);
}
