package com.bty.music_player.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bty.music_player.entity.Permission;
import java.util.Optional;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    boolean existsByName(String name);
    Optional<Permission> findByName(String name);
}
