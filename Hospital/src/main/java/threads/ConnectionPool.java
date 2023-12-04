package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final int POOL_SIZE = 5;
    private final BlockingQueue<CustomConnection> pool;

    public ConnectionPool() {
        pool = new ArrayBlockingQueue<>(POOL_SIZE);
        initializePool();
    }

    private void initializePool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.offer(new CustomConnection());
        }
    }

    public CustomConnection getConnection() throws InterruptedException {
        LOGGER.info(Thread.currentThread().getName() + " - Waiting for connection");
        return pool.take();
    }

    public void releaseConnection(CustomConnection connection) {
        pool.offer(connection);
    }
}