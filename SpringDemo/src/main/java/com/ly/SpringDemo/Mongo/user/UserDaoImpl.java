package com.ly.SpringDemo.Mongo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(String name, Integer role) {
        User user = new User(null, name, role);
        mongoTemplate.insert(user);
    }

    public List<User> query() {
        List<User> users = mongoTemplate.findAll(User.class);
        return users;
    }
}
