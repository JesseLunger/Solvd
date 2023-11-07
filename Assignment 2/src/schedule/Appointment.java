package schedule;

public final class Appointment {
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

    public Appointment(int day, int month, String timeSlot) {
        this.day = day;
        this.month = month;
        this.timeSlot = timeSlot;
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
}
