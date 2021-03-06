package com.example.GeniusApp.Models.Users;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.h2.mvstore.Page;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.swing.text.View;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    @JsonIgnore
    private String password;

    @JsonIgnore
    @ManyToMany
    private List<Song> songs = new ArrayList<>();

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.roles.add(role);
    }

    public User(String username, String password, String role, String role2){
        this.username = username;
        this.password = password;
        this.roles.add(role);
        this.roles.add(role2);
    }

    @Override
    public String toString() {
        return "User{" + ", username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }

    public void addSong(Song song){
        this.songs.add(song);
    }
}
