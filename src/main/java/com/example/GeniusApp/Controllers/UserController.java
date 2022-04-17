package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Users.User;
import com.example.GeniusApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userHolder;

    @GetMapping("/new/user")
    public String register(){

        return "Register";
    }

    @PostMapping("/new/user")
    public String newUser(User user, String pass){
        if (userHolder.checkPassword(user,pass)){
            userHolder.addUser(user);
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
    public String loginSuccess(User user){
        if (userHolder.checkUser(user)){
            return "login_success";
        }else{
            return "login";
        }

    }
}
