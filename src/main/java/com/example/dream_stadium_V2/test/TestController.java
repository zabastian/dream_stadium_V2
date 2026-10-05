package com.example.dream_stadium_V2.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @GetMapping("/recaptcha")
    public String recaptcha() {
        return "index";
    }
}