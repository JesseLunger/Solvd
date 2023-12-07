package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final int POOL_SIZE = 5;
    private static final BlockingQueue<CustomConnection> pool = new ArrayBlockingQueue<>(POOL_SIZE);
    private volatile static ConnectionPool instance;

    private ConnectionPool(int size) {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.offer(new CustomConnection());
        }
    }

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(POOL_SIZE);
        }
        return instance;
    }

    public CustomConnection getConnection() throws InterruptedException {
        LOGGER.info(Thread.currentThread().getName() + " - Waiting for connection");
        return pool.take();
    }

    public void releaseConnection(CustomConnection connection) {
        pool.offer(connection);
    }

}