package Person;

public class Person {
    /*Description: Person class that all classes within package Person inherit from
    *
    * Args: fname: first name
    *       lname: last name
    *       age: age of person
    *       sex: sex of person
    * */
    private String fName;
    private String lName;
    private Integer age;
    private Character sex;

    public Person(String fn, String ln, Integer age, Character s){
        this.fName = fn;
        this.lName = ln;
        this.age = age;
        this.sex = s;
    }
    public String getName(){
        return (this.fName + " " + this.lName);
    }

    @Override
    public String toString(){
        return this.getName();
    }
    public Integer getAge(){
        return this.age;
    }

    public Character getSex(){
        return this.sex;
    }

}
