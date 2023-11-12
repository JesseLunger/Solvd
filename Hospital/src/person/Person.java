package person;

import java.sql.Date;
public class Person {
    private String firstName;
    private String lastName;
    private final Date dateOfBirth;
    private final Character sex;
    private final int id;


    public Person(String firstName, String lastName, Date dateOfBirth, Character sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.id = (int)(Math.random() * 1000000000);
    }

    public void setfName(String name) {
        this.firstName = name;
    }

    public void setlName(String name) {
        this.lastName = name;
    }

    public String getName() {
        return (this.firstName + " " + this.lastName);
    }

    public String getFName() {
        return this.firstName;
    }

    public String getLname() {
        return this.lastName;
    }

    public int getId(){ return this.id;}

    public Date getDob() {
        return this.dateOfBirth;
    }

    public Character getSex() {
        return this.sex;
    }

    @Override
    public String toString() {  return this.getName();
    }
}
