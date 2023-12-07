package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Random;

public class CustomInheritedThread extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    CustomConnection connection;
    private static final Random random = new Random();

    public CustomInheritedThread(CustomConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            LOGGER.info(Thread.currentThread().getName() + " - Got connection: " + connection);
            Thread.sleep(random.nextInt(5000));
            LOGGER.info(Thread.currentThread().getName() + " - Released connection: " + connection);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        } finally {
            connection.release();
        }
    }

}
