package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Services.SongHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class WebController {

    @Autowired
    SongHolder songHolder;

    @GetMapping("")
    public String start(){
        return "Start";
    }

    @GetMapping("/songs")
    public String allsongs(){
        return "Portal";
    }

    @GetMapping("/new-song")
    public String newsong(){
        return "NewSong";
    }

    @PostMapping("/new/song")
    public String addSong(Song song){
        songHolder.addSong(song);

        return "song_success";
    }

    @PostMapping("/new/user")
    public String newUser(){

        return "user_success";
    }
}
