package br.com.maktaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "🚀 Maktaba API rodando! localhost:9090";
    }

    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}
