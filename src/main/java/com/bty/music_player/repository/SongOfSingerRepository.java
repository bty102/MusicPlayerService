package com.bty.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bty.music_player.entity.SongOfSinger;
import com.bty.music_player.entity.SongOfSingerId;

@Repository
public interface SongOfSingerRepository extends JpaRepository<SongOfSinger, SongOfSingerId> {

}
