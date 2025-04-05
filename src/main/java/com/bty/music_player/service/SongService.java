package com.bty.music_player.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.bty.music_player.dto.request.SongCreationRequest;
import com.bty.music_player.dto.response.SongResponse;
import com.bty.music_player.entity.Singer;
import com.bty.music_player.entity.Song;
import com.bty.music_player.entity.SongOfSinger;
import com.bty.music_player.exception.AppException;
import com.bty.music_player.exception.ErrorCode;
import com.bty.music_player.mapper.SongMapper;
import com.bty.music_player.repository.SingerRepository;
import com.bty.music_player.repository.SongOfSingerRepository;
import com.bty.music_player.repository.SongRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SongService {
    SongRepository songRepository;
    SongMapper songMapper;
    SingerRepository singerRepository;
    SongOfSingerRepository songOfSingerRepository;
    
    @PreAuthorize("hasAuthority('PER_GET_ALL_SONGS')")
    public List<SongResponse> getAll() {
        return songRepository.findAll().stream().map(songMapper::toSongRespone).toList();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    public SongResponse create(SongCreationRequest request) {
        if(songRepository.existsByName(request.getName())) throw new AppException(ErrorCode.SONGNAME_EXISTED);
        request.getSingerNames().forEach(singerName -> {
            if(!singerRepository.existsByName(singerName)) throw new AppException(ErrorCode.SINGER_NOTEXIST);
        });
        Song song = songMapper.toSong(request);
        song = songRepository.save(song);
        List<SongOfSinger> songOfSingers = new ArrayList<SongOfSinger>();
        for(String singerName : request.getSingerNames()) {
            Singer singer = singerRepository.findByName(singerName).orElseThrow(() -> new AppException(ErrorCode.SINGER_NOTEXIST));
            SongOfSinger songOfSinger = SongOfSinger.builder()
                .song(song)
                .singer(singer)
                .build();
                songOfSinger = songOfSingerRepository.save(songOfSinger);
                songOfSingers.add(songOfSinger);
        }
        song.setSingers(songOfSingers);
        SongResponse songRespone = songMapper.toSongRespone(song);
        return songRespone;
    }
}
