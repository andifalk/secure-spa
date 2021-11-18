package com.example.bankingserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/attack")
@RestController
public class TokenRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenRestController.class);

    @GetMapping
    public void getXss(@RequestParam("data") String data) {
        LOGGER.info("Got data: {}", data);
    }

}
