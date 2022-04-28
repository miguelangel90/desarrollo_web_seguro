package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsernameAndPassword(String username, String pass);

}
