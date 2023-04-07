package com.password.encoded.config;

import com.password.encoded.dto.LoginCheck;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@RequiredArgsConstructor
public class Config implements HandlerMethodArgumentResolver {

    private final AppConfig appConfig;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");
        return LoginCheck.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument 실행");
        //헤더로 토큰 받아서 검증
        String jws = webRequest.getHeader("Auth");
        if (jws == null || jws.equals("")) {
            throw new IllegalArgumentException();
        }

        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(appConfig.getJwtKey())
                    .build()
                    .parseClaimsJws(jws);
            String memberId = claims.getBody().getSubject();
            return new LoginCheck(Long.parseLong(memberId));
        } catch (JwtException e) {
            throw new RuntimeException("인증이 필요합니다.");
        }
    }
}
