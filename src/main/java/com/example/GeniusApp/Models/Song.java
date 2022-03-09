package com.example.GeniusApp.Models;


import com.example.GeniusApp.Services.CommentHolder;
import com.example.GeniusApp.Services.SongHolder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor

public class Song {

    @Autowired
    CommentHolder commentHolder;

    private long id;
    private String name;
    private String author;
    private String album;
    private String lyrics;
    private String url;

    //private Map <Long, Comment> comments = new ConcurrentHashMap<>();


    public Song(String name, String author,String album,String lyrics,String url){
        this.name = name;
        this.author = author;
        this.album = album;
        this.lyrics = lyrics;
        this.url = url;
    }

    public void addComment(Comment comment){
        if(commentHolder!=null){
            commentHolder.addComment(comment);
        }else{
            CommentHolder c=new CommentHolder();
            c.addComment(comment);
            this.commentHolder=c;
        }

    }

    public Map getComments(){
        if (commentHolder!=null){
            return this.commentHolder.getComments();
        }else{
            return null;
        }
    }

    public Comment getComment(long id){
       return commentHolder.getComment(id);
    }

    public void removeComment(long id){
        commentHolder.removeComment(id);
    }

    public void updateComment(long id, Comment comment){
        commentHolder.updateComment(id,comment);
    }

}
