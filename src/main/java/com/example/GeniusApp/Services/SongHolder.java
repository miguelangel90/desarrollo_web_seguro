package com.example.GeniusApp.Services;


import com.example.GeniusApp.Models.Song;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SongHolder {
    private Map<Long, Song>songs=new ConcurrentHashMap<>();
    private AtomicLong id=new AtomicLong();

    public void addSong(Song song){
        long identification=id.incrementAndGet();
        songs.put(identification,song);
    }
}
