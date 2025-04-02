package com.bty.music_player.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SingerCreationRequest {
    
    @NotNull(message = "SINGERNAME_ISNULL")
    @Size(min = 1, message = "SINGERNAMELEN_NOTVALID")
    String name;
}
