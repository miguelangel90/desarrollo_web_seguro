package com.example.GeniusApp.Models;


import com.example.GeniusApp.Services.CommentHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Autowired
    CommentHolder commentHolder;

    private long id;
    private String name;
    private String author;
    private String album;
    private String lyrics;
    private String url;     //URL to the song on YouTube

    private String date;

    public void addDate(Date date){     // Is a setter but we parse the String returned by Date class.
         String s=date.toString();
         int i=s.indexOf(":");
         int j=s.indexOf(":",i+1);

         this.setDate(s.substring(0,j));
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
