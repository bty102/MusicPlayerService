package com.bty.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bty.music_player.entity.Role;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
}
