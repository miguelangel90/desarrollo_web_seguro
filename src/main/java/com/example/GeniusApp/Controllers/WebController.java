package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Models.Users.User;
import com.example.GeniusApp.Services.CommentHolder;
import com.example.GeniusApp.Services.SongHolder;
import com.example.GeniusApp.Services.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Controller
public class WebController {

    @Autowired
    SongHolder songHolder;

    @Autowired
    UserHolder userHolder;

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

    @PostMapping("/new/comment/{songId}")
    public String addComment(Model model, Comment comment, @PathVariable Long songId){
        //commentHolder.addComment(comment);
        //Long myid=(Long)songId;

        Song song=songHolder.getSong(songId);
        model.addAttribute("song", song);
        song.addComment(comment);
        return "comment_success";
    }

    @GetMapping("/Song/delete/{Sid}/{Cid}")
    public String deleteComment(Model model,@PathVariable Long Sid,@PathVariable Long Cid){
        Song song =songHolder.getSong(Sid);
        song.getCommentHolder().getComments().remove(Cid);
        model.addAttribute("song",song);
        if (song.getComments()!=null){
            if (!song.getComments().isEmpty()){
                model.addAttribute("comment",song.getComments().values());
            }
        }
        return "Song";
    }

    @GetMapping("/songs/delete/{Sid}")
    public String deleteSong(Model model,@PathVariable Long Sid){
        Song song =songHolder.getSong(Sid);
        model.addAttribute("song",song);
        songHolder.removeSong(Sid);
        return "delete_success";
    }

    @GetMapping("/new/user")
    public String register(){

        return "Register";
    }

    @PostMapping("/new/user")
    public String newUser(User user){
        userHolder.addUser(user);
        return "user_success";
    }
}
