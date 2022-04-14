package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Services.SongHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

public class SongController {
    @Autowired
    SongHolder songHolder;

    @GetMapping("")
    public String start(){
        return "Start";
    }

    @GetMapping("/songs")
    public String allsongs(Model model){
        Collection<Song> songs=songHolder.getAll();
        model.addAttribute("song",songs);

        return "Portal";
    }

    @GetMapping("/new/song")
    public String newsong(){
        return "NewSong";
    }

    @PostMapping("/new/song")
    public String addSong(Song song){
        songHolder.addSong(song);

        return "song_success";
    }

    @GetMapping("/Song/{num}")
    public String showSong(Model model, @PathVariable int num){
        Song song= songHolder.getSong(num);
        model.addAttribute("song",song);

        if (song.getComments()!=null){
            if (!song.getComments().isEmpty()){
                model.addAttribute("comment",song.getComments().values());
            }
        }
        return "Song";
    }
}
