package Person;

public class Person {
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


    public Integer getAge(){
        return this.age;
    }

    public Character getSex(){
        return this.sex;
    }

}
