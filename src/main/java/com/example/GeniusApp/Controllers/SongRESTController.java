package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Song;

import com.example.GeniusApp.Services.SongRepository;
import com.example.GeniusApp.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

@RestController
public class SongRESTController {
    @Autowired
    SongService songService;

    @Autowired
    SongRepository songRepository;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/allsongs")
    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    @PostMapping("/song")
    public ResponseEntity<Song> create(@RequestBody Song song){
        songService.addSong(song);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

    @GetMapping("/songsId/{id}")
    public Song getSong(@PathVariable long id) {
        TypedQuery<Song> query = entityManager.createQuery
                ("SELECT s from Song s WHERE s.id= :id", Song.class);
        return query.setParameter("id", id).getSingleResult();
    }

    @GetMapping("/songsLyrics/{lyrics}")
    public Song getSongLyrics(@PathVariable String lyrics) {
        TypedQuery<Song> query = entityManager.createQuery
                ("SELECT s from Song s WHERE s.lyrics= :lyrics", Song.class);
        return query.setParameter("lyrics", lyrics).getSingleResult();
    }

    @GetMapping("/songsUrl/{url}")
    public Song getSongUrl(@PathVariable String url) {
        TypedQuery<Song> query = entityManager.createQuery
                ("SELECT s from Song s WHERE s.url= :url", Song.class);
        return query.setParameter("url", url).getSingleResult();
    }

    @GetMapping("/songsNombre/{nombre}")
    public List<Song> getSongNombre(@PathVariable String nombre) {
        TypedQuery<Song> query = entityManager.createQuery
                ("SELECT s from Song s WHERE s.name= :nombre", Song.class);
        return query.setParameter("nombre", nombre).getResultList();
    }

    @GetMapping("/songsAutor/{autor}")
    public List<Song> getSongAutor(@PathVariable String autor) {
        TypedQuery<Song> query = entityManager.createQuery
                ("SELECT s from Song s WHERE s.author= :autor", Song.class);
        return query.setParameter("autor", autor).getResultList();
    }

    @GetMapping("/songsAlbum/{album}")
    public List<Song> getSongAlbum(@PathVariable String album) {
        TypedQuery<Song> query = entityManager.createQuery
                ("SELECT s from Song s WHERE s.album= :album", Song.class);
        return query.setParameter("album", album).getResultList();
    }

    @Transactional
    @DeleteMapping("/songsDeleteId/{id}")
    public int deleteSong(@PathVariable long id) {
        Query query = entityManager.createQuery
                ("DELETE FROM Song s WHERE s.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }

    @Transactional
    @DeleteMapping("/songsDeleteNombre/{nombre}")
    public int deleteSongNombre(@PathVariable String nombre) {
        Query query = entityManager.createQuery
                ("DELETE FROM Song s WHERE s.name = :nombre");
        return query.setParameter("nombre", nombre).executeUpdate();
    }

    @Transactional
    @DeleteMapping("/songsDeleteAutor/{autor}")
    public int deleteSongAutor(@PathVariable String autor) {
        Query query = entityManager.createQuery
                ("DELETE FROM Song s WHERE s.author = :autor");
        return query.setParameter("autor", autor).executeUpdate();
    }

    @Transactional
    @DeleteMapping("/songsDeleteAlbum/{album}")
    public int deleteSongAlbum(@PathVariable String album) {
        Query query = entityManager.createQuery
                ("DELETE FROM Song s WHERE s.album = :album");
        return query.setParameter("album", album).executeUpdate();
    }

    @Transactional
    @DeleteMapping("/songsDeleteLyrics/{lyrics}")
    public int deleteSongLyrics(@PathVariable String lyrics) {
        Query query = entityManager.createQuery
                ("DELETE FROM Song s WHERE s.lyrics = :lyrics");
        return query.setParameter("lyrics", lyrics).executeUpdate();
    }

    @Transactional
    @DeleteMapping("/songsDeleteUrl/{url}")
    public int deleteSongUrl(@PathVariable String url) {
        Query query = entityManager.createQuery
                ("DELETE FROM Song s WHERE s.url = :url");
        return query.setParameter("url", url).executeUpdate();
    }

    @Transactional
    @PutMapping("/songsUpdate/{nombre}")
    public int updateSong(@PathVariable String nombre, @RequestBody Song updatedSong) {
        String album = updatedSong.getAlbum();
        String nameUpdated = updatedSong.getName();
        String autor = updatedSong.getAuthor();
        String lyrics = updatedSong.getLyrics();
        String url = updatedSong.getUrl();
        Query query = entityManager.createQuery
                ("update Song s SET s.author = :autor, s.lyrics = :lyrics, s.url = :url, s.album = :album WHERE s.name = :nombre");
        query.setParameter("album", album);
        query.setParameter("autor", autor);
        query.setParameter("lyrics", lyrics);
        query.setParameter("url", url);
        //query.setParameter("nombre", nameUpdated);
        return query.setParameter("nombre", nombre).executeUpdate();
    }

}

