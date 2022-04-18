package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Services.SongRepository;
import com.example.GeniusApp.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class SongController {
    @Autowired
    SongService songHolder;

    @Autowired
    SongRepository songRepository;

    @GetMapping("")
    public String start(){
        return "Start";
    }

    @GetMapping("/songs")
    public String allsongs(Model model){
        Collection<Song> songs = songRepository.findAll();
        model.addAttribute("song",songs);
        return "Portal";
    }

    @GetMapping("/new/song")
    public String newsong(){
        return "NewSong";
    }

    @PostMapping("/new/song")
    public String addSong(Song song){
        songRepository.save(song);
        return "song_success";
    }

    @GetMapping("/Song/{num}")
    public String showSong(Model model, @PathVariable long num){
        Song song = songRepository.getById(num);
        model.addAttribute("song",song);
        model.addAttribute("comment",song.collectionComments());
        return "Song";
    }

    @GetMapping("/songs/delete/{Sid}")
    public String deleteSong(Model model,@PathVariable Long Sid){
        Song song = songRepository.getById(Sid);
        model.addAttribute("song",song);
        songRepository.delete(song);
        return "delete_success";
    }
}
