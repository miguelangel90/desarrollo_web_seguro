package com.example.GeniusApp.Services;


import com.example.GeniusApp.Models.Song;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SongHolder {
    private Map<Long, Song>songs=new ConcurrentHashMap<>();
    private AtomicLong id=new AtomicLong();

    public void addSong(Song song){     // Stores a song in the holder, after giving it an id and date.
        long identification=id.incrementAndGet();
        song.setId(identification);
        song.addDate(new Date());
        songs.put(song.getId(),song);
    }
    public Collection<Song> getAll(){
        return songs.values();
    }

    public Song getSong(long id){
        return songs.get(id);
    }

    public void removeSong(long id){
        songs.remove(id);
    }

    public void updateSong(long id, Song updateSong){
        updateSong.setId(id);
        updateSong.addDate(new Date());
        songs.put(updateSong.getId(),updateSong);
    }
}
