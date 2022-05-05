package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Models.Users.User;

import com.example.GeniusApp.Services.UserRepository;
import com.example.GeniusApp.Services.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

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
            return "user_success";
        }else {
            return "Register";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login_success")
    public String success(){
        return "login_success";
    }


    @GetMapping("/admin")
    public String adminPage(Model model){

        model.addAttribute("user",userService.getAll());
        return "admin";
    }

    @GetMapping("/user/delete/{Uid}")
    public String delete(Model model, HttpServletRequest request, @PathVariable long Uid){
        User user = userService.getUser(Uid);

        if (request.isUserInRole("ADMIN") && !user.getUsername().equals(request.getUserPrincipal().getName())){
            userService.removeUser(Uid);
            return "delete_success";
        }else{
            return "error";
        }
    }

    /*
    @PostMapping("/login")
    public String loginSuccess(Model model, User user){
        System.out.println("HOla");
        if (userService.checkUser(user)){
            User user2 = userService.getUserByUsernameAndPassword(user.getUsername(),user.getPassword());
            model.addAttribute("user", user2);
            userService.setLogueado(user2);
            userService.addUser(user2);
            return "login_success";
        }else{
            return "login";
        }
    }*/
}
