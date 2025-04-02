package com.bty.music_player.dto.response;

import java.util.List;

import com.bty.music_player.entity.SongOfSinger;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SongResponse {
    
    String name;
    
    String image;
    
    String path;
    
    String lyrics;
    
    String singers;
}
