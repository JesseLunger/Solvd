package enums;

public enum HospitalDepartment {

    ER("Emergency Room"),
    NICU("Neonatal Intensive Care Unit"),
    ICU("Intensive Care Unit"),
    OR("Operating Room");

    private final String roomName;

    HospitalDepartment(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }
}
