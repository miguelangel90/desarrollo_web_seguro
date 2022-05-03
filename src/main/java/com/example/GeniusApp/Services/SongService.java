package com.example.GeniusApp.Services;


import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Models.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SongService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    SongRepository songRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public void addSong(Song song, String creator){     // Stores a song in the holder, after giving it an id and date.
        song.addDate(new Date());
        song.setOwner(creator);
        songRepository.save(song);
    }
    public List<Song> getAll(){
        return songRepository.findAll();
    }

    public Song getSong(long id){
        return songRepository.getById(id);
    }

    public void removeSongById(long id){
        songRepository.deleteById(id);
    }

    public void removeSong(Song song){
        songRepository.delete(song);
    }

    public void updateSong(long id, Song updateSong){
        updateSong.setId(id);
        updateSong.addDate(new Date());
        songRepository.save(updateSong);
    }

    public void updateLyrics(long id, String lyrics){
        Song song = songRepository.getById(id);
        song.updateLyrics(lyrics);
        songRepository.save(song);
    }

    public List<Song> searchSongs(String name, String album, String author){
        if (name != "" && album == "" && author == ""){
            return songRepository.songsByNombre(name);
        }else if (name == "" && album != "" && author == ""){
            return songRepository.songsByAlbum(album);
        }else if (name == "" && album == "" && author != ""){
            return songRepository.songsByAutor(author);
        }else if (name != "" && album != "" && author == ""){
            return songRepository.songsByNombreAndAlbum(name,album);
        }else if (name != "" && album == "" && author != ""){
            return songRepository.songsByNombreAndAutor(name,author);
        }else if (name == "" && album != "" && author != ""){
            return songRepository.songsByAlbumAndAutor(album,author);
        }else if (name != "" && album != "" && author != ""){
            return songRepository.songsByAlbumAndAutorAndNombre(album,author,name);
        }else{
            return null;
        }
    }
}
