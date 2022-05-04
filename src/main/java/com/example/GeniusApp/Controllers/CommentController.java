package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Services.CommentService;
import com.example.GeniusApp.Services.SongRepository;
import com.example.GeniusApp.Services.SongService;


import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class CommentController {

    @Autowired
    SongService songService;

    @Autowired
    CommentService commentService;

    @PostMapping("/new/comment/{songId}")
    public String addComment(Model model, String comment, HttpServletRequest request, @PathVariable Long songId){
        Song song=songService.getSong(songId);
        model.addAttribute("song", song);
        commentService.addComment(song, Sanitizers.FORMATTING.sanitize(comment), request.getUserPrincipal().getName());
        return "comment_success";
    }

    @GetMapping("/Song/delete/{Sid}/{Cid}")
    public String deleteComment(Model model, HttpServletRequest request,@PathVariable Long Sid,@PathVariable Long Cid){
        Song song=songService.getSong(Sid);
        Comment c=commentService.getComment(Cid);
        if (request.getUserPrincipal().getName().equals(c.getOwner()) || request.isUserInRole("ADMIN")){
            commentService.removeComment(Cid, Sid);
            model.addAttribute("song",song);
            if (song.getComments()!=null){
                if (!song.getComments().isEmpty()){
                    model.addAttribute("comment",song.getComments());
                }
            }
        }
        return "delete_comment";
    }
}
