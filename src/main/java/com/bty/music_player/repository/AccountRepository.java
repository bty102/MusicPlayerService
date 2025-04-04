package com.bty.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bty.music_player.entity.Account;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByAccountName(String accountName);
    Optional<Account> findByAccountName(String accountName);
}
