package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class TestApplication {
    @Resource
    Test test;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @RequestMapping("/test")
    public String test(){
        return test.toString();
    }

}
