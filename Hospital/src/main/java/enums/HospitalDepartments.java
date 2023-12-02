package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public enum HospitalDepartments {

    ER("Emergency Room"),
    NICU("Neonatal Intensive Care Unit"),
    ICU("Intensive Care Unit"),
    OR("Operating Room");

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final String roomName;

    HospitalDepartments(String roomName) {
        this.roomName = roomName;
    }

    static {
        LOGGER.info("Initializing HospitalDepartments enum");
    }

    public String getRoomName() {
        return roomName;
    }
}
