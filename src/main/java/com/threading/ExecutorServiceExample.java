package com.threading;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sdierbec on 5/13/15.
 */
public class ExecutorServiceExample {
    public static void main(String[] args) {
        Random random = new Random();

        // Create an executor of thread pool size 3
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Sum up wait times to know when to shutdown
        int waitTime = 600;
        for (int i=0; i<10; i++) {
            String name = "NamePrinter " + i;
            int time = random.nextInt(500);
            waitTime += time;
            Runnable runnable = new TaskPrint(name, time);
            System.out.println("Adding: " + name + " / " + time);
            executorService.execute(runnable);
        }
        try {
            Thread.sleep(waitTime);
            executorService.shutdown();
            executorService.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {

        }
        System.exit(0);
    }
}
