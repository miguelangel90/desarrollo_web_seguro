package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Services.CommentService;
import com.example.GeniusApp.Services.SongRepository;
import com.example.GeniusApp.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    SongService songService;

    @Autowired
    SongRepository songRepository;

    @Autowired
    CommentService commentService;

    @PostMapping("/new/comment/{songId}")
    public String addComment(Model model,  Comment comment, @PathVariable Long songId){
        Song song=songRepository.getById(songId);
        model.addAttribute("song", song);
        commentService.addComment(song,comment);
        return "comment_success";
    }

    @GetMapping("/Song/delete/{Sid}/{Cid}")
    public String deleteComment(Model model,@PathVariable Long Sid,@PathVariable Long Cid){
        Song song=songRepository.getById(Sid);
        commentService.removeComment(Cid);
        model.addAttribute("song",song);
        if (song.getComments()!=null){
            if (!song.getComments().isEmpty()){
                model.addAttribute("comment",song.getComments());
            }
        }
        return "delete_comment";
    }
}
