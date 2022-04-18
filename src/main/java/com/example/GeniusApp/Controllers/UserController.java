package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Users.User;

import com.example.GeniusApp.Services.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/new/user")
    public String register(){
        return "Register";
    }

    @PostMapping("/new/user")
    public String newUser(User user, String pass){
        if (userService.checkPassword(user,pass)){
            userService.addUser(user);
            userService.setLogueado(user);
            return "user_success";
        }else {
            return "Register";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginSuccess(Model model, User user){
        if (userService.checkUser(user)){
            model.addAttribute("user", user);
            userService.setLogueado(user);
            return "login_success";
        }else{
            return "login";
        }
    }
}
