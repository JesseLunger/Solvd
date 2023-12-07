package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class ThreadManager {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void repeatActivation(int iterations, String threadType) {
        ArrayList<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            try {
                Thread thread;
                CustomConnection connection = ConnectionPool.getInstance().getConnection();
                if (threadType.equals("inherited")) {
                    thread = new CustomInheritedThread(connection);
                } else if (threadType.equals("runnable")) {
                    thread = new Thread(new CustomRunnableThread(connection));
                } else {
                    connection.release();
                    LOGGER.error("Wrong input, inputs: inherited or runnable");
                    return;
                }
                threadList.add(thread);
                thread.start();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public CompletableFuture<Void> getStage(int iterations) {
        ArrayList<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            futures.add(CustomFuture.runAsync());
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }
}
