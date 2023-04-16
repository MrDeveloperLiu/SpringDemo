package com.ly.SpringDemo.Mongo.user;

import java.util.List;

public interface UserDao {
    public void insert(String name, Integer role);
    public List<User> query(int page, int size);
}
