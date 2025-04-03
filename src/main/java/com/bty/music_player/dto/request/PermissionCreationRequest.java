package com.bty.music_player.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionCreationRequest {
    
    @NotNull(message = "PERMISSIONNAME_ISNULL")
    @Size(min = 1, message = "PERMISSIONNAMELEN_NOTVALID")
    String name;

    String describe;
}
