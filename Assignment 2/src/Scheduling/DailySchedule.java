package Scheduling;

import Person.Employee;
public class DailySchedule {
    private Employee[] morningStaff;
    private Employee[] eveningStaff;
    private Employee[] nightStaff;

    String date;

    public void assignStaff(Employee[] employees, String shift){
        switch (shift){
            case ("morning"):
                this.morningStaff = employees;
                break;
            case ("evening"):
                this.eveningStaff = employees;
                break;
            case ("night"):
                this.nightStaff = employees;
                break;
            default:
                System.out.println("paramenter shift must equal morning, evening or night");
        }
    }
    public Employee[] getMorningStaff(){
        return this.morningStaff;
    }

    public Employee[] getEveningStaff(){
        return this.eveningStaff;
    }

    public Employee[] getNightStaff(){
        return this.nightStaff;
    }

}
