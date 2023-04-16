package com.ly.SpringDemo.Mongo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public List<User> query(int page, int size) {
        Query query = new Query().skip(page).limit(size);
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
