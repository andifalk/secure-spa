package com.example.attackerserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataRestController {

    private static final Logger log = LoggerFactory.getLogger(DataRestController.class);

    @GetMapping("/data")
    public String data(@RequestParam("key") String key, @RequestParam("value") String value) {
        log.info("Got key={}, value={}", key, value);
        return "worked";
    }

}
