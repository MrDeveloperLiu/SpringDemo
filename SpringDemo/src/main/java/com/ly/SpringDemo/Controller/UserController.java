package com.ly.SpringDemo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ly.SpringDemo.Common.Response.SResponse;
import com.ly.SpringDemo.Mongo.user.User;
import com.ly.SpringDemo.Mongo.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.POST, path = "/user/create")
    Map dbInsert(@RequestParam String name) {
        try {
            userDao.insert(name, 0);
            return new SResponse(0, "success", null).toMap();
        } catch (Exception e) {
            return new SResponse(-1, e.getMessage(), null).toMap();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/query")
    Map dbQuery(@RequestParam(required = false, defaultValue = "0") Integer page,
                @RequestParam(required = false, defaultValue = "20") Integer size) {
        List<User> users = userDao.query(page, size);
        return new SResponse(0, "success", users).toMap();
    }

}
