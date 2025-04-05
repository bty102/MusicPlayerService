package com.bty.music_player.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bty.music_player.entity.Singer;



@Repository
public interface SingerRepository extends JpaRepository<Singer, Integer> {
    Optional<Singer> findByName(String name);
    boolean existsByName(String name);
}
