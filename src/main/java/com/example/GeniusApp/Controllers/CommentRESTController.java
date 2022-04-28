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
import java.util.Collection;
import java.util.List;

@RestController
public class CommentRESTController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    SongService songService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    SongRepository songRepository;

    @GetMapping("/songs/{id}/allcomments")
    public List<Comment> getAllCommnets(@PathVariable long id){
        Song song = songRepository.getById(id);
        return commentService.getComments(song);
    }

    @PostMapping("/songs/{id}")
    public ResponseEntity<Comment> createComment(@PathVariable long id, @RequestBody Comment comment){
        Song song = songRepository.getById(id);
        commentService.addComment(song,comment);
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
        return commentRepository.findCommentsByTextContains(string);
    }

    @Transactional
    @DeleteMapping("/songs/{sid}/{cid}")
    public void deleteComment(@PathVariable long sid, @PathVariable long cid) {
        /*Query query = entityManager.createQuery
                ("DELETE FROM Comment c WHERE c.id = :cid");
        return query.setParameter("cid", cid).executeUpdate();*/
        Song song = songRepository.getById(sid);
        Comment comment = commentRepository.getById(cid);
        song.getComments().size();
        song.getComments().remove(comment);
        commentRepository.deleteById(cid);
    }

    @Transactional
    @DeleteMapping("/songs/{sid}/comentario/{text}")
    public void deleteCommentByText(@PathVariable long sid, @PathVariable String text) {
        /*Query query = entityManager.createQuery
                ("DELETE FROM Comment c WHERE c.text = :text");
        return query.setParameter("text", text).executeUpdate();*/
        Song song = songRepository.getById(sid);
        song.getComments().size();
        commentService.removeCommentByText(text,sid);
        commentRepository.deleteByText(text);
    }

    /*@PutMapping("/songs/{sid}/{cid}")
    public ResponseEntity<Comment> updateComment(@PathVariable long sid, @PathVariable long cid, @RequestBody Comment updatedComment) {
        Song song = songHolder.getSong(sid);
        if (song != null) {
            song.updateComment(cid, updatedComment);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

}
