package com.password.encoded.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtResponse {
    private final String jwt;
}
