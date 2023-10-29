package Person;

import java.util.Arrays;
import java.util.ArrayList;


import Scheduling.Appointment;
public class Doctor extends Employee{
    Patient[] pcp;
    ArrayList<String> onCall;
    ArrayList<Appointment> appointments;
    Boolean busy;
}
