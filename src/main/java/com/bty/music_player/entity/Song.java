package com.bty.music_player.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "song")
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    
    @Column(name = "name", unique = true, nullable = false)
    String name;
    
    @Column(name = "image", nullable = true)
    String image;
    
    @Column(name = "path", nullable = false)
    String path;
    
    @Column(name = "lyrics", nullable = true)
    String lyrics;
    
    @OneToMany(mappedBy = "song")
    List<SongOfSinger> singers;
}
