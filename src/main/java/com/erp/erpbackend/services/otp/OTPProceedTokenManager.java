package com.erp.erpbackend.services.otp;

import com.erp.erpbackend.constants.TokenConstants;
import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.models.porperties.otp.OTPProperties;
import com.erp.erpbackend.services.base.TokenGenerator;
import com.erp.erpbackend.services.base.TokenReader;
import com.erp.erpbackend.services.getters.IdGetter;
import com.erp.erpbackend.utils.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@AllArgsConstructor
@Slf4j
public class OTPProceedTokenManager implements TokenGenerator<User>, TokenReader<Claims>, IdGetter<Long> {

    private final OTPProperties otpProperties;

    @Override
    public String generate(User obj) {
        Claims claims = Jwts.claims();
        claims.put("type", "OTP_PROCEED_KEY");

        Date now = new Date();
        Date exp = new Date(now.getTime() + otpProperties.getValidityTime());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(exp)
                .setIssuedAt(now)
                .setSubject(String.valueOf(obj.getId()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public Claims read(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey())
                .build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(otpProperties.getOtpJwtData().getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Long getId(String token) {
        return Long.valueOf(read(token).getSubject());
    }
}
