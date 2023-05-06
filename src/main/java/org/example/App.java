package org.example;

import org.example.interfaceTest.FunctionInter;
import org.example.pojo.ParamName;
import org.example.pojo.UnlucklyDuck;
import org.example.pojo.User;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        randomString();

    }
    public static void testException(){
        try {
            throw new IOException("hello",new IOException());
        }catch (Exception e){
            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
        }
    }
    public static void testStringNum() {
        List<String> result = new ArrayList<>();
        ArrayList<String> result1 = new ArrayList<>();
//        List<String> originalList = Arrays.asList("ball.sort.puzzle.color.sorting.bubble.games", "com.blockpuzzletangram.real.game", "6443843866", "com.dna.solitaireapp", "1493199883", "1559032748", "1590353335", "brain.blow.quest", "com.wn.hairtattoo", "io.state.fight", "749133753", "com.fiogonia.dominoes", "com.fiogonia.yatzy", "com.blackout.bubble", "com.blackout.spades", "com.bigcake.android.bubbledaily", "com.pranksounds.appglobaltd", "1426179665", "1225888584", "1630942647", "com.puzzle.find.differences", "479280326", "com.auto.touch.cleaner.junk", "6443481860", "com.anil.ani", "1492055041", "1528844711", "art.color.planet.oil.paint.canvas.number.free", "art.color.planet.paint.by.number.game.puzzle.free", "com.impossible.tracks.real.race.games", "com.adfone.aditup", "605569663", "1190849728", "1193508329", "1274972321", "1445691600", "1452227871", "1452992954", "1505735640", "1519174001", "1545567989", "1619320898", "com.easybrain.backgammon.board.games", "com.easybrain.brain.test.easy.game", "com.easybrain.nonogram.color", "1561830495", "com.easybrain.art.puzzle", "solitaire.card.classic.bgames", "spider.solitaire.card.bgames", "com.fiogonia.euchre", "1502227743", "1617401370", "water.sort.free.puzzle.game.relax", "com.easybrain.cross.logic.puzzle", "com.easybrain.find.the.difference", "com.easybrain.killer.sudoku.free", "com.easybrain.make.music", "com.easybrain.number.puzzle.game", "com.easybrain.solitaire.klondike.free", "com.easybrain.spider.solitaire", "1163786766", "droom.sleepIfUCan", "1517829670", "1542613280", "1607122287", "com.master.triple3d.find", "com.leftover.CoinDozer", "com.apalon.myclockfree", "1565374299", "com.gimica.solitaireverse", "com.find.out.hidden.objects", "1546705283", "com.fugo.wowguru", "com.bigbluebubble.singingmonsters.full", "com.globalplay.birdsort", "com.gamecircus.CoinDozerJackpot", "1374403536", "com.candywriter.bitlife", "com.youmusic.magictiles", "1475864805", "com.bigc.solitaire.spider", "com.solitaire.card", "651510680", "com.etermax.preguntados.lite", "com.lemel.hiddengame", "com.fiogonia.hearts", "com.fiogonia.spades", "com.bigduckgames.flow", "1340841589", "com.luckyblockpuzzle.real.game", "1609906813", "921765888", "com.sportbrain.jewelpuzzle", "com.fugo.wow", "solitaire.spider.card.free.mania", "com.falcon.dx3.wugy.monster.chapter2", "blockpuzzle.game.block.puzzle", "com.ludo.king", "com.creative.cross.stitch.relaxing.game", "com.creative.sandbox.number.drawning.coloring");
        List<String> testList = Arrays.asList("1163786766", "1190849728", "1193508329", "1225888584", "1274972321", "1340841589", "1374403536", "1426179665", "1445691600", "1452227871", "1452992954", "1475864805", "1492055041", "1493199883", "1502227743", "1505735640", "1517829670", "1519174001", "1528844711", "1542613280", "1545567989", "1546705283", "1559032748", "1561830495", "1565374299", "1590353335", "1607122287", "1617401370", "1619320898", "1630942647", "479280326", "605569663", "6443481860", "6443843866", "651510680", "749133753", "921765888", "art.color.planet.oil.paint.canvas.number.free", "art.color.planet.paint.by.number.game.puzzle.free", "ball.sort.puzzle.color.sorting.bubble.games", "blockpuzzle.game.block.puzzle", "brain.blow.quest", "com.adfone.aditup", "com.anil.ani", "com.apalon.myclockfree", "com.auto.touch.cleaner.junk", "com.bigbluebubble.singingmonsters.full", "com.bigc.solitaire.spider", "com.bigcake.android.bubbledaily", "com.bigduckgames.flow", "com.blackout.bubble", "com.blackout.spades", "com.blockpuzzletangram.real.game", "com.candywriter.bitlife", "com.creative.cross.stitch.relaxing.game", "com.creative.sandbox.number.drawning.coloring", "com.dna.solitaireapp", "com.easybrain.art.puzzle", "com.easybrain.backgammon.board.games", "com.easybrain.brain.test.easy.game", "com.easybrain.cross.logic.puzzle", "com.easybrain.find.the.difference", "com.easybrain.killer.sudoku.free", "com.easybrain.make.music", "com.easybrain.nonogram.color", "com.easybrain.number.puzzle.game", "com.easybrain.solitaire.klondike.free", "com.easybrain.spider.solitaire", "com.etermax.preguntados.lite", "com.falcon.dx3.wugy.monster.chapter2", "com.find.out.hidden.objects", "com.fiogonia.dominoes", "com.fiogonia.euchre", "com.fiogonia.hearts", "com.fiogonia.spades", "com.fiogonia.yatzy", "com.fugo.wow", "com.fugo.wowguru", "com.gamecircus.CoinDozerJackpot", "com.gimica.solitaireverse", "com.globalplay.birdsort", "com.leftover.CoinDozer", "com.lemel.hiddengame", "com.luckyblockpuzzle.real.game", "com.ludo.king", "com.master.triple3d.find", "com.pranksounds.appglobaltd", "com.puzzle.find.differences", "com.solitaire.card", "com.sportbrain.jewelpuzzle", "com.wn.hairtattoo", "com.youmusic.magictiles", "droom.sleepIfUCan", "io.state.fight", "solitaire.card.classic.bgames", "solitaire.spider.card.free.mania", "spider.solitaire.card.bgames", "water.sort.free.puzzle.game.relax");
        List<String> dspList = Arrays.asList("art.color.planet.oil.paint.canvas.number.free", "art.color.planet.paint.by.number.game.puzzle.free", "1492055041", "1528844711", "1475864805", "com.globalplay.birdsort", "com.falcon.dx3.wugy.monster.chapter2", "com.easybrain.art.puzzle", "1193508329", "com.easybrain.number.puzzle.game", "1452227871", "1545567989", "1452992954", "1274972321", "1505735640", "1190849728", "1445691600", "1519174001", "com.easybrain.nonogram.color", "com.easybrain.find.the.difference", "com.easybrain.solitaire.klondike.free", "com.easybrain.killer.sudoku.free", "com.easybrain.make.music", "com.easybrain.cross.logic.puzzle", "com.easybrain.spider.solitaire", "com.easybrain.backgammon.board.games", "com.easybrain.brain.test.easy.game", "com.blackout.bubble", "com.blackout.spades", "479280326", "921765888", "1493199883", "io.state.fight", "1559032748", "1590353335", "brain.blow.quest", "com.wn.hairtattoo", "com.bigduckgames.flow", "1617401370", "blockpuzzle.game.block.puzzle", "com.fugo.wow", "com.fugo.wowguru", "1546705283", "com.gamecircus.CoinDozerJackpot", "com.leftover.CoinDozer", "605569663", "com.gimica.solitaireverse", "1517829670", "1561830495", "ball.sort.puzzle.color.sorting.bubble.games", "1542613280", "com.lemel.hiddengame", "1565374299", "com.fiogonia.hearts", "com.fiogonia.spades", "com.fiogonia.dominoes", "com.fiogonia.euchre", "com.fiogonia.yatzy", "1225888584", "com.anil.ani", "1426179665", "com.bigcake.android.bubbledaily", "com.bigc.solitaire.spider", "com.solitaire.card", "com.creative.sandbox.number.drawning.coloring", "com.creative.cross.stitch.relaxing.game", "solitaire.card.classic.bgames", "spider.solitaire.card.bgames", "solitaire.spider.card.free.mania", "1340841589", "com.find.out.hidden.objects", "com.master.triple3d.find", "1607122287", "651510680", "com.etermax.preguntados.lite", "com.sportbrain.jewelpuzzle", "com.adfone.aditup", "com.ludo.king", "1502227743", "water.sort.free.puzzle.game.relax", "com.dna.solitaireapp", "6443843866", "6443481860", "com.youmusic.magictiles", "com.puzzle.find.differences", "1630942647", "com.pranksounds.appglobaltd", "com.candywriter.bitlife", "1374403536", "com.apalon.myclockfree", "749133753", "1163786766", "droom.sleepIfUCan", "com.auto.touch.cleaner.junk");

        //        for (String testData : originalList) {
//            if (!testList.contains(testData)) {
//                result.add(testData);
//            }
//        }
        for (String s : testList) {
            if (!dspList.contains(s)){
                result1.add(s);
            }
        }
//        System.out.println("原始数据大小" + originalList.size() + "测试数据大小" + testList.size() + "," + "未包含数据大小" + result.size());
//        for (String s : result) {
//            System.out.println(s);
//        }
        System.out.println("数据库数据大小" + testList.size() + "dsp数据大小" + dspList.size() + "," + "未包含数据大小" + result1.size());
        for (String s : result1) {
            System.out.println(s);
        }
    }

    public static void testFuture() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        CompletableFuture<User> userCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("程序开始运行" + Thread.currentThread());
            User user;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("程序运行结束" + Thread.currentThread());
            user = new User("fankai", 12);
            return user;
        }, pool);


    }

    public static void testConstants() {
        String hi = Constants.HELLO.replace("hello", "hi");
        System.out.println(hi);
        System.out.println(Constants.HELLO);
    }

    public static void testHashMap() {
        HashMap<String, List<Integer>> map = new HashMap<>();
        map.computeIfAbsent("key1", k -> new ArrayList<>()).add(1);
        map.computeIfAbsent("key1", k -> new ArrayList<>()).add(2);
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            for (Integer integer : entry.getValue()) {
                System.out.println(integer);
            }
        }
    }

    public static void testIterator() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 0)
                iterator.remove();
        }
        for (Integer num : list) {
            System.out.println(num);
        }

    }

    public static void testMatcher() {
        final Pattern domainPattern = Pattern.compile("https?://([^/]+)");
        Matcher matcher = domainPattern.matcher("https://www.baidu.com/");
        System.out.println(matcher.group(0));
    }

    public static void filedMethod() {
        FunctionInter functional = null;
        functional.say();
        functional.dance();
    }

    public static Future<String> useCompletableFuture() {
        Future<String> completableFuture = null;
        for (int i = 0; i < 10; i++) {
            completableFuture =
                    CompletableFuture.completedFuture(i + "");
        }

        return completableFuture;
    }

    public static void randomString() {
//        String result = UUID.randomUUID().toString();
        String result = UUID.randomUUID().toString().replace("-", "");
        System.out.println(result.substring(0, 8));
        System.out.println(result);
    }

    public static void testComputeIfAbsent() {
        Map<String, Integer> map = new HashMap<>();
//        map.put("fankai",12);
        int length = map.computeIfAbsent("fankai", String::length);
        System.out.println(map.get("fankai"));
        System.out.println(length);

    }

    public static void testAnnotation() {
//        Method[] methods = UnlucklyDuck.class.getMethods();
//        for (Method method : methods) {
//            ParamName[] annotationsByType = method.getAnnotationsByType(ParamName.class);
//            System.out.println(annotationsByType.length);
//        }
        Field[] fields = UnlucklyDuck.class.getFields();
        for (Field field : fields) {
            ParamName[] annotationsByType = field.getAnnotationsByType(ParamName.class);
            System.out.println(annotationsByType[0].value());
        }
    }

    /**
     * java 8 Consumer
     */
    public static void testConsumer() {
        //设置好Consumer实现方法
        Consumer<Integer> square = x -> System.out.println("平方计算 : " + x * x);
        //传入值
        square.accept(1);
    }

    public static void testAndThen() {
        //当前值
        Consumer<Integer> consumer1 = x -> System.out.println("当前值 : " + x);
        //相加
        Consumer<Integer> consumer2 = x -> {
            System.out.println("相加 : " + (x + x));
        };
        //相乘
        Consumer<Integer> consumer3 = x -> System.out.println("相乘 : " + x * x);
        //andThen拼接
        consumer1.andThen(consumer2).andThen(consumer3).accept(1);
    }

    public static void testStringClass() {
        String s1 = "Hello";
        String s4 = "Hel" + new String("lo");
        String s5 = new String("Hello");
        String s7 = "H";
        String s8 = "ello";
        String s9 = s7 + s8;
        System.out.println(s1 == s9);
    }

