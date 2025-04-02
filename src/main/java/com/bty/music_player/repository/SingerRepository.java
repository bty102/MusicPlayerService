package com.bty.music_player.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bty.music_player.dto.request.SingerCreationRequest;
import com.bty.music_player.dto.response.SingerResponse;
import com.bty.music_player.entity.Singer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


@Repository
public interface SingerRepository extends JpaRepository<Singer, Integer> {
    Optional<Singer> findByName(String name);
    boolean existsByName(String name);
}
