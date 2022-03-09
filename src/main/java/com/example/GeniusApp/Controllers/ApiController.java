package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Services.CommentHolder;
import com.example.GeniusApp.Services.SongHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    @Autowired
    SongHolder songHolder;

    @PostMapping("/songs")
    public ResponseEntity<Song> create(@RequestBody Song song){
        songHolder.addSong(song);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> get(@PathVariable long id) {
        Song song = songHolder.getSong(id);
        if (song != null) {
            return new ResponseEntity<>(song, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable long id) {
        songHolder.removeSong(id);
        Song song = songHolder.getSong(id);
        if (song != null) {
            return new ResponseEntity<>(song, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable long id, @RequestBody Song updatedSong) {
        Song song = songHolder.getSong(id);
        if (song != null) {
            songHolder.updateSong(id, updatedSong);
            return new ResponseEntity<>(updatedSong, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    CommentHolder commentHolder;

    @PostMapping("/songs/{id}")
    public ResponseEntity<Comment> createComment(@PathVariable long id, @RequestBody Comment comment){
        Song song = songHolder.getSong(id);
        song.addComment(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/songs/{sid}/{cid}")
    public ResponseEntity<Comment> getComment(@PathVariable long sid, @PathVariable long cid) {
        Song song = songHolder.getSong(sid);
        if (song != null) {
            Comment comment = song.getComment(cid);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/songs/{sid}/{cid}")
    public ResponseEntity<Comment> deleteComment(@PathVariable long sid, @PathVariable long cid) {
        Song song = songHolder.getSong(sid);
        if (song != null) {
            song.removeComment(cid);
            Comment comment = song.getComment(cid);
            if (comment != null){
                return new ResponseEntity<>(comment, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/songs/{sid}/{cid}")
    public ResponseEntity<Comment> updateComment(@PathVariable long sid, @PathVariable long cid, @RequestBody Comment updatedComment) {
        Song song = songHolder.getSong(sid);
        if (song != null) {
            song.updateComment(cid, updatedComment);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
