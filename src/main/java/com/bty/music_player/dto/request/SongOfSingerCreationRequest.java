package com.bty.music_player.dto.request;

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
public class SongOfSingerCreationRequest {
    
    @NotNull(message = "SONGID_ISNULL")
    Integer songId;
    
    @NotNull(message = "SINGERID_ISNULL")
    Integer singerId;

    Boolean isFt;
}
