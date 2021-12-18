package com.example.helloapp.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/")
    public String home() {
        log.info("welcome!");
        return "HELLO WORLD!";
    }

    @GetMapping("/hello")
    public String hello() {
        log.error("ERROR!");
        throw new RuntimeException("HELLO YOUTHCON 21");
    }

}

