package com.password.encoded.controller;

import com.password.encoded.dto.JwtResponse;
import com.password.encoded.dto.LoginCheck;
import com.password.encoded.dto.LoginRequest;
import com.password.encoded.dto.SingUp;
import com.password.encoded.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody SingUp singUp) {
        memberService.signUp(singUp);
    }

    @PostMapping("/signIn")
    public JwtResponse signIn(@RequestBody LoginRequest request) {
        return memberService.signIn(request);
    }

    @GetMapping("/check")
    public Long loginCheck(LoginCheck loginCheck) {
        return loginCheck.getId();
    }
}
