package schedule;


import person.Patient;

import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

public final class Appointment implements ICompareTime{
    /*Description: Appointment class that holds information of appointment time. Appointment
     * data is stored in Doctor class.
     *
     *Args:  day: integer 1-30
     *       month: integer 1-12
     *       timeSlot: String = {"morning", "evening", "night"}
     * */

    private final int day;
    private final int month;

    private final String timeSlot;

    private final Patient patient;

    public Appointment(int day, int month, String timeSlot, Patient patient) {
        this.day = day;
        this.month = month;
        this.timeSlot = timeSlot;
        this.patient = patient;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public String getTimeSlot() {
        return this.timeSlot;
    }


    /* need to be removed*/
    public boolean conflicts(Appointment otherApp){
        if (this.day == otherApp.getDay() && this.month == otherApp.month && this.timeSlot.equals(otherApp.timeSlot)){
            return true;
        }
        return false;
    }
    public int compare(Appointment otherApp){
        HashMap<String, Integer> timeSlots = new HashMap<>();
        timeSlots.put("morning", 1);
        timeSlots.put("evening", 2);
        timeSlots.put("night", 3);
        int thisTotal = (90 * this.month) + (3 * this.day) + timeSlots.get(this.timeSlot);
        int otherTotal = (90 * otherApp.getMonth()) + (3 * otherApp.getDay()) + timeSlots.get(otherApp.getTimeSlot());
        if (thisTotal > otherTotal){
            return 1;
        } else if(thisTotal < otherTotal){
            return -1;
        }
        return 0;
    }

}
