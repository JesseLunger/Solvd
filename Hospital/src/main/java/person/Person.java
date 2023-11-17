package person;

import java.sql.Date;

public class Person {
    private final Date DATE_OF_BIRTH;
    private final Character SEX;
    private final int ID;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, Date dateOfBirth, Character sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DATE_OF_BIRTH = dateOfBirth;
        this.SEX = sex;
        this.ID = (int) (Math.random() * 1000000000);
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

    public int getID() {
        return this.ID;
    }

    public Date getDob() {
        return this.DATE_OF_BIRTH;
    }

    public Character getSEX() {
        return this.SEX;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
