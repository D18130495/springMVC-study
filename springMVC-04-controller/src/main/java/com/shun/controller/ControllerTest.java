package com.shun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerTest {

    @RequestMapping("/t1")
    public String test(@RequestParam("username") String name, Model model) {

        model.addAttribute("msg", name);

        return "test";
    }

    @PostMapping("/t2")
    public String test1(String name, Model model) {

        model.addAttribute("msg", name);

        return "test";
    }
}
