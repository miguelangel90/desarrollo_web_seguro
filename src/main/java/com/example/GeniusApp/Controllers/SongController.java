package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Models.Users.User;
import com.example.GeniusApp.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class SongController {
    @Autowired
    SongService songService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String start(){
        return "Start";
    }

    @GetMapping("/songs")
    public String allsongs(Model model){
        Collection<Song> songs = songService.getAll();
        User u=(User)model.getAttribute("user");
        model.addAttribute("song",songs);
        return "Portal";
    }

    @GetMapping("/new/song")
    public String newsong(){
        return "NewSong";
    }

    @PostMapping("/new/song")
    public String addSong(Song song){
        songService.addSong(song);
        return "song_success";
    }

    @GetMapping("/Song/{num}")
    public String showSong(Model model, @PathVariable long num){
        Song song = new Song(songService.getSong(num));
        model.addAttribute("song",song);
        model.addAttribute("comment",song.collectionComments());
        return "Song";
    }

    // Search a list of songs matching a set of parameters given
    @PostMapping("/songs/search")
    public String searchSong(Model model, @RequestParam String name, @RequestParam String author,
                             @RequestParam String album){
        List<Song> results=songService.searchSongs(name,album,author);

        model.addAttribute("results",results);

        return "search_results";
    }

    @GetMapping("/songs/delete/{Sid}")
    public String deleteSong(Model model,@PathVariable Long Sid){
        Song song = songService.getSong(Sid);
        model.addAttribute("song",song);
        songService.removeSongById(Sid);
        return "delete_success";
    }

    @GetMapping("/songs/update/{Sid}")
    public String updateSong(Model model,@PathVariable Long Sid){
        Song song = songService.getSong(Sid);
        model.addAttribute("song",song);
        return "update_lyrics";
    }

    @PostMapping("/new/lyrics/{Sid}")
    public String update(Model model, @RequestParam String lyrics, @PathVariable Long Sid){
        songService.updateLyrics(Sid,lyrics);
        User user = userService.getLogueado();
        Song song = new Song(songService.getSong(Sid));
        song.addUser(user);
        songService.addSong(song);
        user.addSong(song);
        userService.addUser(user);
        model.addAttribute("song",song);
        return "lyrics_success";
    }
}
