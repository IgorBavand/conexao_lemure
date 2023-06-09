package com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.service;

import com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.dto.response.JwtPayloadResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public JwtPayloadResponse gerarToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        var token = this.encoder.encode(JwtEncoderParameters.from(claims));

        return JwtPayloadResponse.builder()
                .user(authentication.getName())
                .token(token.getTokenValue())
                .expiration(Objects.requireNonNull(token.getExpiresAt()).toString())
                .authorities(scope)
                .build();
    }

}