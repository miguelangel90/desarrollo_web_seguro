package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query(value = "select * from Song s where s.name = :nombre ", nativeQuery = true)
    List<Song> songsByNombre(@Param("nombre") String nombre);

    @Query(value = "select * from Song s where s.album = :album ", nativeQuery = true)
    List<Song> songsByAlbum(@Param("album") String album);

    @Query(value = "select * from Song s where s.author = :autor", nativeQuery = true)
    List<Song> songsByAutor(@Param("autor") String autor);

    @Query(value = "select * from Song s where s.name = :nombre and s.album = :album", nativeQuery = true)
    List<Song> songsByNombreAndAlbum(@Param("nombre") String nombre, @Param("album") String album);

    @Query(value = "select * from Song s where s.name = :nombre and s.author = :autor", nativeQuery = true)
    List<Song> songsByNombreAndAutor(@Param("nombre") String nombre, @Param("autor") String autor);

    @Query(value = "select * from Song s where s.album = :album and s.author = :autor", nativeQuery = true)
    List<Song> songsByAlbumAndAutor(@Param("album") String album, @Param("autor") String autor);

    @Query(value = "select * from Song s where s.album = :album and s.author = :autor and s.name = :nombre", nativeQuery = true)
    List<Song> songsByAlbumAndAutorAndNombre(@Param("album") String album, @Param("autor") String autor, @Param("nombre") String nombre);

}
