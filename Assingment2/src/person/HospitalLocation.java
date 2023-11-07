package person;

import location.Department;
import location.Floor;
import location.Hospital;

public interface HospitalLocation {
    public Hospital getHospital();
    public Department getDepartment();
}
