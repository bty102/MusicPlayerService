package com.bty.music_player.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bty.music_player.dto.request.SongOfSingerCreationRequest;
import com.bty.music_player.dto.request.SongOfSingerDeleteRequest;
import com.bty.music_player.dto.request.SongOfSingerUpdateRequest;
import com.bty.music_player.dto.response.SingerResponse;
import com.bty.music_player.dto.response.SongOfSingerResponse;
import com.bty.music_player.dto.response.SongResponse;
import com.bty.music_player.entity.Singer;
import com.bty.music_player.entity.Song;
import com.bty.music_player.entity.SongOfSinger;
import com.bty.music_player.entity.SongOfSingerId;
import com.bty.music_player.exception.AppException;
import com.bty.music_player.exception.ErrorCode;
import com.bty.music_player.mapper.SingerMapper;
import com.bty.music_player.mapper.SongMapper;
import com.bty.music_player.repository.SingerRepository;
import com.bty.music_player.repository.SongOfSingerRepository;
import com.bty.music_player.repository.SongRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongOfSingerService {
    SongOfSingerRepository songOfSingerRepository;
    SongMapper songMapper;
    SingerMapper singerMapper;
    SongRepository songRepository;
    SingerRepository singerRepository;
    
    public List<SongOfSingerResponse> getAll() {
        List<SongOfSingerResponse> songOfSingerResponses = new ArrayList<>();
        songOfSingerRepository.findAll().forEach(songOfSinger -> {
            SongResponse songRespone = songMapper.toSongRespone(songOfSinger.getSong());
            SingerResponse singerResponse = singerMapper.toSingerResponse(songOfSinger.getSinger());
            SongOfSingerResponse songOfSingerResponse = SongOfSingerResponse.builder()
                .song(songRespone)
                .singer(singerResponse)
                .isFt(songOfSinger.isFt())
                .build();
            songOfSingerResponses.add(songOfSingerResponse);
        });;
        return songOfSingerResponses;
    }
    
    public SongOfSingerResponse create(SongOfSingerCreationRequest request) {
        Song song = songRepository.findById(request.getSongId()).orElseThrow(() -> new AppException(ErrorCode.SONG_NOTEXIST));
        Singer singer = singerRepository.findById(request.getSingerId()).orElseThrow(() -> new AppException(ErrorCode.SINGER_NOTEXIST));
        SongOfSingerId songOfSingerId = new SongOfSingerId(song, singer);
        if(songOfSingerRepository.existsById(songOfSingerId)) {
            throw new AppException(ErrorCode.SONGOFSINGER_EXISTED);
        }
        SongOfSinger songOfSinger = SongOfSinger.builder()
            .song(song)
            .singer(singer)
            .build();
        if(request.getIsFt() != null) songOfSinger.setFt(request.getIsFt());
        songOfSinger = songOfSingerRepository.save(songOfSinger);
        SongOfSingerResponse songOfSingerResponse = toSongOfSingerResponse(songOfSinger);
        return songOfSingerResponse;
    }
    
    private SongOfSingerResponse toSongOfSingerResponse(SongOfSinger songOfSinger) {
        SongOfSingerResponse songOfSingerResponse = SongOfSingerResponse.builder()
            .singer(singerMapper.toSingerResponse(songOfSinger.getSinger()))
            .song(songMapper.toSongRespone(songOfSinger.getSong()))
            .isFt(songOfSinger.isFt())
            .build();
        return songOfSingerResponse;
    }
    
    public SongOfSingerResponse updateFt(SongOfSingerUpdateRequest request) {
        Song song = songRepository.findById(request.getSongId())
            .orElseThrow(() -> new AppException(ErrorCode.SONG_NOTEXIST));
        
        Singer singer = singerRepository.findById(request.getSingerId())
            .orElseThrow(() -> new AppException(ErrorCode.SINGER_NOTEXIST));
        
        SongOfSingerId songOfSingerId = new SongOfSingerId(song, singer);
        SongOfSinger songOfSinger = songOfSingerRepository.findById(songOfSingerId)
            .orElseThrow(() -> new AppException(ErrorCode.SONGOFSINGER_NOTEXIST));
        songOfSinger.setFt(request.getIsFt());
        
        songOfSinger = songOfSingerRepository.save(songOfSinger);
        return toSongOfSingerResponse(songOfSinger);
    }
    
    public void delete(SongOfSingerDeleteRequest request) {
        Song song = songRepository.findById(request.getSongId())
            .orElseThrow(() -> new AppException(ErrorCode.SONG_NOTEXIST));
        
        Singer singer = singerRepository.findById(request.getSingerId())
            .orElseThrow(() -> new AppException(ErrorCode.SINGER_NOTEXIST));
        
        SongOfSingerId songOfSingerId = new SongOfSingerId(song, singer);
        songOfSingerRepository.deleteById(songOfSingerId);
    }
}
