package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public enum Sex {
    MALE('m'),
    FEMALE('f');

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final char sex;

    Sex(char sex) {
        this.sex = sex;
    }

    static {
        LOGGER.info("Initializing Sex enum");
    }

    public char getSex() {
        return this.sex;
    }
}
