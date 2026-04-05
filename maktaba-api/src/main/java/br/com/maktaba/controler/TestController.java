package br.com.maktaba.controler;

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

    @GetMapping("/sobre")
    public String sobre() { return "sobre"; }

}

