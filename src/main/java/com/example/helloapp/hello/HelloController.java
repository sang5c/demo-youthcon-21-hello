package com.example.helloapp.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        log.info("call hello service");
        String url = getRandomUrl();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response : " + response);
        return response;
    }

    private String getRandomUrl() {
        return System.currentTimeMillis() % 2 == 0 ? "http://localhost:8081/world" : "http://localhost:8081/fail";
    }

}

