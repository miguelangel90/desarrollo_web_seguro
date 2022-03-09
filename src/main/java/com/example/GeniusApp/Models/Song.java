package com.example.GeniusApp.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
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



}
