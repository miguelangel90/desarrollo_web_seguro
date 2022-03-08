package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CommentHolder {

    @Autowired
    SongHolder songHolder;

    private Map<Long, Comment> comments =new ConcurrentHashMap<>();
    private AtomicLong id=new AtomicLong();
    private long idSong;


    public void setIdSong(long id){
        idSong = id;
    }

    public void addComment(Comment comment){
        long identification=id.incrementAndGet();
        comment.setId(identification);
        Song song;
        song = songHolder.getSong(idSong);
        comment.setSong(song);
        comment.getSong().addComment(comment);
    }

    /*public Collection<Comment> getAll(Song song){
        Map<Long, Comment> comments = getComments(song);
        return comments.values();
    }



    public void removeSong(long id){
        songs.remove(id);
    }

    public void updateSong(long id, Song updateSong){
        updateSong.setId(id);
        songs.put(updateSong.getId(),updateSong);
    }*/
}
