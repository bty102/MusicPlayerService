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
    ;

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
