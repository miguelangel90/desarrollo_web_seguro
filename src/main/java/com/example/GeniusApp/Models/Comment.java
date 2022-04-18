package com.example.GeniusApp.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.awt.print.Book;


@Data
@NoArgsConstructor
public class Comment {

    private long id = -1;
    private String text;


    public Comment(String comment){
        this.text = comment;
    }

    private Book book;
}