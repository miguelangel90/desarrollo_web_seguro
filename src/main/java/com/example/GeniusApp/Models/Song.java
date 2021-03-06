package com.example.GeniusApp.Models;



import com.example.GeniusApp.Models.Users.User;
import com.example.GeniusApp.Services.CommentRepository;
import com.example.GeniusApp.Services.CommentService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String author;
    private String album;
    private String lyrics;
    private String url;     //URL to the song on YouTube
    private String date;
    private String owner;

    @JsonView
    @ManyToMany
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Song (String nombre, String autor, String album, String lyrics, String url){
        this.name = nombre;
        this.author = autor;
        this.album = album;
        this.lyrics = lyrics;
        this.url = url;
    }

    public Song(Song song){
        this.id = song.getId();
        this.name = song.getName();
        this.author = song.getAuthor();
        this.album = song.getAlbum();
        this.lyrics = song.getLyrics();
        this.url = song.getUrl();
        this.date = song.getDate();
        this.users = song.getUsers();
        this.comments =  song.getComments();
    }

    public Collection<Comment> collectionComments(){
        Collection<Comment> comments = this.getComments();
        return comments;
    }

    public void addDate(Date date){     // Is a setter but we parse the String returned by Date class.
        String s=date.toString();
        int i=s.indexOf(":");
        int j=s.indexOf(":",i+1);
        this.setDate(s.substring(0,j));
    }

    public void updateLyrics(String lyrics){
        this.lyrics = lyrics;
    }

    public void addUser(User user){
        this.users.add(user);
    }
}
