package com.example.GeniusApp.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {

    private long id = -1;
    private String author;
    private String comment;
    private Song song;

    public Comment(String comment){
        this.comment = comment;
        this.author = author;
    }
}
