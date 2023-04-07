package com.password.encoded.service;

import com.password.encoded.entity.Member;
import com.password.encoded.repository.MemberRepository;
import com.password.encoded.crypto.PasswordEncoder;
import com.password.encoded.dto.LoginRequest;
import com.password.encoded.dto.SingUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SingUp singUp) {
        String encryptedPassword = passwordEncoder.encrypt(singUp.getPassword());

        Member member = Member.builder()
                .name(singUp.getName())
                .password(encryptedPassword)
                .build();

        memberRepository.save(member);
    }

    public Long signIn(LoginRequest loginRequest) {
        Member member = memberRepository.findByName(loginRequest.getName())
                .orElseThrow(IllegalAccessError::new);

        passwordEncoder.match(loginRequest.getPassword(), member.getPassword());

        return member.getId();
    }
}
