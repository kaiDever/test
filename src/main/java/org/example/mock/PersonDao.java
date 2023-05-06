package org.example.mock;

import org.example.pojo.User;

public class PersonDao {
    public String update(User user) {
        System.out.println(user);
        return "ok";
    }

    public User getPerson(int id) {
        return new User("fankai", 11 + id);
    }

}
