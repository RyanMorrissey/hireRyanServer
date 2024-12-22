package com.youShould.hireRyanServer.controller

import com.youShould.hireRyanServer.dto.HttpTest
import org.slf4j.Logger;
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RyanController {

    private final AtomicLong counter = new AtomicLong();
    private final static Logger logger = LoggerFactory.getLogger(RyanController.class);
    private List<HttpTest> httpTests = new ArrayList<>();

    RyanController( ) {
    }

    @GetMapping("/getAllHttpTests")
    List<HttpTest> getAllHttpTests() {
        return httpTests;
    }

    @GetMapping("/getHttpTestById/{id}")
    HttpTest getHttpTestById(@PathVariable("id") String id) {
        Optional<HttpTest> test = httpTests.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        return test.orElse(null);
    }

    @PostMapping("/addHttpTest")
    @ResponseStatus(HttpStatus.CREATED)
    HttpTest push(@RequestBody HttpTest newTest) {
        httpTests.add(newTest);
        return newTest;
    }
}