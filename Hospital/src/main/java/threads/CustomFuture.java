package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CustomFuture {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final Random random = new Random();
    ConnectionPool connectionPool;

    public CustomFuture(ConnectionPool collectionPool) {
        this.connectionPool = collectionPool;
    }

    public CompletableFuture<Void> runAsync() {
        return CompletableFuture.runAsync(() -> {
            try {
                CustomConnection connection = connectionPool.getConnection();
                LOGGER.info(Thread.currentThread().getName() + " - Got connection");
                Thread.sleep(random.nextInt(5000));
                LOGGER.info(Thread.currentThread().getName() + " - Released connection");
                connectionPool.releaseConnection(connection);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        });
    }

    public CompletableFuture<Void> getStage(int iterations) {
        ArrayList<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            futures.add(runAsync());
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }
}

