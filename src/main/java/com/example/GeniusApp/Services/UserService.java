package com.example.GeniusApp.Services;


import com.example.GeniusApp.Models.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    private Set<String> registrados = new HashSet<>();
    //private User logueado;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(User user){
        if (userRepository.findByUsername(user.getUsername()).isEmpty()){
            user = new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),"USER");
            this.registrados.add(user.getUsername());
            userRepository.save(user);
        }
    }

    public void addAdmin(User user){
       if (userRepository.findByUsername(user.getUsername()).isEmpty()){
           user = new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),"USER","ADMIN");
           this.registrados.add(user.getUsername());
           userRepository.save(user);
       }
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
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

    public User getUserByUsernameAndPassword(String username, String pass){
        return userRepository.getByUsernameAndPassword(username,pass);
    }

    public boolean checkUser(User user){
        String username = user.getUsername();
        String pass = user.getPassword();
        User user2 = userRepository.getByUsernameAndPassword(username,pass);
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

    public User findByNameOrElseThrow(String name){
        return userRepository.findByUsername(name).orElseThrow();
    }

}