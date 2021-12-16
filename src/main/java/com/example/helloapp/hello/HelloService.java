package com.example.helloapp.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private static final Logger log = LoggerFactory.getLogger(HelloService.class);

    public String callWorld() {
        RestTemplate restTemplate = new RestTemplate();
        String url = getRandomUrl();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        log.info("RESPONSE CODE : " + response.getStatusCode());
        log.info("BODY : " + response.getBody());
        return response.getBody();
    }

    private String getRandomUrl() {
        return System.currentTimeMillis() % 2 == 0 ? "http://localhost:8081/world" : "http://localhost:8081/fail";
    }

}
