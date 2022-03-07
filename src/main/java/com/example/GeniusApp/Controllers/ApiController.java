package com.example.GeniusApp.Controllers;


import com.example.GeniusApp.Models.Song;
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

}
