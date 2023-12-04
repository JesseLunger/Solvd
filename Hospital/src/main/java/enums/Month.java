package enums;

public enum Month {
    JANUARY(1, "January", 31),
    FEBRUARY(2, "February", 28),
    MARCH(3, "March", 31),
    APRIL(4, "April", 30),
    MAY(5, "May", 31),
    JUNE(6, "June", 30),
    JULY(7, "July", 31),
    AUGUST(8, "August", 31),
    SEPTEMBER(9, "September", 30),
    OCTOBER(10, "October", 31),
    NOVEMBER(11, "November", 30),
    DECEMBER(12, "December", 31);

    private final int monthNumber;
    private final String monthName;
    private final int numberOfDays;

    Month(int monthNumber, String monthName, int numberOfDays) {
        this.monthNumber = monthNumber;
        this.monthName = monthName;
        this.numberOfDays = numberOfDays;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
}
