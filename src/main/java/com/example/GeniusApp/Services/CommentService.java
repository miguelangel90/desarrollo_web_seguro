package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    SongRepository songRepository;


    /*
    public void addComment(Song song, Comment comment){
        song.getComments().add(comment);
        commentRepository.save(comment);
    }*/

    public List<Comment> getComments(Song song){
        List<Comment> comments = song.getComments();
        return comments;
    }

    public Comment getComment(long id){
        Comment comment = commentRepository.getById(id);
        return comment;
    }

    public void removeComment(long cid, long sid) {
        Song song = songRepository.getById(sid);
        Comment comment = commentRepository.getById(cid);
        song.getComments().remove(comment);
        commentRepository.deleteById(cid);
    }

    public void updateComment(long id, Comment comment){
        comment.setId(id);
        commentRepository.save(comment);
    }

    public void removeCommentByText(String text, long sid){
        Song song = songRepository.getById(sid);
        for (Comment c: song.getComments()){
            if (c.getText().equals(text)){
                song.getComments().remove(c);
            }
        }
        commentRepository.deleteByText(text);
    }

    public void addComment(Song song, String comment, String userName){
        Comment c = new Comment(comment);
        c.setOwner(userName);
        song.getComments().add(c);
        commentRepository.save(c);
    }

    public List<Comment> getCommentByText(String text){
        return commentRepository.findCommentsByTextContains(text);
    }
}
