package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Services.CommentRepository;
import com.example.GeniusApp.Services.CommentService;
import com.example.GeniusApp.Services.SongRepository;
import com.example.GeniusApp.Services.SongService;
import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRESTController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    SongService songService;

    @Autowired
    CommentService commentService;


    @GetMapping("/songs/{id}/allcomments")
    public List<Comment> getAllCommnets(@PathVariable long id){
        Song song = songService.getSong(id);
        return commentService.getComments(song);
    }

    @PostMapping("/songs/{id}")
    public ResponseEntity<Comment> createComment(HttpServletRequest request, @PathVariable long id, @RequestBody Comment comment){
        Song song = songService.getSong(id);
        commentService.addComment(song,Sanitizers.FORMATTING.sanitize(comment.getText()), request.getUserPrincipal().getName());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/songs/{sid}/{cid}")
    public Comment getComment(@PathVariable long sid, @PathVariable long cid) {
        TypedQuery<Comment> query = entityManager.createQuery
                ("SELECT c FROM Comment c WHERE c.id= :cid", Comment.class);
        return query.setParameter("cid", cid).getSingleResult();
    }

    @GetMapping("/songs/{sid}/contiene/{string}")
    public List<Comment> getComment2(@PathVariable long sid,@PathVariable String string) {
        return commentService.getCommentByText(string);
    }

    @Transactional
    @DeleteMapping("/songs/{sid}/{cid}")
    public int deleteComment(HttpServletRequest request, @PathVariable long sid, @PathVariable long cid) {
        Song song = songService.getSong(sid);
        Comment comment = commentService.getComment(cid);
        if (request.getUserPrincipal().getName().equals(comment.getOwner()) || request.isUserInRole("ADMIN")){
            song.getComments().size();
            song.getComments().remove(comment);
            commentService.removeComment(cid,sid);
            return 1;
        }else{
            return 0;
        }
    }

    @Transactional
    @DeleteMapping("/songs/{sid}/comentario/{text}")
    public void deleteCommentByText(@PathVariable long sid, @PathVariable String text) {
        Song song = songService.getSong(sid);
        song.getComments().size();
        commentService.removeCommentByText(text,sid);
    }

}