//    public static void testBucket(){
//        //Create the Bandwidth to set the rule - one token per minute
//        Bandwidth oneCosumePerMinuteLimit = Bandwidth.simple(1, Duration.ofMinutes(1));
//
//        //Create the Bucket and set the Bandwidth which we created above
//        Bucket bucket = Bucket.builder()
//                .addLimit(oneCosumePerMinuteLimit)
//                .build();
//
//        //Call method tryConsume to set count of Tokens to take from the Bucket,
//        //returns boolean, if true - consume successful and the Bucket had enough Tokens inside Bucket to execute method tryConsume
//        System.out.println(bucket.tryConsume(1)); //return true
//
//        //Call method tryConsumeAndReturnRemaining and set count of Tokens to take from the Bucket
//        //Returns ConsumptionProbe, which include much more information than tryConsume, such as the
//        //isConsumed - is method consume successful performed or not, if true - is successful
//        //getRemainingTokens - count of remaining Tokens
//        //getNanosToWaitForRefill - Time in nanoseconds to refill Tokens in our Bucket
//        ConsumptionProbe consumptionProbe = bucket.tryConsumeAndReturnRemaining(1);
//        System.out.println(consumptionProbe.isConsumed()); //return false since we have already called method tryConsume, but Bandwidth has a limit with rule - one token per one minute
//        System.out.println(consumptionProbe.getRemainingTokens()); //return 0, since we have already consumed all of the Tokens
//        System.out.println(consumptionProbe.getNanosToWaitForRefill()); //
//    }

    public static void testStringAndStringBuilder() {
        String abc0 = "abc";
        StringBuilder abc1 = new StringBuilder("abc");
        System.out.println(System.identityHashCode(abc0));
        System.out.println(System.identityHashCode(abc1));
    }

    public static void testUnmodifiableSet() {
        Set<String> set = new HashSet<>();
        set.add("fankai");
        // create and initialize the set instance
        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
        set.add("Costa Rica");
        System.out.println(unmodifiableSet);

    }


    public static void testCompletableFuture() {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(App::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }


}
