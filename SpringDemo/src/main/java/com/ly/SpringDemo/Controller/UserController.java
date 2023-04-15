package com.ly.SpringDemo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String dbInsert(@RequestParam String name) {
        try {
            userDao.insert(name, 0);
            return "插入成功";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/query")
    Map dbQuery() {
        Map retVal = new HashMap();
        List<User> users = userDao.query();
        retVal.put("data", users);
        retVal.put("code", "0");
        retVal.put("msg", "success");
        return retVal;
    }

}
