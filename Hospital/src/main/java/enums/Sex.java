package enums;

public enum Sex {
    MALE('m'),
    FEMALE('f');

    private final char sex;

    Sex(char sex) {
        this.sex = sex;
    }

    public char getSex() {
        return this.sex;
    }
}
