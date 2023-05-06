package org.example.thread.future;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FuturesTest {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
            30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static <T> CompletableFuture<List<T>> allAsList(final List<CompletableFuture<T>> futures) {
        return CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(ignored -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
                );
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<CompletableFuture<String>> futures = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                        Random random = new Random();
                        int w = random.nextInt(5);
                        String message = (w == 1) ? "timeout" : "正常输出";
                        System.out.println(message);
                        return message;
                    }
            );
            futures.add(future);
        }
        CompletableFuture<List<String>> completableFuture = allAsList(futures);
        List<String> list = completableFuture.get();
        System.out.println("列表长度" + list.size());
        System.out.println("------------");
        for (String unused : list) {
            System.out.println(unused);
        }
        Thread.sleep(30000);
    }

    static class TimeOut implements Runnable {

        @Override
        public void run() {
            Random random = new Random(5);
            int i = random.nextInt();
            String message = (i == 0) ? "timeout" : "正常输出";
            System.out.println(message);
        }
    }
}
