package com.bty.music_player.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bty.music_player.dto.request.SingerCreationRequest;
import com.bty.music_player.dto.response.SingerResponse;
import com.bty.music_player.entity.Singer;

@Mapper(componentModel = "spring")
public interface SingerMapper {

    @Mapping(target = "name", source = "name")
    SingerResponse toSingerResponse(Singer singer);
    
    @Mapping(target = "name", source = "name")
    Singer toSinger(SingerCreationRequest request);
    
}
