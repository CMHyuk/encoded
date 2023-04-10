package com.password.encoded.service;

import com.password.encoded.config.AppConfig;
import com.password.encoded.crypto.PasswordEncoder;
import com.password.encoded.dto.JwtResponse;
import com.password.encoded.dto.LoginRequest;
import com.password.encoded.dto.SingUp;
import com.password.encoded.entity.Member;
import com.password.encoded.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppConfig appConfig;

    public void signUp(SingUp singUp) {
        String encryptedPassword = passwordEncoder.encrypt(singUp.getPassword());

        Member member = Member.builder()
                .name(singUp.getName())
                .password(encryptedPassword)
                .build();

        memberRepository.save(member);
    }

    public JwtResponse signIn(LoginRequest loginRequest) {
        Member member = memberRepository.findByName(loginRequest.getName())
                .orElseThrow(IllegalAccessError::new);

        passwordEncoder.match(loginRequest.getPassword(), member.getPassword());

        SecretKey key = Keys.hmacShaKeyFor(appConfig.getJwtKey());

        String jws = Jwts.builder()
                .setSubject(String.valueOf(member.getId()))
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 100000)) //토큰 유효시간 10초
                .compact();

        return new JwtResponse(jws);
    }
}
