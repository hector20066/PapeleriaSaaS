package com.hector.papeleriasaas.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Ing. Hector Acevedo
 */
public final class ReportExecutor {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2);

    private ReportExecutor() {

    }

    public static void execute(Runnable task) {
        EXECUTOR.submit(task);
    }

    public static void shutdown() {
        EXECUTOR.shutdown();
        try {
            if (!EXECUTOR.awaitTermination(10, TimeUnit.SECONDS)) {
                EXECUTOR.shutdownNow();
            }
        } catch (InterruptedException e) {
            EXECUTOR.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
