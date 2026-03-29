package br.com.maktaba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @GetMapping("/")
    public String home() { return "home"; }

    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }

}

