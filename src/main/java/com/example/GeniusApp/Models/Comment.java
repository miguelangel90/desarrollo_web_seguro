package com.example.GeniusApp.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {

    private long id = -1;
    private String author;
    private String text;


    public Comment(String comment){
        this.text = comment;
        this.author = author;
    }
}