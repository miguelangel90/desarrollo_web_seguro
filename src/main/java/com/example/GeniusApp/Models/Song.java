package com.example.GeniusApp.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Dat date;



}
