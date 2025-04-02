package com.bty.music_player.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreationRequest {

    @NotNull(message = "ACCOUNTNAME_ISNULL")
    @Size(min = 3, message = "ACCOUNTNAMELEN_NOTVALID")
    String accountName;

    @NotNull(message = "ACCOUNTPW_ISNULL")
    @Size(min = 3, message = "ACCOUNTPWLEN_NOTVALID")
    String password;
}
