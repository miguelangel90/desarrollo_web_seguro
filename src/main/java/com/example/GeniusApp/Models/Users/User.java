package com.example.GeniusApp.Models.Users;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.h2.mvstore.Page;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Song> songs;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + ", username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
