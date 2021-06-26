package com.shun.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shun.pojo.User;
import com.shun.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RestController
public class UserController {

    @RequestMapping("/j1")
    @ResponseBody
    public String json1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User("潜江一号", 23,"男");

        return mapper.writeValueAsString(user);
    }

    @RequestMapping("/j2")
    @ResponseBody
    public String json2() throws JsonProcessingException {
        List<User> list = new ArrayList<User>();

        User user1 = new User("潜江一号", 23,"男");
        User user2 = new User("潜江二号", 23,"男");
        User user3 = new User("潜江三号", 23,"男");
        User user4 = new User("潜江四号", 23,"男");

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        return JsonUtils.getJson(list);
    }

    @RequestMapping("/j3")
    @ResponseBody
    public String json3() throws JsonProcessingException {
        Date date = new Date();

        return JsonUtils.getJson(date, "yyyy-MM-dd HH:mm:ss");
    }
}
