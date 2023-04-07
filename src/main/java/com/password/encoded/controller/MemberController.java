package com.password.encoded.controller;

import com.password.encoded.dto.LoginRequest;
import com.password.encoded.dto.SingUp;
import com.password.encoded.service.MemberService;
import lombok.RequiredArgsConstructor;
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
    public Long signIn(@RequestBody LoginRequest request) {
        return memberService.signIn(request);
    }
}
