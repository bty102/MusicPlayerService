package com.bty.music_player.dto.request;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
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
public class SongCreationRequest {
    
    @NotNull(message = "SONGNAME_ISNULL")
    String name;
    
    String image;
    
    @NotNull(message = "SONGPATH_ISNULL")
    String path;
    
    String lyrics;
    
    @NotNull(message = "SINGERNAMELIST_ISNULL")
    Set<String> singerNames;
}
