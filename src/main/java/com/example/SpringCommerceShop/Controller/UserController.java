package com.example.SpringCommerceShop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/")
    public String indexUser(){
        return "index.html";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
