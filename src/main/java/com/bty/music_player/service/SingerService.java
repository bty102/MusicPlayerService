package com.bty.music_player.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bty.music_player.dto.request.SingerCreationRequest;
import com.bty.music_player.dto.response.SingerResponse;
import com.bty.music_player.entity.Singer;
import com.bty.music_player.exception.AppException;
import com.bty.music_player.exception.ErrorCode;
import com.bty.music_player.mapper.SingerMapper;
import com.bty.music_player.repository.SingerRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SingerService {
    SingerRepository singerRepository;
    SingerMapper singerMapper;
    
    public List<SingerResponse> getAll() {
        return singerRepository.findAll().stream().map(singerMapper::toSingerResponse).toList();
    }
    
    public SingerResponse create(SingerCreationRequest request) {
        if(singerRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.SINGERNAME_EXISTED);
        }
        Singer singer = singerMapper.toSinger(request);
        singer = singerRepository.save(singer);
        return singerMapper.toSingerResponse(singer);
    }
}
