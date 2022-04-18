package com.example.GeniusApp.Services;


import com.example.GeniusApp.Models.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;




@Service
public class UserService {

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
        Long id = user.getId();
        User user2 = userRepository.getById(id);
        if (user.equals(user2)){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPassword(User user, String pass){
        String userPass = user.getPassword();
        if (userPass.equals(pass)){
            return true;
        }else{
            return false;
        }
    }

}