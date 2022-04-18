package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

}
