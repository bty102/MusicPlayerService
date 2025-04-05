package com.bty.music_player.configuration;


import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bty.music_player.entity.Account;
import com.bty.music_player.entity.Permission;
import com.bty.music_player.entity.Role;
import com.bty.music_player.exception.AppException;
import com.bty.music_player.exception.ErrorCode;
import com.bty.music_player.repository.AccountRepository;
import com.bty.music_player.repository.PermissionRepository;
import com.bty.music_player.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    
    PasswordEncoder passwordEncoder;


    @Bean
    ApplicationRunner applicationRunner(PermissionRepository permissionRepository,
        RoleRepository roleRepository,
        AccountRepository accountRepository) throws Exception {
        return args -> {
        
            permissionRepository.findByName("GET_ALL_SONGS")
                .ifPresentOrElse(
                    permission -> {
                        log.info("permission \"GET_ALL_SONGS\" existed");
                    },
                    () -> {
                        Permission permission = Permission.builder()
                            .name("GET_ALL_SONGS")
                            .describe("get all songs")
                            .build();
                        permissionRepository.save(permission);
                        log.info("created permission: GET_ALL_SONGS");
                    });

            permissionRepository.findByName("GET_ALL_ACCOUNTS")
                .ifPresentOrElse(
                    permission -> {
                        log.info("permission \"GET_ALL_ACCOUNTS\" existed");
                    }, 
                    () -> {
                        Permission permission = Permission.builder()
                                .name("GET_ALL_ACCOUNTS")
                                .describe("get all accounts")
                                .build();
                        permissionRepository.save(permission);
                        log.info("created permission: GET_ALL_ACCOUNTS");
                    });
            
            roleRepository.findByName("ADMIN")
                .ifPresentOrElse(
                    role -> {
                        log.info("role \"ADMIN\" existed");
                    }, 
                    () -> {
                        Set<Permission> permissions = new HashSet<>();
                        permissionRepository.findByName("GET_ALL_SONGS")
                            .ifPresent(permission -> {
                                permissions.add(permission);
                            });
                        permissionRepository.findByName("GET_ALL_ACCOUNTS")
                            .ifPresent(permission -> {
                                permissions.add(permission);
                            });
                        Role adminRole = Role.builder()
                            .name("ADMIN")
                            .describe("admin role")
                            .permissions(permissions)
                            .build();
                        roleRepository.save(adminRole);
                        log.info("created admin role");
                    });
            
            roleRepository.findByName("USER")
                .ifPresentOrElse(
                    role -> {
                        log.info("role \"USER\" existed");
                    }, 
                    () -> {
                        Set<Permission> permissions = new HashSet<>();
                        permissionRepository.findByName("GET_ALL_SONGS")
                            .ifPresent(permission -> {
                                permissions.add(permission);
                            });
                        Role userRole = Role.builder()
                            .name("USER")
                            .describe("user role")
                            .permissions(permissions)
                            .build();
                        roleRepository.save(userRole);
                        log.info("created user role");
                    });
            
            accountRepository.findByAccountName("admin")
                    .ifPresentOrElse(
                        account -> {
                            log.info("account \"admin\" existed");
                        }, 
                        () -> {
                            Set<Role> roles = new HashSet<>();
                            roleRepository.findByName("ADMIN")
                                .ifPresent(role -> {
                                    roles.add(role);
                                });
                            Account adminAccount = Account.builder()
                                .accountName("admin")
                                .password(passwordEncoder.encode("admin"))
                                .roles(roles)
                                .build();
                            accountRepository.save(adminAccount);
                            log.info("created admin account");
                        });
        };   
    }
}
