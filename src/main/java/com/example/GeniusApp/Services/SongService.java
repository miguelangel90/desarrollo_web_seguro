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

    public void addSong(Song song){     // Stores a song in the holder, after giving it an id and date.
        song.addDate(new Date());
        songRepository.save(song);
    }
    public Collection<Song> getAll(){
        return songRepository.findAll();
    }

    public Song getSong(long id){
        return songRepository.getById(id);
    }

    public void removeSong(long id){
        songRepository.deleteById(id);
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
        if (name != null && album == null && author == null){
            return songRepository.songsByNombre(name);
        }else if (name == null && album != null && author == null){
            return songRepository.songsByAlbum(album);
        }else if (name == null && album == null && author != null){
            return songRepository.songsByAutor(author);
        }else if (name != null && album != null && author == null){
            return songRepository.songsByNombreAndAlbum(name,album);
        }else if (name != null && album == null && author != null){
            return songRepository.songsByNombreAndAutor(name,author);
        }else if (name == null && album != null && author != null){
            return songRepository.songsByAlbumAndAutor(album,author);
        }else if (name != null && album != null && author != null){
            return songRepository.songsByAlbumAndAutorAndNombre(album,author,name);
        }else{
            return null;
        }
    }
}
