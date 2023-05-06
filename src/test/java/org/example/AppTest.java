package org.example;

import com.github.benmanes.caffeine.cache.*;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void timeTest() {
        final DateTimeFormatter sqlFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        OffsetDateTime utcNow = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime utcNowHour = OffsetDateTime.of(utcNow.getYear(), utcNow.getMonthValue(), utcNow.getDayOfMonth(), 0, 0, 0, 0, ZoneOffset.UTC);
        String endTimeStr = utcNowHour.format(sqlFormat);
        OffsetDateTime utcPreDay = utcNowHour.minusDays(1);
        String startTimeStr = utcPreDay.format(sqlFormat);
    }

    @Test
    public void test() {
        // 初始化缓存，设置了1分钟的写过期，100的缓存最大个数
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
        int key1 = 1;
        // 使用getIfPresent方法从缓存中获取值。如果缓存中不存指定的值，则方法将返回 null：
        System.out.println(cache.getIfPresent(key1));

        // 也可以使用 get 方法获取值，该方法将一个参数为 key 的 Function 作为参数传入。如果缓存中不存在该 key
        // 则该函数将用于提供默认值，该值在计算后插入缓存中：
        System.out.println(cache.get(key1, integer -> 2));

        // 校验key1对应的value是否插入缓存中
        System.out.println(cache.getIfPresent(key1));

        // 手动put数据填充缓存中
        int value1 = 2;
        cache.put(key1, value1);

        // 使用getIfPresent方法从缓存中获取值。如果缓存中不存指定的值，则方法将返回 null：
        System.out.println(cache.getIfPresent(1));

        // 移除数据，让数据失效
        cache.invalidate(1);
        System.out.println(cache.getIfPresent(1));

    }

    /**
     * 模拟从数据库中读取key
     *
     * @param key
     * @return
     */
    private int getInDB(int key) {
        return key + 1;
    }

    @Test
    public void testLoadDB() {
        // 初始化缓存，设置了1分钟的写过期，100的缓存最大个数
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(key -> getInDB(key));

        int key1 = 1;
        // get数据，取不到则从数据库中读取相关数据，该值也会插入缓存中：
        Integer value1 = cache.get(key1);
        System.out.println(value1);

        // 支持直接get一组值，支持批量查找
        Map<Integer, Integer> dataMap
                = cache.getAll(Arrays.asList(1, 2, 3));
        System.out.println(dataMap);
    }

    @Test
    public void testLoadDBAsync() throws ExecutionException, InterruptedException {
        // 使用executor设置线程池
//        AsyncCache<Integer, Integer> asyncCache = Caffeine.newBuilder()
//                .expireAfterWrite(1, TimeUnit.MINUTES)
//                .maximumSize(100).executor(Executors.newSingleThreadExecutor()).buildAsync();
//        Integer key = 1;
//        // get返回的是CompletableFuture
//        CompletableFuture<Integer> future = asyncCache.get(key, (Function<Integer, Integer>) key1 -> {
//            // 执行所在的线程不在是main，而是ForkJoinPool线程池提供的线程
//            System.out.println("当前所在线程：" + Thread.currentThread().getName());
//            int value = getInDB(key1);
//            return value;
//        });
//
//        int value = future.get();
//        System.out.println("当前所在线程：" + Thread.currentThread().getName());
//        System.out.println(value);
    }

    @Test
    public void testOutByWeight() throws InterruptedException {
        // 初始化缓存，设置最大权重为2
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumWeight(2)
                .weigher((Weigher<Integer, Integer>) (key, value) -> key)
                .build();

        cache.put(1, 1);
        // 打印缓存个数，结果为1
        System.out.println(cache.estimatedSize());

        cache.put(2, 2);
        // 稍微休眠一秒
        Thread.sleep(1000);
        // 打印缓存个数，结果为1
        System.out.println(cache.estimatedSize());
    }

    @Test
    public void testEvictionAfter() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfter(new Expiry<Integer, Integer>() {
                    // 创建1秒后过期，可以看到这里必须要用纳秒
                    @Override
                    public long expireAfterCreate(Integer key, Integer value, long currentTime) {
                        return TimeUnit.SECONDS.toNanos(1);
                    }

                    // 更新2秒后过期，可以看到这里必须要用纳秒
                    @Override
                    public long expireAfterUpdate(Integer key, Integer value, long currentTime, long currentDuration) {
                        return TimeUnit.SECONDS.toNanos(2);
                    }

                    // 读3秒后过期，可以看到这里必须要用纳秒
                    @Override
                    public long expireAfterRead(Integer key, Integer value, long currentTime, long currentDuration) {
                        return TimeUnit.SECONDS.toNanos(3);
                    }
                }).build();

        cache.put(1, 2);

        System.out.println(cache.getIfPresent(1));

        Thread.sleep(6000);

        System.out.println(cache.getIfPresent(1));
    }


    private int index = 1;

    private int getInDB() {
        // 这里为了体现数据重新被get，因而用了index++
        index++;
        return index;
    }

    @Test
    public void testRefresh() throws InterruptedException {
        // 设置写入后3秒后数据过期，2秒后如果有数据访问则刷新数据
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                .expireAfterWrite(6, TimeUnit.SECONDS)
                .build(key -> getInDB(key));
        cache.put(1, getInDB());

        // 休眠2.5秒，后取值
        Thread.sleep(5500);
        System.out.println(cache.get(1));

        // 休眠1.5秒，后取值
        Thread.sleep(1500);
        System.out.println(cache.get(1));
    }

    @Test
    public void testListener() throws InterruptedException {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                // 增加了淘汰监听
                .removalListener(((key, value, cause) -> {
                    System.out.println("淘汰通知，key：" + key + "，原因：" + cause);
                }))
                .build(key -> key);

        cache.put(1, 2);

        Thread.sleep(5000);
        System.out.println("结束标志");
        cache.put(1, 3);
        Thread.sleep(2000);
        cache.put(2, 3);
    }

    public BloomFilter checkRelationLoading(String advName) {
        HashSet<String> result = new HashSet<>();
        result.add("aaa");
        result.add("bbb");
        Set<String> bundles = result;
        BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 100, 0.01);
        if (bundles != null && bundles.size() > 0) {
            bundles.forEach(filter::put);
        }
        return filter;
    }

    @Test
    public void checkFilter() {
        LoadingCache<String, BloomFilter> cache =
                Caffeine.newBuilder()
                        .expireAfterWrite(2, TimeUnit.MINUTES)
                        //指定在创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
                        .refreshAfterWrite(2, TimeUnit.MINUTES)
                        .build(advName -> checkRelationLoading(advName));
        ;
        BloomFilter hello = cache.get("hello");
        if (!hello.mightContain("bbb")) {
            System.out.println("bbb不存在");
        }
        HashSet<String> result = new HashSet<>();
        result.add("aaa");
        Set<String> bundles = result;
        BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 100, 0.01);
        for (String bundle : bundles) {
            filter.put(bundle);
        }
        cache.put("hello", filter);
        if (!hello.mightContain("ccc")) {
            System.out.println("bbb不存在");
        }

        System.out.println("hello");
    }

    @Test
    public void testLogger() {

        try {
            System.out.println(0 / 0);
        } catch (Exception e) {
            logger.error("hello{}", e);

        }
    }

    @Test
    public void ThreadPoolTest() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executor.scheduleWithFixedDelay(()->{
                System.out.println("thread id is: " + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },1,2,TimeUnit.SECONDS);
        }
        executor.shutdown();
        final boolean success = executor.awaitTermination(5, TimeUnit.MINUTES);
    }

}
