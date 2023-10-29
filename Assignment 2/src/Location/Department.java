package Location;

import Person.Patient;
import Scheduling.DailySchedule;
import java.util.ArrayList;
import java.util.Arrays;
public class Department {
    public String name;
    public String contactInfo;
    public Integer floor;
    public ArrayList<Patient[]> patientsByFloor;
    public ArrayList<DailySchedule> staff;


}
