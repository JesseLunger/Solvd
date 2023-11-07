package person;

import location.Department;
import location.Hospital;

public interface HospitalLocation {
    /*description: information required for any hosptial personal.
     *
     * */
    Hospital getHospital();

    Department getDepartment();
}
