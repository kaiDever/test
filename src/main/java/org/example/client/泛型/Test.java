package org.example.client.泛型;


import org.example.interfaceTest.FunctionInter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

//    E - Element (在集合中使用，因为集合中存放的是元素)
//    T - Type（Java 类）
//    K - Key（键）
//    V - Value（值）
//    N - Number（数值类型）
//    ？ - 表示不确定的 java 类型

    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4};
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, ints);
        testMatchingAll(list);
    }


    /**
     * E 表示element 集合元素
     *
     * @param array
     * @param <E>
     */
    public static <E> void printArray(E[] array) {
        for (E e : array) {
            System.out.println(e);
        }
    }

    /***
     * 有界类型参数
     */
    public static <T extends FunctionInter> void test(T t) {
        System.out.println(t);
//        return t;
    }

    /**
     * 通配符 ？
     * 用于表示具体的参数类型
     */
    public static void testMatchingAll(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

}
