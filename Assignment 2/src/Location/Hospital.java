package Location;

import Person.Employee;
import Location.Department;
import Scheduling.Appointment;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class Hospital {
    String name;
    ArrayList<Employee> employees = new ArrayList<>();
    Stack<Integer> getAvailableIDs = new Stack<>();
    ArrayList<Department> departments = new ArrayList<>();
    ArrayList<Appointment> appointments = new ArrayList<>();
    Stack<Integer> getAvailableAppID = new Stack<>();


    public void addEmployee(Employee employee){
        /*Description: Add employee using id's as the index into the employee array.
        *Also save potential ids not in use. Runtime O(1) Space O(2n)
        *
        * Args: employee: Child of person Class (see in Person Package)
        * */
        if (this.getAvailableIDs.isEmpty()){
            employee.id = employees.size();
            this.employees.add(employee);
        }
        Integer next = this.getAvailableIDs.pop();
        employee.id = next;
        this.employees.set(next, employee);
    }
    public void removeEmployee(Employee employee){
        /*Description: Remove employee while also saving their id/index for latter use
        * Runtime O(1)
        *
        * Args: employee: Child of person Class (see in Person Package)
        * */

        this.getAvailableIDs.push(employee.id);
        this.employees.set(employee.id, null);
    }

    public void addAppointment(Appointment appointment){
        /*Description: Add appointment using id's as the index into the appointments array.
         *Also save potential ids not in use. Runtime O(1) Space O(2n)
         *
         * Args: appointment: (see in Scheduling Package)
         * */
        if (this.getAvailableAppID.isEmpty()){
            appointment.id = appointments.size();
            this.appointments.add(appointment);
        }
        Integer next = this.getAvailableIDs.pop();
        appointment.id = next;
        this.appointments.set(next, appointment);
    }
    public void removeAppointment(Appointment appointment){
        /*Description: Remove appointment while also saving index/id.
         *Runtime O(1) Space O(2n)
         *
         * Args: appointment: (see in Scheduling Package)
         * */
        this.getAvailableAppID.push(appointment.id);
        this.appointments.set(appointment.id, null);
    }
    
    public void prtEmployeeList(){
        ArrayList<String> tmp = new ArrayList<>();
        for (Employee employee: employees){
            if (employee != null){
                tmp.add(employee.fName + " " + employee.lName);
            }
        }
        System.out.println(tmp);
    }
}
