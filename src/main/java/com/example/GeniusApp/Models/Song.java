package com.example.GeniusApp.Models;


import com.example.GeniusApp.Services.CommentHolder;
import com.example.GeniusApp.Services.SongHolder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor

public class Song {

    private long id;
    private String name;
    private String author;
    private String album;
    private String lyrics;
    private String url;

    //private Map <Long, Comment> comments = new ConcurrentHashMap<>();

    private CommentHolder commentHolder;

    public Song(String name, String author,String album,String lyrics,String url){
        this.name = name;
        this.author = author;
        this.album = album;
        this.lyrics = lyrics;
        this.url = url;
    }

    public void addComment(Comment comment){
        commentHolder.addComment(comment);
    }


}
