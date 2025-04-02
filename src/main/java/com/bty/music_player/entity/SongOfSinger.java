package com.bty.music_player.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@IdClass(SongOfSingerId.class)
@Table(name = "song_singer")
public class SongOfSinger {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "song_id")
    Song song;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "singer_id")
    Singer singer;
    
    
    @Column(name = "is_ft", nullable = false)
    boolean isFt;
}
