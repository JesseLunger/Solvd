package person;

import enums.Sex;
import functionalinterfaces.IFiveParameters;

import java.util.Date;
import java.util.function.Supplier;


public class Person {

    private final Date DATE_OF_BIRTH;
    private final Sex SEX;
    private final int ID;
    private Supplier<Integer> randomSupplier;
    private Supplier<String> nameSupplier;
    private IFiveParameters<String> concatStringLambda;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, Date dateOfBirth, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DATE_OF_BIRTH = dateOfBirth;
        this.SEX = sex;
        randomSupplier = () -> (int) (Math.random() * 1000000000);
        nameSupplier = () -> firstName + " " + lastName;
        this.ID = randomSupplier.get();
        concatStringLambda = (string1, string2, string3, string4, string5) ->
                string1 + string2 + string3 + string4 + string5;
    }

    public void setfName(String name) {
        this.firstName = name;
    }

    public void setlName(String name) {
        this.lastName = name;
    }

    public String getName() {
        return nameSupplier.get();
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

    public Sex getSex() {
        return SEX;
    }

    public String getFullinformation() {
        return concatStringLambda.concatenate(firstName, lastName,
                ", Sex: " + SEX,
                ", DOB: " + DATE_OF_BIRTH.toString(),
                ", ID: " + ID);
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
