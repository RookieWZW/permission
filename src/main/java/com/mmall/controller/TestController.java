package com.mmall.controller;

import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by RookieWangZhiWei on 2018/11/2.
 */
@Controller
@RequestMapping
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public String hello() {
        log.info("hello");
        throw new PermissionException("hello permission");
       // return "hello permission";
    }
}
