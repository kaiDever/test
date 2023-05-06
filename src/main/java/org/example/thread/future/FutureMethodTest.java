package org.example.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureMethodTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> hello = CompletableFuture.completedFuture("hello");
        System.out.println(CompletableFuture.completedFuture("hello").get());

    }
}
