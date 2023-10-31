package Person;

import Location.Department;
public class Employee extends Person{
    private Integer id;
    private Department department;


    public Employee(String fn, String ln, Integer age, Character s, Department department) {
        super(fn, ln, age, s); // Call the constructor of the superclass (Person)
        this.department = department;
    }

    public Integer getID(){
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
