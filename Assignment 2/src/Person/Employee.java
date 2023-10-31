package Person;

import Location.Department;
public class Employee extends Person{
    private Integer id;
    private Department department;

    public Employee(String fn, String ln, Integer age, Character s, Department department) {
        /*Description: Child of Person class, now requires department. ID is set using the
        * Hospital class using the below getters/setters.
        *
        * Args: see package Person.Person + Department: see package Location.Department
        *
        * */
        super(fn, ln, age, s); //super class Person
        this.department = department;
    }


    /*Getter and Setter functions*/
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public Department getDepartment(){
        return department;
    }

    public void setDepartment(Department department){
        this.department = department;
    }
}
