package org.example.client;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNonStringKeyAsString;

public class StudentCompare implements Comparable<StudentCompare> {
    public static void main(String[] args) {
        List<StudentCompare> people = new ArrayList<>();
        people.add(new StudentCompare("fankai", 22));
        people.add(new StudentCompare("liuxu", 18));
        people.add(new StudentCompare("fankai", 55));
//        people.sort(Comparator.comparing(StudentCompare::getAge, Comparator.reverseOrder()).thenComparing(StudentCompare::getName));
//        for (StudentCompare person : people) {
//            System.out.println(person);
//        }
        String liudehua = JSON.toJSONString(people, WriteNonStringKeyAsString);
        System.out.println(liudehua);
    }

    private String name;
    private int age;

    public StudentCompare(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentCompare{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    public int compareTo(StudentCompare o) {
        if (getAge() > o.getAge()) {
            return 1;
        } else if (getAge() < o.getAge()) {
            return -1;
        }
        return 0;
    }
}
