package com.example.GeniusApp.Services;


import com.example.GeniusApp.Models.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    private Set<String> registrados = new HashSet<>();

    @Autowired
    UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }
    public Collection<User> getAll(){
        return userRepository.findAll();
    }

    public User getUser(long id){
        return userRepository.getById(id);
    }

    public void removeUser(long id){
        userRepository.deleteById(id);
    }

    public void updateUser(long id, User user){
        user.setId(id);
        userRepository.save(user);
    }

    public boolean checkUser(User user){
        String username = user.getUsername();
        String pass = user.getPassword();
        User user2 = userRepository.findByUsernameAndPassword(username,pass);
        if (user2==null){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkPassword(User user, String pass){
        String username =  user.getUsername();
        String userPass = user.getPassword();
        if (userPass.equals(pass) && !registrados.contains(username)){
            registrados.add(username);
            return true;
        }else{
            return false;
        }
    }

}