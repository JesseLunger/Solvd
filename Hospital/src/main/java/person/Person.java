package person;

import functionalinterfaces.IFiveParameters;

import java.util.Date;
import java.util.function.Supplier;


public class Person {

    private final Date DATE_OF_BIRTH;
    private final Character SEX;
    private final int ID;
    Supplier<Integer> randomSupplier;
    Supplier<String> nameSupplier;
    IFiveParameters<String> concatStringLambda;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, Date dateOfBirth, Character sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DATE_OF_BIRTH = dateOfBirth;
        this.SEX = (sex == 'm' || sex == 'M') ? SexEnum.MALE.getSex() : SexEnum.Female.getSex();
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

    public Character getSex() {
        return this.SEX;
    }

    public String getFullinformation() {
        return concatStringLambda.myApply(firstName, lastName,
                ", Sex: " + SEX,
                ", DOB: " + DATE_OF_BIRTH.toString(),
                ", ID: " + ID);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    enum SexEnum {
        MALE('m'),
        Female('f');

        private final char sex;

        SexEnum(char sex) {
            this.sex = sex;
        }

        public char getSex() {
            return this.sex;
        }

    }
}
