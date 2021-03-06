package com.shun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/h1")
    public String hello(Model model) {
        model.addAttribute("msg", "HelloSpringMVC");
        return "Hello";
    }
}
