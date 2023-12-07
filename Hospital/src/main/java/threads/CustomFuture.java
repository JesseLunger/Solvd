package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CustomFuture {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final Random random = new Random();

    public static CompletableFuture<Void> runAsync() {
        return CompletableFuture.runAsync(() -> {
            try {
                CustomConnection connection = ConnectionPool.getInstance().getConnection();
                LOGGER.info(Thread.currentThread().getName() + " - Got connection");
                Thread.sleep(random.nextInt(5000));
                LOGGER.info(Thread.currentThread().getName() + " - Released connection");
                connection.release();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        });
    }
}

