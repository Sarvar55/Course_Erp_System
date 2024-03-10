package com.erp.erpbackend.services.otp;

import com.erp.erpbackend.constants.TokenConstants;
import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.services.base.TokenGenerator;
import com.erp.erpbackend.services.base.TokenReader;
import com.erp.erpbackend.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@Slf4j
public class OTPProceedTokenManager implements TokenGenerator<User>, TokenReader<Claims> {

    @Override
    public String generate(User obj) {
        Claims claims = Jwts.claims();
        claims.put(TokenConstants.EMAIL, obj.getEmail());

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperties.getJwt().getAccessTokenValidityTime());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(exp)
                .setIssuedAt(now)
                .setSubject(String.valueOf(obj.getId()))
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        return null;
    }
}
