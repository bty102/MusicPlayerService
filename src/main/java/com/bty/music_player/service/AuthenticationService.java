package com.bty.music_player.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bty.music_player.dto.request.AuthenticationRequest;
import com.bty.music_player.dto.request.IntrospectRequest;
import com.bty.music_player.dto.response.AuthenticationResponse;
import com.bty.music_player.dto.response.IntrospectResponse;
import com.bty.music_player.entity.Account;
import com.bty.music_player.exception.AppException;
import com.bty.music_player.exception.ErrorCode;
import com.bty.music_player.repository.AccountRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    
    @NonFinal
    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Account account = accountRepository.findByAccountName(request.getAccountName())
            .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOTEXIST));
        if(!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new AppException(ErrorCode.ACCOUNTPW_NOTMATCH);
        }
        String token = generateToken(account);
        return AuthenticationResponse.builder()
            .token(token)
            .build();
    }
    
    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(request.getToken());
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY);
        
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean valid = signedJWT.verify(jwsVerifier) && expiryTime.after(new Date());
        return IntrospectResponse.builder()
            .valid(valid)
            .build();
    }
    
    private String generateToken(Account account) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
            .subject(account.getAccountName())
            .issuer("BTYDZ")
            .issueTime(new Date())
            .expirationTime(new Date(
                Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
            ))
            .claim("scope", buildScope(account))
            .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.GENTOKEN_FAIL);
        }
    }
    
    private String buildScope(Account account) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        account.getRoles().forEach(role -> {
            stringJoiner.add("ROLE_" + role.getName());
            role.getPermissions().forEach(permission -> {
                stringJoiner.add("PER_" + permission.getName());
            });
        });
        return stringJoiner.toString();
    }
}
