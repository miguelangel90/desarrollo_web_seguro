package com.example.GeniusApp.Models;


import com.example.GeniusApp.Services.CommentRepository;
import com.example.GeniusApp.Services.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.util.Map;

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



    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

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


}
