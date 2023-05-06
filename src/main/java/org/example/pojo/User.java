package org.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @JSONField(ordinal = 1)
    private String User;
    @JSONField(ordinal = 2)
    private Integer age;


    public User(String user, Integer age) {
        User = user;
        this.age = age;
    }

    public User() {
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "User='" + User + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        String str = new String("11");
    }

}
