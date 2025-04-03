package com.bty.music_player.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    SINGERNAME_EXISTED(1001, "singer name existed", HttpStatus.BAD_REQUEST),
    SINGERNAME_ISNULL(1002, "singer name must be not null", HttpStatus.BAD_REQUEST),
    SONGNAME_ISNULL(1003, "song name must be not null", HttpStatus.BAD_REQUEST),
    SONGNAME_EXISTED(1004, "song name existed", HttpStatus.BAD_REQUEST),
    SONGPATH_ISNULL(1005, "song path must be not null", HttpStatus.BAD_REQUEST),
    SINGERNAMELIST_ISNULL(1006, "singer name list of song must be not null", HttpStatus.BAD_REQUEST),
    SINGER_NOTEXIST(1007, "singer do not exist", HttpStatus.BAD_REQUEST),
    SONG_NOTEXIST(1008, "song do not exist", HttpStatus.BAD_REQUEST),
    SONGOFSINGER_EXISTED(1009, "song of singer existed", HttpStatus.BAD_REQUEST),
    SINGERNAMELEN_NOTVALID(1010, "singer name length must be at least 1 char", HttpStatus.BAD_REQUEST),
    SONGID_ISNULL(1011, "song id must be not null", HttpStatus.BAD_REQUEST),
    SINGERID_ISNULL(1012, "singer id must be not null", HttpStatus.BAD_REQUEST),
    FT_ISNULL(1013, "ft must be not null", HttpStatus.BAD_REQUEST),
    SONGOFSINGER_NOTEXIST(1014, "song of singer is not exist", HttpStatus.BAD_REQUEST),
    ACCOUNTNAME_ISNULL(1015, "accout name must be not null", HttpStatus.BAD_REQUEST),
    ACCOUNTPW_ISNULL(1016, "accout password must be not null", HttpStatus.BAD_REQUEST),
    ACCOUNTNAMELEN_NOTVALID(1017, "account name length must be at least 3 char", HttpStatus.BAD_REQUEST),
    ACCOUNTPWLEN_NOTVALID(1018, "account password length must be at least 3 char", HttpStatus.BAD_REQUEST),
    ACCOUNT_EXISTED(1019, "account existed", HttpStatus.BAD_REQUEST),
    ACCOUNTID_ISNULL(1020, "account id must be not null", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOTEXIST(1021, "account is not exist", HttpStatus.BAD_REQUEST),
    PERMISSIONNAME_ISNULL(1022, "permission name must be not null", HttpStatus.BAD_REQUEST),
    PERMISSION_EXISTED(1023, "permission existed", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1024, "role existed", HttpStatus.BAD_REQUEST),
    ROLENAME_ISNULL(1025, "role name must be not null", HttpStatus.BAD_REQUEST),
    ROLENAMELEN_NOTVALID(1026, "role name length must be at least 1 char", HttpStatus.BAD_REQUEST),
    PERMISSIONNAMELEN_NOTVALID(1027, "permission name length must be at least 1 char", HttpStatus.BAD_REQUEST),
    ;

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
