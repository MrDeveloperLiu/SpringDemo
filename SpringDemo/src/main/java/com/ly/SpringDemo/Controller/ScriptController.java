package com.ly.SpringDemo.Controller;

import com.ly.SpringDemo.Utils.HttpUtil;
import com.ly.SpringDemo.Utils.ScriptUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@EnableAutoConfiguration
public class ScriptController {
    @RequestMapping(method = RequestMethod.POST, path = "/script/exec")
    String execScript(@RequestParam String path) {
        return ScriptUtil.execScript(path);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/script/http")
    String execHttp(@RequestParam String url) {
        return HttpUtil.get(url, new HashMap<>());
    }
}
