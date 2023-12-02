package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public enum Months {
    January(1, "January", 31),
    February(2, "February", 28),
    March(3, "March", 31),
    April(4, "April", 30),
    May(5, "May", 31),
    June(6, "June", 30),
    July(7, "July", 31),
    August(8, "August", 31),
    September(9, "September", 30),
    October(10, "October", 31),
    November(11, "November", 30),
    December(12, "December", 31);

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final int monthNumber;
    private final String monthName;
    private final int numberOfDays;

    Months(int monthNumber, String monthName, int numberOfDays) {
        this.monthNumber = monthNumber;
        this.monthName = monthName;
        this.numberOfDays = numberOfDays;
    }

    static {
        LOGGER.info("Initializing Months enum");
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
