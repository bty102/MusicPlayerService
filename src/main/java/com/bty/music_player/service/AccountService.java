package com.bty.music_player.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bty.music_player.dto.request.AccountCreationRequest;
import com.bty.music_player.dto.request.AccountUpdateRequest;
import com.bty.music_player.dto.response.AccountResponse;
import com.bty.music_player.entity.Account;
import com.bty.music_player.exception.AppException;
import com.bty.music_player.exception.ErrorCode;
import com.bty.music_player.mapper.AccountMapper;
import com.bty.music_player.repository.AccountRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    
    public List<AccountResponse> getAll() {
        return accountRepository.findAll().stream().map(accountMapper::toAccountResponse).toList();
    }
    
    public AccountResponse create(AccountCreationRequest request) {
        if(accountRepository.existsByAccountName(request.getAccountName())) throw new AppException(ErrorCode.ACCOUNT_EXISTED);
        Account account = accountMapper.toAccount(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }
    
    public AccountResponse update(Integer id, AccountUpdateRequest request) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOTEXIST));
        accountMapper.update(account, request);
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }
    
    public void delete(Integer id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOTEXIST));
        accountRepository.delete(account);
    }
}
