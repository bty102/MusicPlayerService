package com.bty.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bty.music_player.entity.Song;
import java.util.List;
import java.util.Optional;


@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    boolean existsByName(String name);
    Optional<Song> findByName(String name);
}
