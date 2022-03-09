package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@Service
public class CommentHolder {

    private Map<Long, Comment> comments;
    private AtomicLong id;

    public CommentHolder(){
        this.comments=new ConcurrentHashMap<>();
        this.id=new AtomicLong();
    }

    public void addComment(Comment comment){
        long identification=id.incrementAndGet();
        comment.setId(identification);

        comments.put(identification,comment);
    }

    public Comment getComment(long id){
        Comment c = comments.get(id);
        return c;
    }


    public void removeComment(long id){
        comments.remove(id);
    }

    public void updateComment(long id, Comment updateComment) {
        updateComment.setId(id);
        comments.put(updateComment.getId(), updateComment);
    }
}
