package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Random;


public class CustomRunnableThread implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final Random random = new Random();
    ConnectionPool connectionPool;

    public CustomRunnableThread(ConnectionPool collectionPool) {
        this.connectionPool = collectionPool;
    }

    public void repeatActivation(int iterations) {
        ArrayList<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            Thread thread = new Thread(new CustomRunnableThread(connectionPool));
            thread.start();
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public void run() {
        try {
            CustomConnection connection = connectionPool.getConnection();
            LOGGER.info(Thread.currentThread().getName() + " - Got connection: " + connection);
            Thread.sleep(random.nextInt(5000));
            connectionPool.releaseConnection(connection);
            LOGGER.info(Thread.currentThread().getName() + " - Released connection: " + connection);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
