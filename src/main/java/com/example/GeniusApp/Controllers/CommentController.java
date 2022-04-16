package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Services.SongHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    @Autowired
    SongHolder songHolder;


    @PostMapping("/new/comment/{songId}")
    public String addComment(Model model, Comment comment, @PathVariable Long songId){

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
        return "delete_comment";
    }

}
