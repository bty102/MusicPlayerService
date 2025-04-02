package com.bty.music_player.mapper;

import java.util.List;
import java.util.StringJoiner;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.bty.music_player.dto.request.SongCreationRequest;
import com.bty.music_player.dto.response.SongResponse;
import com.bty.music_player.entity.Song;
import com.bty.music_player.entity.SongOfSinger;

@Mapper(componentModel = "spring")
public interface SongMapper {
    
    @Mapping(source = "name", target = "name")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "path", target = "path")
    @Mapping(source = "lyrics", target = "lyrics")
    @Mapping(source = "singers", target = "singers", qualifiedByName = "toStringSingers")
    SongResponse toSongRespone(Song song);
    
    @Named("toStringSingers")
    public static String toStringSingers(List<SongOfSinger> singers) {
        StringJoiner mainSingers = new StringJoiner(" & ");
        StringJoiner ftSingers = new StringJoiner(" & ");
        for(SongOfSinger singer : singers) {
            if(singer.isFt()) {
                ftSingers.add(singer.getSinger().getName());
            }
            else mainSingers.add(singer.getSinger().getName());
        };
        String str_singers = (ftSingers.length() == 0) ? 
            mainSingers.toString() 
            : mainSingers.toString() + " FT. " + ftSingers.toString();
        return str_singers;
    }
    
    @Mapping(source = "name", target = "name")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "path", target = "path")
    @Mapping(source = "lyrics", target = "lyrics")
    @Mapping(target = "singers", ignore = true)
    Song toSong(SongCreationRequest request);
}
