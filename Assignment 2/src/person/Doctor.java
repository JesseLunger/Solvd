package person;

import location.Department;
import location.Hospital;
import schedule.Appointment;

import java.util.ArrayList;

public class Doctor extends Employee {
    /*Description: Doctor class that stores appointment data
     *
     *Args: see package person.Employee
     *
     * */

    ArrayList<Appointment> appointments = new ArrayList<>();

    public Doctor(String fn, String ln, Integer age, Character s, Integer id, Hospital hospital, Department department) {
        super(fn, ln, age, s, id, hospital, department);
    }

    public boolean addApointment(int month, int day, String timeSlot) {
        if (!((0 < month && month < 13) && (0 < day && day < 31))) {
            return false;
        }
        if (!(timeSlot.equals("morning") || timeSlot.equals("evening") || timeSlot.equals("night"))) {
            return false;
        }
        for (Appointment appointment : this.appointments) {
            if (appointment.getMonth() == month && appointment.getDay() == day && appointment.getTimeSlot().equals(timeSlot)) {
                return false;
            }
        }
        Appointment newAppointment = new Appointment(month, day, timeSlot);
        return this.appointments.add(newAppointment);
    }
}
