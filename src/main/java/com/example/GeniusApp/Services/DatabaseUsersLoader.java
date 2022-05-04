package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseUsersLoader {

    /*
    @Autowired
    private UserRepository userRepository;
    */
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostConstruct
    private void initDatabase() {
        /*
        userRepository.save(new User("user", passwordEncoder.encode("pass"), "USER"));
        userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
        */
        userService.addUser(new User("user", "pass"));
        userService.addAdmin(new User("admin", "adminpass"));
    }
}
