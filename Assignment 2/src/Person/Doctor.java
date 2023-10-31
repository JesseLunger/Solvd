package Person;

import java.util.Arrays;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Stack;


import Location.Department;
import Person.Patient;
import Scheduling.Appointment;

public class Doctor extends Employee{

    ArrayList<Appointment> appointments = new ArrayList<>();
    Stack<Integer> getAvailableAppID = new Stack<>();


    public Doctor(String fn, String ln, Integer age, Character s, Department dep) {
        super(fn, ln, age, s, dep);
    }

    @Override
    public String getName() {
        return "(doctor)" + super.getName();
    }

    public void addAppointment(Appointment appointment){
        /*Description: Add appointment using id's as the index into the appointments array.
         *Also save potential ids not in use. Runtime O(1) Space O(2n)
         *
         * Args: appointment: (see in Scheduling Package)
         * */
        if (this.getAvailableAppID.isEmpty()){
            appointment.setId(appointments.size());
            this.appointments.add(appointment);
        }else {
            Integer next = this.getAvailableAppID.pop();
            appointment.setId(next);
            this.appointments.set(next, appointment);
        }

    }

    public void removeAppointment(Appointment appointment){
        /*Description: Remove appointment while also saving index/id.
         *Runtime O(1) Space O(2n)
         *
         * Args: appointment: (see in Scheduling Package)
         * */
        this.getAvailableAppID.push(appointment.getId());
        this.appointments.set(appointment.getId(), null);
    }

    public ArrayList<Appointment> getAppointments(){
        return this.appointments;
    }


}
