package schedule;

public final class Appointment {

    private final int day;
    private final int month;

    /*options: "morning", "evening", "night"*/
    private final String timeSlot;

    public Appointment(int day, int month, String timeSlot){
        this.day = day;
        this.month = month;
        this.timeSlot = timeSlot;
    }

    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;
    }

    public String getTimeSlot(){
        return this.timeSlot;
    }


}
