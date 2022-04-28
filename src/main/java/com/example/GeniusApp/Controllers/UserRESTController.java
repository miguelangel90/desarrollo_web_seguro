package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Users.User;
import com.example.GeniusApp.Services.UserRepository;
import com.example.GeniusApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

import java.util.List;

@RestController
public class UserRESTController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/allusers")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        TypedQuery<User> query = entityManager.createQuery
                ("SELECT u FROM User u WHERE u.id= :id", User.class);
        return query.setParameter("id", id).getSingleResult();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        userService.removeUser(id);
        User user = userService.getUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        User user = userService.getUser(id);
        if (user != null) {
            userService.updateUser(id, updatedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
