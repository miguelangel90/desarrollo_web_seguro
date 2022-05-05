package com.example.GeniusApp.Controllers;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;

import com.example.GeniusApp.Services.CommentService;
import com.example.GeniusApp.Services.SongRepository;
import com.example.GeniusApp.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
public class SongRESTController {
    @Autowired
    SongService songService;

    @Autowired
    CommentService commentService;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/allsongs")
    public List<Song> getAllSongs(){
        return songService.getAll();
    }

    @GetMapping("filter/{nombre}/{album}/{autor}")
    public List<Song> getByFilter(@PathVariable String nombre, @PathVariable String album, @PathVariable String autor){
        return songService.searchSongs(nombre,album,autor);
    }

    @PostMapping("/song")
    public ResponseEntity<Song> create(HttpServletRequest request, @RequestBody Song song){
        String name= request.getUserPrincipal().getName();

        songService.addSong(song,name);
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

    @GetMapping("/songsNombre/{nombre}/{album}")
    public List<Song> getSongNombreyAlbum(@PathVariable String nombre, @PathVariable String album) {
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
    public int deleteSong(HttpServletRequest request, @PathVariable long id) {
        Song song  = songService.getSong(id);
        if (request.getUserPrincipal().getName().equals(song.getOwner()) || request.isUserInRole("ADMIN")){
            Query query = entityManager.createQuery
                    ("DELETE FROM Song s WHERE s.id = :id");
            return query.setParameter("id", id).executeUpdate();
        }else{
            return 0;
        }
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
    @PutMapping("/songsUpdateNombre/{nombre}")
    public int updateSong(@PathVariable String nombre, @RequestBody Song updatedSong) {
        String album = updatedSong.getAlbum();
        String autor = updatedSong.getAuthor();
        String lyrics = updatedSong.getLyrics();
        String url = updatedSong.getUrl();
        Query query = entityManager.createQuery
                ("update Song s SET s.author = :autor, s.lyrics = :lyrics, s.url = :url, s.album = :album WHERE s.name = :nombre");
        query.setParameter("album", album);
        query.setParameter("autor", autor);
        query.setParameter("lyrics", lyrics);
        query.setParameter("url", url);
        return query.setParameter("nombre", nombre).executeUpdate();
    }

    @Transactional
    @PutMapping("/songsUpdateAlbum/{album}")
    public int updateSongAlbum(@PathVariable String album, @RequestBody Song updatedSong) {
        String nombre = updatedSong.getName();
        String autor = updatedSong.getAuthor();
        String lyrics = updatedSong.getLyrics();
        String url = updatedSong.getUrl();
        Query query = entityManager.createQuery
                ("update Song s SET s.author = :autor, s.lyrics = :lyrics, s.url = :url, s.name = :nombre WHERE s.album = :album");
        query.setParameter("nombre", nombre);
        query.setParameter("autor", autor);
        query.setParameter("lyrics", lyrics);
        query.setParameter("url", url);
        return query.setParameter("album", album).executeUpdate();
    }

    @Transactional
    @PutMapping("/songsUpdateAutor/{autor}")
    public int updateSongAutor(@PathVariable String autor, @RequestBody Song updatedSong) {
        String nombre = updatedSong.getName();
        String album = updatedSong.getAlbum();
        String lyrics = updatedSong.getLyrics();
        String url = updatedSong.getUrl();
        Query query = entityManager.createQuery
                ("update Song s SET s.album = :album, s.lyrics = :lyrics, s.url = :url, s.name = :nombre WHERE s.author = :autor");
        query.setParameter("nombre", nombre);
        query.setParameter("album", album);
        query.setParameter("lyrics", lyrics);
        query.setParameter("url", url);
        return query.setParameter("autor", autor).executeUpdate();
    }

    @Transactional
    @PutMapping("/songsUpdateUrl/{url}")
    public int updateSongUrl(@PathVariable String url, @RequestBody Song updatedSong) {
        String nombre = updatedSong.getName();
        String album = updatedSong.getAlbum();
        String lyrics = updatedSong.getLyrics();
        String autor = updatedSong.getAuthor();
        Query query = entityManager.createQuery
                ("update Song s SET s.album = :album, s.lyrics = :lyrics, s.author = :autor, s.name = :nombre WHERE s.url = :url");
        query.setParameter("nombre", nombre);
        query.setParameter("album", album);
        query.setParameter("lyrics", lyrics);
        query.setParameter("autor", autor);
        return query.setParameter("url", url).executeUpdate();
    }

    /*@Transactional
    @PutMapping("/songsUpdateLyrics/{lyrics}")
    public int updateSongLyrics(@PathVariable String lyrics, @RequestBody Song updatedSong) {
        String nombre = updatedSong.getName();
        String album = updatedSong.getAlbum();
        String url = updatedSong.getUrl();
        String autor = updatedSong.getAuthor();
        Query query = entityManager.createQuery
                ("update Song s SET s.album = :album, s.url = :url, s.author = :autor, s.name = :nombre WHERE s.lyrics = :lyrics");
        query.setParameter("nombre", nombre);
        query.setParameter("album", album);
        query.setParameter("url", url);
        query.setParameter("autor", autor);
        return query.setParameter("lyrics", lyrics).executeUpdate();
    }*/

    @Transactional
    @PutMapping("/songsUpdateLyrics/{id}")
    public int updateLyrics(@PathVariable long id, @RequestBody Song updatedSong) {
        String lyrics = updatedSong.getLyrics();
        Query query = entityManager.createQuery
                ("update Song s SET s.lyrics = :lyrics WHERE s.id = :id");
        query.setParameter("id", id);
        return query.setParameter("lyrics", lyrics).executeUpdate();
    }
}

