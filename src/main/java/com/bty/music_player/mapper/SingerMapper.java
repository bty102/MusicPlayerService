package com.bty.music_player.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.bty.music_player.dto.request.SingerCreationRequest;
import com.bty.music_player.dto.response.SingerResponse;
import com.bty.music_player.entity.Singer;
import com.bty.music_player.entity.Song;
import com.bty.music_player.entity.SongOfSinger;

@Mapper(componentModel = "spring")
public interface SingerMapper {

    @Mapping(target = "name", source = "name")
    SingerResponse toSingerResponse(Singer singer);
    
    @Mapping(target = "name", source = "name")
    Singer toSinger(SingerCreationRequest request);
    
}
