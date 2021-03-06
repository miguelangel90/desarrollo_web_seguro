package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Comment;
import com.example.GeniusApp.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentById(long id);

    List<Comment> findCommentsByText(String text);

    List<Comment> findCommentsByTextContains(String string);

    void deleteByText(String text);


}