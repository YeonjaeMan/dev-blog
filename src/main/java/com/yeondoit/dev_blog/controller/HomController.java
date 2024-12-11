package com.yeondoit.dev_blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomController {

    @GetMapping("/")
    public String goToHome() {
        return "index";
    }
}
