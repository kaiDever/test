package org.example.client;

import java.util.concurrent.*;


public class TestGetNowMain {

    private static String process() {
        sleep(2000);
        System.out.println("Current Execution thread where the supplier is executed - " + Thread.currentThread().getName());
        return "Hello Educative";
    }

    private static CompletableFuture<String> createFuture() {
        return CompletableFuture.supplyAsync(TestGetNowMain::process);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CompletableFuture<String> stringCompletableFuture = createFuture();

        String valueToReturn = null;

        String value = stringCompletableFuture.getNow(valueToReturn);

        System.out.println("maybe not hava value " + value);

        sleep(10000);
        value = stringCompletableFuture.getNow(valueToReturn);
        System.out.println("Completed Processing. Returned value - " + value);

    }
}