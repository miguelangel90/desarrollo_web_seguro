package com.example.GeniusApp.Services;


import com.example.GeniusApp.Models.Song;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SongHolder {
    private Map<Long, Song>songs=new ConcurrentHashMap<>();
    private AtomicLong id=new AtomicLong();

    public void addSong(Song song){
        long identification=id.incrementAndGet();
        song.setId(identification);
        songs.put(song.getId(),song);
        //song.getCommentHolder().setIdSong(song.getId());  ///ESTO ES LO QUE DA ERROR
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
        songs.put(updateSong.getId(),updateSong);
    }
}
