package com.example.GeniusApp.Models.Users;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.h2.mvstore.Page;

import javax.persistence.*;
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

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @ManyToMany(mappedBy = "user")
    private List<Song> songs;

    @Override
    public String toString() {
        return "User{" + ", username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
