package schedule;

public interface ICompareTime {
    public boolean conflicts(Appointment app);
    public int compare(Appointment app);
}
