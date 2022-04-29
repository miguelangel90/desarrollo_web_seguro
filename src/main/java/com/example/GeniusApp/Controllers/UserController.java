package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Users.User;

import com.example.GeniusApp.Services.UserRepository;
import com.example.GeniusApp.Services.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    /*@PostConstruct
    public void init(){
        User u1 = new User("username", "pass");
        userService.addUser(u1);
    }*/

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
            User user2 = userService.getUserByUsernameAndPassword(user.getUsername(),user.getPassword());
            model.addAttribute("user", user2);
            userService.setLogueado(user2);
            userService.addUser(user2);
            return "login_success";
        }else{
            return "login";
        }
    }
}
