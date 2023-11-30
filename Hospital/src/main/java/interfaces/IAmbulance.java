package interfaces;

import location.Hospital;

import java.sql.Date;
import java.util.ArrayList;

public interface IAmbulance {

    boolean isReadyToDrive();

    boolean findHospital(ArrayList<Hospital> hospitals, Date date, String timeSlot);


    enum Model {

        Ford("Ford"),
        Chevy("Chevy"),
        Honda("Honda"),
        Subaru("Subaru");

        private final String model;

        Model(String model) {
            this.model = model;
        }

        public String getModelName() {
            return model;
        }
    }

    enum HospitalDepartments {

        ER("Emergency Room"),
        NICU("Neonatal Intensive Care Unit"),
        ICU("Intensive Care Unit"),
        OR("Operating Room");

        private final String roomName;

        HospitalDepartments(String roomName) {
            this.roomName = roomName;
        }

        public String getRoomName() {
            return roomName;
        }
    }
}
