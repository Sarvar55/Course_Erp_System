package com.erp.erpbackend.services.security;

import com.erp.erpbackend.constants.TokenConstants;
import com.erp.erpbackend.models.dto.RefreshTokenDto;
import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.models.porperties.security.SecurityProperties;
import com.erp.erpbackend.services.base.TokenGenerator;
import com.erp.erpbackend.services.base.TokenReader;
import com.erp.erpbackend.services.getters.EmailGetter;
import com.erp.erpbackend.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenManager implements TokenGenerator<RefreshTokenDto>,
        TokenReader<Claims>, EmailGetter {
    private final SecurityProperties securityProperties;

    @Override
    public String generate(RefreshTokenDto obj) {
        final User user = obj.getUser();
        Claims claims = Jwts.claims();
        claims.put(TokenConstants.EMAIL, user.getEmail());
        claims.put("type", TokenConstants.REFRESH_TOKEN);

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperties.getJwt().getRefreshTokenValidityTime(obj.isRememberMe()));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(exp)
                .setIssuedAt(now)
                .setSubject(String.valueOf(user.getId()))
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        Claims tokenData = Jwts.parserBuilder().setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build().parseClaimsJws(token).getBody();

        final String typeOfToken = tokenData.get("type", String.class);

        if (!TokenConstants.REFRESH_TOKEN.equals(typeOfToken)) {
            throw new RuntimeException("Invalid type of token");
        }
        return tokenData;
    }

    @Override
    public String getEmail(String token) {
        return read(token).get(TokenConstants.EMAIL, String.class);
    }
}
